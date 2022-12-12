package com.dh.movie.controller;

import com.dh.movie.dto.MovieDto;
import com.dh.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    ResponseEntity<List<MovieDto>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<MovieDto>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(movieService.findByGenre(genre));
    }

    @PostMapping
    ResponseEntity<MovieDto> createNewMovie(@RequestBody MovieDto movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }
}
