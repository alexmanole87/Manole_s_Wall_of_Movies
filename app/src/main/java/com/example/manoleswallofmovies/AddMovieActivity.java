package com.example.manoleswallofmovies;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            FileOutputStream fos = openFileOutput(getString(R.string.nume_bd_movies), MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(movieList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.error_save_movies_txt, Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Movie> loadMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(getString(R.string.nume_bd_movies));
            ObjectInputStream ois = new ObjectInputStream(fis);
            movieList = (ArrayList<Movie>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieList;
    }
}
