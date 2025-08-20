package com.kirov.movie_system_be.genres;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/genres")
@RequiredArgsConstructor
public class GenreController {
  private final GenreRepository genres;

  @PostMapping
  public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
    Genre savedGenre = genres.save(new Genre(null, genre.getName()));
    return ResponseEntity.created(URI.create("/genres/" + savedGenre.getId())).body(savedGenre);
  }
}
