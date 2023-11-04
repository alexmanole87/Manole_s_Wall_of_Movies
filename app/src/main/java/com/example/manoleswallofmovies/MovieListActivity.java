package com.example.manoleswallofmovies;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ListView listView = findViewById(R.id.movie_list_view);
        loadMovies();

        ArrayAdapter<Movie> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                movieList
        );
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadMovies() {

        SharedPreferences sharedPreferences = getSharedPreferences("ManolesWallOfMoviesPrefs", MODE_PRIVATE);
        String moviesJson = sharedPreferences.getString("movie", null);

        Gson gson = new Gson();
        if (moviesJson != null) {
            Type movieListType = new TypeToken<ArrayList<Movie>>() {
            }.getType();
            movieList = gson.fromJson(moviesJson, movieListType);
            Log.d("MovieListActivity", "Movies JSON from SharedPreferences: " + moviesJson);
        } else {

            movieList = new ArrayList<>();
            movieList.add(new Movie("Titlu Film 1", "Gen Film 1", 8.5));
//            movieList.add(new Movie("Titlu Film 2", "Gen Film 2", 7.3));
//            movieList.add(new Movie("Titlu Film 3", "Gen Film 3", 9.1));
            moviesJson = gson.toJson(movieList);
            sharedPreferences.edit().putString("movie", moviesJson).apply();
        }
    }
}

