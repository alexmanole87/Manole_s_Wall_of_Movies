package com.example.manoleswallofmovies;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "directors")
public class Director {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "awards")
    private String awards;

    // Constructor
    public Director(int id, String name, String awards) {
        this.id = id;
        this.name = name;
        this.awards = awards;
    }

    // Getteri și Setteri
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

}
