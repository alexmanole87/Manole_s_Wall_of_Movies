package com.example.manoleswallofmovies;

import androidx.room.Embedded;
import androidx.room.Relation;

public class MovieWithDirectorAndDetails {
    @Embedded
    public Movie movie;

    @Relation(
            parentColumn = "director_id", // asigurați-vă că aceasta este coloana corectă în entitatea Movie
            entityColumn = "id",         // id-ul din entitatea Director
            entity = Director.class      // clasa entității Director
    )
    public Director director;

    @Relation(
            parentColumn = "details_id",  // asigurați-vă că aceasta este coloana corectă în entitatea Movie
            entityColumn = "id",         // id-ul din entitatea Details
            entity = Details.class       // clasa entității Details
    )
    public Details details;


}
