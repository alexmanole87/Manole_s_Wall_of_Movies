package com.example.manoleswallofmovies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddMovieActivity extends AppCompatActivity {

    private EditText editTextMovieTitle;
    private EditText editTextMovieGenre;
    private RatingBar ratingBarMovie;
    private ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        editTextMovieTitle = findViewById(R.id.editTextMovieTitle);
        editTextMovieGenre = findViewById(R.id.editTextMovieGenre);
        ratingBarMovie = findViewById(R.id.ratingBarMovie);
        Button buttonSaveMovie = findViewById(R.id.buttonSaveMovie);

        movieList = loadMovies();

        buttonSaveMovie.setOnClickListener(v -> {
            String title = editTextMovieTitle.getText().toString();
            String genre = editTextMovieGenre.getText().toString();
            float rating = ratingBarMovie.getRating();

            if (!title.isEmpty() && !genre.isEmpty()) {
                Movie newMovie = new Movie(title, genre, rating);
                movieList.add(newMovie);
                saveMovies(movieList);


                Toast.makeText(AddMovieActivity.this, R.string.movie_save, Toast.LENGTH_SHORT).show();


                editTextMovieTitle.setText("");
                editTextMovieGenre.setText("");
                ratingBarMovie.setRating(0);
            } else {
                Toast.makeText(AddMovieActivity.this, R.string.title_genere_warning, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveMovies(ArrayList<Movie> movieList) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("ManolesWallOfMoviesPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String moviesJson = gson.toJson(movieList);
            editor.putString("movie", moviesJson);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.error_save_movies_txt, Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Movie> loadMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("ManolesWallOfMoviesPrefs", MODE_PRIVATE);
            String moviesJson = sharedPreferences.getString("movie", null);
            Gson gson = new Gson();

            if (moviesJson != null) {
                Type movieListType = new TypeToken<ArrayList<Movie>>() {}.getType();
                movieList = gson.fromJson(moviesJson, movieListType);
            } else {
                movieList = new ArrayList<>();
            }
            return movieList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieList;
    }
}
