package com.kirov.movie_system_be.movies;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ListCrudRepository<Movie, Long> {

}
