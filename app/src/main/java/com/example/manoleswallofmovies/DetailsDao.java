package com.example.manoleswallofmovies;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface DetailsDao {
    // Inserare detalii
    @Insert
    long insert(Details details);

    // Obținerea detaliilor unui film după ID
    @Query("SELECT * FROM details WHERE id = :detailsId")
    Details getDetailsById(int detailsId);

    // Obținerea tuturor detaliilor
    @Query("SELECT * FROM details")
    List<Details> getAllDetails();

    // Actualizarea detaliilor
    @Update
    void update(Details details);

    // Ștergerea detaliilor
    @Delete
    void delete(Details details);
}
