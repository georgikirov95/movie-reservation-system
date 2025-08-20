package com.kirov.movie_system_be.reservations;

import java.util.List;

import com.kirov.movie_system_be.reservations.dto.UserReservationDto;

public interface ReservationService {
   UserReservationDto createReservation(Reservation reservation);

  List<Reservation> listAll();
}
