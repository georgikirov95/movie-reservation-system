package com.kirov.movie_system_be.showtimes;

import java.util.List;

public interface ShowtimeService {
  Showtime createShowtime(Long movieId, Showtime showtime);

  List<Showtime> listShowtimesByMovie(Long movieId);

  Showtime getShowtimeByMovieAndId(Long movieId, Long id);
}
