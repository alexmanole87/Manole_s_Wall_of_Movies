package com.example.manoleswallofmovies;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddMovieActivity extends AppCompatActivity {

    private static final int REQUEST_READ_STORAGE_PERMISSION = 100;

    private EditText editTextMovieTitle;
    private EditText editTextMovieGenre;
    private EditText editTextDirector;
    private EditText editTextReleaseYear;
    private EditText editTextDuration;
    private EditText editTextLeadActor;
    private RatingBar ratingBarMovie;
    private ImageView movieImageView;
    private Uri imageUri;
    private ArrayList<Movie> movieList;

    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    movieImageView.setImageURI(imageUri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        editTextMovieTitle = findViewById(R.id.editTextMovieTitle);
        editTextMovieGenre = findViewById(R.id.editTextMovieGenre);
        editTextDirector = findViewById(R.id.editTextDirector);
        editTextReleaseYear = findViewById(R.id.editTextReleaseYear);
        editTextDuration = findViewById(R.id.editTextDuration);
        editTextLeadActor = findViewById(R.id.editTextLeadActor);
        ratingBarMovie = findViewById(R.id.ratingBarMovie);
        movieImageView = findViewById(R.id.movieImageView);

        movieList = loadMovies();

        Button buttonChooseImage = findViewById(R.id.buttonChooseImage);
        buttonChooseImage.setOnClickListener(v -> checkPermissionAndPickImage());

        Button buttonSaveMovie = findViewById(R.id.buttonSaveMovie);
        buttonSaveMovie.setOnClickListener(v -> saveMovie());
    }

    private void checkPermissionAndPickImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_READ_STORAGE_PERMISSION);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            pickImageLauncher.launch(intent);
        }
    }


    private void saveMovie() {
        String title = editTextMovieTitle.getText().toString();
        String genre = editTextMovieGenre.getText().toString();
        float rating = ratingBarMovie.getRating();
        String director = editTextDirector.getText().toString();
        int releaseYear = Integer.parseInt(editTextReleaseYear.getText().toString());
        String duration = editTextDuration.getText().toString();
        List<String> cast = Arrays.asList(editTextLeadActor.getText().toString().split(","));

        if (!title.isEmpty() && !genre.isEmpty() && imageUri != null) {
            Movie.MovieDetails details = new Movie.MovieDetails(director, releaseYear, duration);
            Movie newMovie = new Movie(title, genre, rating, imageUri.toString(), false, details, cast);
            movieList.add(newMovie);
            saveMovies(movieList);
            Toast.makeText(this, R.string.movie_added_successfully, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_save_movies_txt, Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Movie> loadMovies() {
        SharedPreferences sharedPreferences = getSharedPreferences("ManolesWallOfMoviesPrefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("movies", null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<ArrayList<Movie>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    private void saveMovies(ArrayList<Movie> movieList) {
        SharedPreferences sharedPreferences = getSharedPreferences("ManolesWallOfMoviesPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(movieList);
        editor.putString("movies", json);
        editor.apply();
    }
}
