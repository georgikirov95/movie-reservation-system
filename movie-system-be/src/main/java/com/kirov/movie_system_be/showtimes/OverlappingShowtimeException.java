package com.kirov.movie_system_be.showtimes;

public class OverlappingShowtimeException extends RuntimeException {
  public OverlappingShowtimeException(String message) {
    super(message);
  }
}
