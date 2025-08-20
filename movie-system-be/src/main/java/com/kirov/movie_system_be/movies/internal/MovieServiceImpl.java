package com.kirov.movie_system_be.movies.internal;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kirov.movie_system_be.genres.Genre;
import com.kirov.movie_system_be.genres.GenreRepository;
import com.kirov.movie_system_be.movies.Movie;
import com.kirov.movie_system_be.movies.MovieRepository;
import com.kirov.movie_system_be.movies.MovieService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
  private final MovieRepository movies;
  private final GenreRepository genres;

  @Override
  public Movie createMovie(Movie movie) {
    Genre genre = genres.findById(movie.getGenre().getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Genre with id: %d not found!", movie.getGenre().getId())));

    return movies.save(
        new Movie(null, movie.getTitle(), movie.getDescription(), movie.getPosterUrl(), genre));
  }

  @Override
  public List<Movie> listMovies() {
    return movies.findAll();
  }

}
