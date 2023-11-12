package com.example.manoleswallofmovies;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private double rating;
    private String imagePath;

    private boolean isWatched;


    public Movie(String title, String genre, double rating, String imagePath, boolean isWatched) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.imagePath = imagePath;
        this.isWatched = isWatched;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ") - Rating: " + rating;
    }
}

