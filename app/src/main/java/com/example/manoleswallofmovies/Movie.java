package com.example.manoleswallofmovies;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import java.io.Serializable;
import java.util.List;

@Entity(tableName = "movies")
public class Movie implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "rating")
    private double rating;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "is_watched")
    private boolean isWatched;

    @ColumnInfo(name = "director_id")
    private int directorId;

    @ColumnInfo(name = "details_id")
    private int detailsId;

    public Movie(int id, String title, String genre, double rating, String imagePath, boolean isWatched, int directorId, int detailsId) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.imagePath = imagePath;
        this.isWatched = isWatched;
        this.directorId = directorId;
        this.detailsId = detailsId;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }
}
