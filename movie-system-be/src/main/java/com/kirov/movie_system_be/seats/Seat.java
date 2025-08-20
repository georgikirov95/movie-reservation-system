package com.kirov.movie_system_be.seats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kirov.movie_system_be.showtimes.Showtime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "seats")
public class Seat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String number;
  private boolean isAvailable;
  @ManyToOne
  @JoinColumn(name = "showtime_id")
  @JsonIgnore()
  private Showtime showtime;
}
