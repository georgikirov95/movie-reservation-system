package com.kirov.movie_system_be.showtimes;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/movies/{movieId}/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {
  private final ShowtimeService showtimeService;

  @PostMapping
  public ResponseEntity<Showtime> createShowtime(@PathVariable Long movieId, @RequestBody Showtime showtime) {
    Showtime savedShowtime = showtimeService.createShowtime(movieId, showtime);
    return ResponseEntity.created(
        URI.create(String.format("/movies/%d/showtimes/%d", movieId, savedShowtime.getId())))
        .body(savedShowtime);
  }

  @GetMapping
  public ResponseEntity<List<Showtime>> listShowtimesByMovie(@PathVariable Long movieId) {
    return ResponseEntity.ok(showtimeService.listShowtimesByMovie(movieId));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Showtime> getShowtimeByMovieAndId(@PathVariable Long movieId, @PathVariable Long id) {
    return ResponseEntity.ok(showtimeService.getShowtimeByMovieAndId(movieId, id));
  }
}
