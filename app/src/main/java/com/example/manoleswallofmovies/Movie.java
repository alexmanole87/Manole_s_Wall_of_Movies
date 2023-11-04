package com.example.manoleswallofmovies;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private double rating;

    private String synopsis;

    public Movie(String title, String genre, double rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public Movie(String title, String genre, double rating, String synopsis) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.synopsis = synopsis;

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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ") - Rating: " + rating;
    }
}

