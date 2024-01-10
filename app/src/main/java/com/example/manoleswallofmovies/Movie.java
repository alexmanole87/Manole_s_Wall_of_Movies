package com.example.manoleswallofmovies;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private double rating;
    private String imagePath;
    private boolean isWatched;
    private MovieDetails details;
    private List<String> cast;

    // Constructor
    public Movie(String title, String genre, double rating, String imagePath, boolean isWatched, MovieDetails details, List<String> cast) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.imagePath = imagePath;
        this.isWatched = isWatched;
        this.details = details;
        this.cast = cast;
    }

    // Getteri și setteri
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public MovieDetails getDetails() {
        return details;
    }

    public void setDetails(MovieDetails details) {
        this.details = details;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    // Clasa imbricată MovieDetails
    public static class MovieDetails implements Serializable {
        private String director;
        private int releaseYear;
        private String duration;

        // Constructor
        public MovieDetails(String director, int releaseYear, String duration) {
            this.director = director;
            this.releaseYear = releaseYear;
            this.duration = duration;
        }

        // Getteri și setteri
        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public int getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }
}
