package com.kirov.movie_system_be.movies;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/movies")
@RequiredArgsConstructor
public class MovieController {
  private final MovieService movieService;

  @PostMapping
  public ResponseEntity<Movie> createMovie(@RequestBody Movie movieDto) {
    return ResponseEntity.ok(movieService.createMovie(movieDto));
  }

  @GetMapping
  public ResponseEntity<List<Movie>> listMovies() {
    return ResponseEntity.ok(movieService.listMovies());
  }
}
