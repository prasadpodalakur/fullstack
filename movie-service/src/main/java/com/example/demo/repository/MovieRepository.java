package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	Optional<Movie> findByMovieId(String movieId);

}
