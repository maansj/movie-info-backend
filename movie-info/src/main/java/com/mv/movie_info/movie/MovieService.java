package com.mv.movie_info.movie;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovie(){
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesFromGenre(String genreName) {
        return movieRepository.findAll().stream()
                .filter(movie -> genreName.equals(movie.getGenre()))
                .collect(Collectors.toList());
    }
    public List<Movie> getMoviesByTitle(String searchText) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByDirector(String searchText) {
        return movieRepository.findAll().stream()
                .filter(movie ->
                        movie.getDirector().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByCountry(String searchText) {
        return movieRepository.findAll().stream()
                .filter(movie ->
                        movie.getCountry().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByGenreAndDirector(String genre, String director) {
        return movieRepository.findAll().stream()
                .filter(movie -> genre.equals(movie.getGenre()) && director.equals(movie.getDirector()))
                .collect(Collectors.toList());
    }
    public Movie addMovie(Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    public Movie updateMovie(Movie updatedMovie) {
        Optional<Movie> existingMovie = movieRepository.findByTitle(updatedMovie.getTitle());

        if (existingMovie.isPresent()){
            Movie movieToUpdate = existingMovie.get();
            movieToUpdate.setTitle(updatedMovie.getTitle());
            movieToUpdate.setDirector(updatedMovie.getDirector());
            movieToUpdate.setRelease_year(updatedMovie.getRelease_year());
            movieToUpdate.setRating(updatedMovie.getRating());
            movieToUpdate.setCountry(updatedMovie.getCountry());

            movieRepository.save(movieToUpdate);
            return movieToUpdate;
        }
        return null;
    }

    @Transactional
    public void deleteMovie(String movieName) {
        movieRepository.deleteByTitle(movieName);
    }
}
