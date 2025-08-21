package com.kirov.movie_system_be.movies;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/movies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {
  private final MovieService movieService;

  @PostMapping
  public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
    Movie savedMovie = movieService.createMovie(movie);
    return ResponseEntity.created(URI.create("/movies/" + savedMovie.getId())).body(savedMovie);
  }

  @GetMapping
  public ResponseEntity<List<Movie>> listMovies() {
    return ResponseEntity.ok(movieService.listMovies());
  }
}
