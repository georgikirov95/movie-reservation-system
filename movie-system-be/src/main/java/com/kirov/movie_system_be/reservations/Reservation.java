package com.kirov.movie_system_be.reservations;

import java.util.List;

import com.kirov.movie_system_be.seats.Seat;
import com.kirov.movie_system_be.showtimes.Showtime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String customerName;
  @ManyToMany
  @JoinTable(name = "reservations_seats",
    joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "seat_id", referencedColumnName = "id"))
  private List<Seat> seats;
  @ManyToOne
  private Showtime showtime;
}
