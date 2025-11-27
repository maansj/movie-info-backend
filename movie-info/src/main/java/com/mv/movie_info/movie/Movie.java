package com.mv.movie_info.movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "title",
        "genre",
        "director",
        "release_year",
        "rating",
        "country"
})

@Entity
@Table(name="movie_information")
public class Movie {
    @Id
    @Column(name = "title", unique=true)
    private String title;
    private String genre;
    private String director;
    private Integer release_year;
    private Float rating;
    private String country;

    public Movie() {
    }

    public Movie(String title, String genre, String director, Integer release_year, Float rating, String country) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.release_year = release_year;
        this.rating = rating;
        this.country = country;
    }

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
