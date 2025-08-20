package com.kirov.movie_system_be.reservations.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kirov.movie_system_be.reservations.Reservation;
import com.kirov.movie_system_be.reservations.ReservationRepository;
import com.kirov.movie_system_be.reservations.ReservationService;
import com.kirov.movie_system_be.reservations.dto.UserReservationDto;
import com.kirov.movie_system_be.seats.Seat;
import com.kirov.movie_system_be.seats.SeatRepository;
import com.kirov.movie_system_be.showtimes.Showtime;
import com.kirov.movie_system_be.showtimes.ShowtimeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
  private final ReservationRepository reservations;
  private final ShowtimeRepository showtimes;
  private final SeatRepository seats;

  @Override
  public UserReservationDto createReservation(Reservation reservation) {
    Showtime showtime = showtimes.findById(reservation.getShowtime().getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Showtime with id %d not found!", reservation.getShowtime().getId())));

    List<Seat> reservedSeats = reserveSeats(reservation);
    Reservation savedReservation = reservations.save(
        new Reservation(null, reservation.getCustomerName(), reservedSeats, showtime));

    return toUserReservation(savedReservation);
  }

  @Override
  public List<Reservation> listAll() {
    return reservations.findAll();
  }

  private List<Seat> reserveSeats(Reservation reservation) {
    List<Seat> reservedSeats = new ArrayList<>();

    for (Seat requestedSeat : reservation.getSeats()) {
      Seat seat = seats.findByIdAndShowtimeId(requestedSeat.getId(),
          reservation.getShowtime().getId())
          .orElseThrow(() -> new EntityNotFoundException("Seat not found"));

      if (!seat.isAvailable()) {
        throw new IllegalStateException("Seat " + seat.getNumber() + " is already reserved");
      }

      seat.setAvailable(false);
      reservedSeats.add(seat);
    }

    seats.saveAll(reservedSeats);
    return reservedSeats;
  }

  private UserReservationDto toUserReservation(Reservation reservation) {
    Showtime showtime = reservation.getShowtime();
    return new UserReservationDto(
        reservation.getId(),
        reservation.getCustomerName(),
        showtime.getMovie().getTitle(),
        showtime.getStartTime(),
        showtime.getEndTime(),
        showtime.getLocation(),
        reservation.getSeats().stream().map(Seat::getNumber).toList());
  }
}
