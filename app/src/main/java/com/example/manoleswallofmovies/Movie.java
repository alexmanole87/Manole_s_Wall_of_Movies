package com.example.manoleswallofmovies;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private double rating;

    private String sinopsis;

    public Movie(String title, String genre, double rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public Movie(String title, String genre, double rating, String sinopsis) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.sinopsis = sinopsis;

    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ") - Rating: " + rating;
    }
}

