package com.dh.catalog.mapper;

import com.dh.catalog.dto.MovieDto;
import com.dh.catalog.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    Movie toMovieEntity(MovieDto dto);
    MovieDto toMovieEntity(Movie entity);

    List<Movie> toMovieEntity(Iterable<MovieDto> dtos);
    List<MovieDto> toMovieDto(Iterable<Movie> entities);
}
