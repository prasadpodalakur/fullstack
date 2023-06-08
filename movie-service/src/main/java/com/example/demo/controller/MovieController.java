package com.example.demo.controller;

import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorModel;
import com.example.demo.dto.SignUpDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.auth.Role;
import com.example.demo.entity.auth.User;
import com.example.demo.exception.MyCustomException;
import com.example.demo.repository.auth.RoleRepository;
import com.example.demo.repository.auth.UserRepository;
import com.example.demo.service.MovieService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MovieController {

	private final MovieService movieService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@ExceptionHandler(value = NumberFormatException.class)	
	public ResponseEntity<ErrorModel> handleException(NumberFormatException e)
	{
		ErrorModel errorModel=new ErrorModel(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
	}

	@ExceptionHandler(value = MyCustomException.class)
	public ResponseEntity<ErrorModel> handleIdNotFoundException(MyCustomException e) {
		ErrorModel errorModel = new ErrorModel(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);

	}
	
	@PostMapping("api/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto,@RequestParam("roleName") String roleName){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName(roleName).get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

	@PostMapping("movies/create")
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		System.out.println(movie);
		movie.setMovieId(UUID.randomUUID().toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(movie));
	}

	@GetMapping("movies")
	public ResponseEntity<Iterable<Movie>> getAllMovies() {
		return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
	}
	
	@GetMapping("movies/{movieId}")
	public ResponseEntity<?> findByMovieId(@PathVariable("movieId") String movieId) {
		Movie movie = movieService.findByMovieId(movieId);
		if(movie == null) {
			throw new MyCustomException("Movie not found for the given Id");
		}
		return ResponseEntity.ok().body(movie);
	}

	@PutMapping("movies/update/{movieId}")
	public ResponseEntity<?> updateByMovieId(@PathVariable("movieId") String movieId,@RequestBody Movie movie) {
		Movie theMovie = movieService.updateByMovieId(movieId, movie);
		if(theMovie == null) {
			throw new MyCustomException("Movie not found for the given Id");
		}
		return ResponseEntity.ok().body(theMovie);
	}

	@DeleteMapping("movies/delete/{movieId}")
	public ResponseEntity<?> deleteByMovieId(@PathVariable("movieId") String movieId) {
		movieService.deleteByMovieId(movieId);
		return ResponseEntity.ok().body("Movie has been deleted successfully");
		
	}

	@DeleteMapping("movies/delete/all")
	public ResponseEntity<?> deleteAllMovies() {

		movieService.deleteAllMovies();
		
		return ResponseEntity.ok().body("All movies have been deleted successfully");
	}
}
