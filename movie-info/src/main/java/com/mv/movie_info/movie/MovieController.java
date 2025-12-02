package com.mv.movie_info.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getMovies (
            @RequestParam(required=false) String genre,
            @RequestParam(required=false) String director,
            @RequestParam(required=false) String country,
            @RequestParam(required=false) String title){
       if (genre != null && director != null){
           return movieService.getMoviesByGenreAndDirector(genre, director);
       }
       else if(title != null) {
           return movieService.getMoviesByTitle(title);
       }
       else if(director != null) {
           return movieService.getMoviesByDirector(director);
       }
       else if(country != null) {
           return movieService.getMoviesByCountry(country);
       }
       else if(genre != null) {
           return movieService.getMoviesFromGenre(genre);
       }
       else {
           return movieService.getMovie();
       }
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie createdMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie){
        Movie resultMovie = movieService.updateMovie(movie);
        if (resultMovie != null){
            return new ResponseEntity<>(resultMovie, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{movieName}")
    public ResponseEntity<String> deleteMovie(@PathVariable String movieName){
        movieService.deleteMovie(movieName);
        return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
    }
}
