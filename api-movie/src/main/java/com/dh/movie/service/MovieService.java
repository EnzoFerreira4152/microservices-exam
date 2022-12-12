package com.dh.movie.service;

import com.dh.movie.dto.MovieDto;
import com.dh.movie.event.NewMovieProducer;
import com.dh.movie.mapper.MovieMapper;
import com.dh.movie.mapper.MovieMapperImpl;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final NewMovieProducer newMovieProducer;

    @Inject
    private MovieMapper mapper;

    public MovieService(MovieRepository movieRepository, NewMovieProducer newMovieProducer) {
        this.movieRepository = movieRepository;
        this.newMovieProducer = newMovieProducer;
    }

    public List<MovieDto> getAll(){
        List<Movie> movies = movieRepository.findAll();
        return mapper.toMovieDto(movies);
    }

    public List<MovieDto> findByGenre(String genre) {
        List<Movie> movies = movieRepository.findAllByGenre(genre);
        return mapper.toMovieDto(movies);
    }

    public MovieDto createMovie(MovieDto movieDto) {
        Movie movieResource = movieRepository.save(mapper.toMovieEntity(movieDto));
        newMovieProducer.execute(movieResource);
        return mapper.toMovieDto(movieResource);
    }
}
