package com.kirov.movie_system_be.movies;

import java.util.List;

public interface MovieService {
  Movie createMovie(Movie movie);

  List<Movie> listMovies();
}
