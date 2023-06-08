package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Movie;
import com.example.demo.exception.MyCustomException;
import com.example.demo.repository.MovieRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
	private final MovieRepository movieRepository;

	@Override
	public Movie createMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Iterable<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie findByMovieId(String movieId) {
		Optional<Movie> movie = movieRepository.findByMovieId(movieId);
		if(movie.isPresent()) {
			return movie.get();
		}
		return null;
	}

	@Override
	public Movie updateByMovieId(String movieId, Movie ip_movie) {
		Optional<Movie> movie = movieRepository.findByMovieId(movieId);
		if(movie.isPresent()) {
			System.out.println(movie);
			Movie theMovie = movie.get();
			theMovie.setMovieName(ip_movie.getMovieName());
			theMovie.setGenre(ip_movie.getGenre());
			movieRepository.save(theMovie);
			
			return theMovie;
		}
		return null;
	}

	@Override
	public void deleteByMovieId(String movieId) {
		Optional<Movie> movie = movieRepository.findByMovieId(movieId);
		if(movie.isPresent()) {
			movieRepository.delete(movie.get());
		} else {
			throw new MyCustomException("Movie not found for the given Id for the deletion");
		}
	}

	@Override
	public void deleteAllMovies() {
		movieRepository.deleteAll();
	}

}
