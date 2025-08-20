package com.kirov.movie_system_be.showtimes.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kirov.movie_system_be.movies.Movie;
import com.kirov.movie_system_be.movies.MovieRepository;
import com.kirov.movie_system_be.seats.Seat;
import com.kirov.movie_system_be.seats.SeatRepository;
import com.kirov.movie_system_be.showtimes.OverlappingShowtimeException;
import com.kirov.movie_system_be.showtimes.Showtime;
import com.kirov.movie_system_be.showtimes.ShowtimeRepository;
import com.kirov.movie_system_be.showtimes.ShowtimeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
  private final ShowtimeRepository showtimes;
  private final MovieRepository movies;
  private final SeatRepository seats;

  @Override
  public Showtime createShowtime(Long movieId, Showtime showtime) {
    Movie movie = movies.findById(movieId)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Movie with id: %d not found!", movieId)));

    List<Showtime> overlappingShowtimes = showtimes.findOverlappingShowtimes(
        showtime.getStartTime(), showtime.getEndTime(), showtime.getLocation());

    if (!overlappingShowtimes.isEmpty()) {
      throw new OverlappingShowtimeException("Showtime overlaps with existing showtimes.");
    }

    Showtime savedShowtime = showtimes.save(
        new Showtime(null, showtime.getStartTime(), showtime.getEndTime(), showtime.getLocation(), movie, null));

    List<Seat> showtimeSeats = generateSeats(savedShowtime);
    seats.saveAll(showtimeSeats);
    savedShowtime.setSeats(showtimeSeats);

    return savedShowtime;
  }

  @Override
  public List<Showtime> listShowtimesByMovie(Long movieId) {
    return showtimes.findByMovieId(movieId);
  }

  @Override
  public Showtime getShowtimeByMovieAndId(Long movieId, Long id) {
    return showtimes.findByMovieIdAndId(movieId, id)
        .orElseThrow(
            () -> new EntityNotFoundException(
                String.format("Showtime with movieId: %d and id: %d not found", movieId, id)));
  }

  private List<Seat> generateSeats(Showtime showtime) {
    List<Seat> seats = new ArrayList<>();
    for (int i = 1; i <= 30; i++) {
      seats.add(new Seat(null, "A-" + i, true, showtime));
    }
    return seats;
  }
}
