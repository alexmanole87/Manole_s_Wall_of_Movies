package com.example.manoleswallofmovies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;

@Dao
public interface MovieDao {
    // Inserare film
    @Insert
    long insert(Movie movie);

    // Obținerea unui film după ID
    @Transaction
    @Query("SELECT * FROM movies WHERE id = :movieId")
    //Movie getMovieById(int movieId);
    LiveData<MovieWithDirectorAndDetails> getMovieWithDirectorAndDetails(int movieId);

    // Obținerea unui film după gen si nota

    @Query("SELECT * FROM movies WHERE genre = :genre")
    LiveData<List<Movie>> getMoviesByGenre(String genre);

    @Query("SELECT * FROM movies WHERE rating >= :rating")
    LiveData<List<Movie>> getMoviesByRating(float rating);


    // Obținerea tuturor filmelor
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovies();

    // Actualizarea unui film
    @Update
    void update(Movie movie);

    // Ștergerea unui film
    @Delete
    void delete(Movie movie);

}
