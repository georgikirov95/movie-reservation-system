package com.kirov.movie_system_be.reservations.dto;

import java.time.LocalDateTime;
import java.util.List;

public record UserReservationDto(
    Long id,
    String customerName,
    String movie,
    LocalDateTime startTime,
    LocalDateTime endTime,
    String location,
    List<String> seats) { }
