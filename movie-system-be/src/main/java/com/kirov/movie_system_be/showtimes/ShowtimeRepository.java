package com.kirov.movie_system_be.showtimes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends ListCrudRepository<Showtime, Long> {
  @Query("""
      SELECT s
      FROM Showtime s
      WHERE s.startTime < :endTime AND s.endTime > :startTime
      AND s.location = :location
      """)
  List<Showtime> findOverlappingShowtimes(LocalDateTime startTime, LocalDateTime endTime, String location);

  List<Showtime> findByMovieId(Long movieId);

  Optional<Showtime> findByMovieIdAndId(Long movieId, Long id);
}
