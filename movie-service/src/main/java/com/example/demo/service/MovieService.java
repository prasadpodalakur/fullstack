package com.example.demo.service;

import com.example.demo.entity.Movie;

public interface MovieService {
	
	public Movie createMovie(Movie movie);
	
	public  Iterable<Movie> getAllMovies();
	
	public Movie findByMovieId(String movieId);
	
	public Movie updateByMovieId(String movieId, Movie movie);
	
	public void deleteByMovieId(String movieId);
	
	public void deleteAllMovies();


}
