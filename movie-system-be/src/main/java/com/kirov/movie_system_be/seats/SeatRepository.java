package com.kirov.movie_system_be.seats;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends ListCrudRepository<Seat, Long> {
  Optional<Seat> findByIdAndShowtimeId(Long id, Long showtimeId);
}
