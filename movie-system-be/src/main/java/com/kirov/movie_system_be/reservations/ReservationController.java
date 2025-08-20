package com.kirov.movie_system_be.reservations;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirov.movie_system_be.reservations.dto.UserReservationDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/reservations")
@RequiredArgsConstructor
public class ReservationController {
  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<UserReservationDto> createReservation(@RequestBody Reservation reservation) {
    UserReservationDto userReservation = reservationService.createReservation(reservation);
    return ResponseEntity.created(URI.create("/reservations/" + userReservation.id())).body(userReservation);
  }

  @GetMapping
  public ResponseEntity<List<Reservation>> listAll() {
    return ResponseEntity.ok(reservationService.listAll());
  }
}
