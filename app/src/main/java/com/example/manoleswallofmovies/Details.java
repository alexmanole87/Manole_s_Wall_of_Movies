package com.example.manoleswallofmovies;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "details")
public class Details {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "duration")
    public String duration;

    @ColumnInfo(name = "cast")
    public String cast; // Poate fi un șir de caractere sau un alt tip mai complex, cum ar fi List<String>

    @ColumnInfo(name = "release_date")
    public String releaseDate;

    public Details(int id, String duration, String cast, String releaseDate) {
        this.id = id;
        this.duration = duration;
        this.cast = cast;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
