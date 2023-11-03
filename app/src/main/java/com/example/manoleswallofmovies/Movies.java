package com.example.manoleswallofmovies;

import java.io.Serializable;

public class Movies implements Serializable {
    private String title;
    private String genre;
    private float rating;

    public Movies(String title, String genre, float rating) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
