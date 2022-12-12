package com.dh.movie.mapper;

import com.dh.movie.dto.MovieDto;
import com.dh.movie.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    Movie toMovieEntity(MovieDto dto);
    MovieDto toMovieDto(Movie entity);

    List<Movie> toMovieEntity(Iterable<MovieDto> dtos);
    List<MovieDto> toMovieDto(Iterable<Movie> entities);
}
