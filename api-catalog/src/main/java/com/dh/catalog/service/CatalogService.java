package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.dto.CatalogDto;
import com.dh.catalog.dto.MovieDto;
import com.dh.catalog.dto.series.SerieDto;
import com.dh.catalog.exceptions.CircuitBreakerException;
import com.dh.catalog.mapper.*;
import com.dh.catalog.model.Movie;
import com.dh.catalog.model.series.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CatalogService {
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;
    private final MovieServiceClient movieClient;
    private final SerieServiceClient serieClient;

    @Inject
    private MovieMapper movieMapper;
    @Inject
    private SerieMapper serieMapper;

    public CatalogService(MovieRepository movieRepository, SerieRepository serieRepository, MovieServiceClient movieClient, SerieServiceClient serieClient) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
        this.movieClient = movieClient;
        this.serieClient = serieClient;
    }

    @CircuitBreaker(name = "catalog", fallbackMethod = "onlineCatalogFallbackMethod" )
    @Retry(name = "catalog")
    public CatalogDto getCatalogOnline(String genre) throws Exception{
        String normalizeGenre = genre.toLowerCase();
        List<MovieDto> movieDtos = movieClient.getMovieByGenre(normalizeGenre);
        List<SerieDto> serieDtos = serieClient.getSerieByGenre(normalizeGenre);
        return new CatalogDto(genre, movieDtos, serieDtos);
    }

    public CatalogDto getCatalogOffline(String genre) {
        String normalizeGenre = genre.toLowerCase();
        List<Movie> movies = movieRepository.findAllByGenre(normalizeGenre);
        List<Serie> series = serieRepository.findAllByGenre(normalizeGenre);

        Map<String, Object> dtosResult = converterDataToDto(movies, series);

        return new CatalogDto(
                normalizeGenre,
                (List<MovieDto>) dtosResult.get("movies"),
                (List<SerieDto>) dtosResult.get("series")
        );
    }

    public CatalogDto getAll() {
        List<Movie> movies = movieRepository.findAll();
        List<Serie> series = serieRepository.findAll();

        Map<String, Object> dtosResult = converterDataToDto(movies, series);

        return new CatalogDto(
                "all",
                (List<MovieDto>) dtosResult.get("movies"),
                (List<SerieDto>) dtosResult.get("series")
        );
    }

    public void saveNewMovie(MovieDto movieDto){
        MovieDto dto = new MovieDto(
                movieDto.id(),
                movieDto.name().toLowerCase(),
                movieDto.genre(),
                movieDto.urlStream()
        );
        movieRepository.save(movieMapper.toMovieEntity(dto));
    }

    public void saveNewSerie(SerieDto serieDto){
        SerieDto dto = new SerieDto(
                serieDto.id(),
                serieDto.name().toLowerCase(),
                serieDto.genre(),
                serieDto.seasons()
        );
        serieRepository.save(serieMapper.ToSerieEntity(dto));
    }

    /**
     * En caso de un estado abierto opté por devolver una excepción con un mensaje informativo
     * para el cliente y la causa del error correspondiente.
     * */
    private CatalogDto onlineCatalogFallbackMethod(Exception exception) throws CircuitBreakerException {
        throw new CircuitBreakerException(exception.getMessage() + " \nCause: " + exception.getCause());
    }

    private Map<String, Object> converterDataToDto(List<Movie> movies, List<Serie> series) {
        HashMap<String, Object> response = new HashMap<>();

        if(movies.isEmpty() && series.isEmpty()) {
            response.put("info", "Both lists are empty");
            response.put("movies", List.of());
            response.put("series", List.of());
        }else{
            response.put("movies", movieMapper.toMovieDto(movies));
            response.put("series", serieMapper.toSerieDto(series));
        }

        return response;
    }
}
