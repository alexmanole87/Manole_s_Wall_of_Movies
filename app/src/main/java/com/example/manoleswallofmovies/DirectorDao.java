package com.example.manoleswallofmovies;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface DirectorDao {
    // Inserarea unui nou regizor
    @Insert
    long insert(Director director);

    // Obținerea unui regizor după ID
    @Query("SELECT * FROM directors WHERE id = :directorId")
    Director getDirectorById(int directorId);

    // Obținerea tuturor regizorilor
    @Query("SELECT * FROM directors")
    List<Director> getAllDirectors();

    // Actualizarea informațiilor unui regizor
    @Update
    void update(Director director);

    // Ștergerea unui regizor
    @Delete
    void delete(Director director);

}
