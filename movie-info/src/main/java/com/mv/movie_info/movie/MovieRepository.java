package com.mv.movie_info.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    void deleteByTitle(String movieTitle);
    Optional<Movie> findByTitle(String title);
}
