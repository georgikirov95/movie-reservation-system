package com.kirov.movie_system_be.showtimes;

import java.time.LocalDateTime;
import java.util.List;

import com.kirov.movie_system_be.movies.Movie;
import com.kirov.movie_system_be.seats.Seat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "showtimes")
public class Showtime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String location;
  @ManyToOne
  @JoinColumn(name = "movie_id")
  private Movie movie;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "showtime", cascade = { CascadeType.PERSIST,
      CascadeType.REMOVE }, orphanRemoval = true)
  private List<Seat> seats;
}
