package com.kirov.movie_system_be.genres;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends ListCrudRepository<Genre, Long> {

}
