package com.example.wildmovies.controller;

import com.example.wildmovies.entity.Movie;
import com.example.wildmovies.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepositoryInjected) {
        this.movieRepository = movieRepositoryInjected;
    }

    @GetMapping("")
    public List<Movie> getAll() {
        return this.movieRepository.findAll();
    }

    @PostMapping("")
    public Movie create(@RequestBody Movie newMovie) {
        return this.movieRepository.save(newMovie);
    }
}
