package com.example.manoleswallofmovies;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddMovieActivity extends AppCompatActivity {

    private static final int REQUEST_READ_STORAGE_PERMISSION = 100;

    private EditText editTextMovieTitle;
    private EditText editTextMovieGenre;
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

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        editTextMovieTitle = findViewById(R.id.editTextMovieTitle);
        editTextMovieGenre = findViewById(R.id.editTextMovieGenre);
        ratingBarMovie = findViewById(R.id.ratingBarMovie);
        movieImageView = findViewById(R.id.movieImageView);
        Button buttonSaveMovie = findViewById(R.id.buttonSaveMovie);
        Button buttonChooseImage = findViewById(R.id.button_choose_image);

        movieList = loadMovies();

        buttonChooseImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_READ_STORAGE_PERMISSION);
            } else {
                openImagePicker();
            }
        });

        buttonSaveMovie.setOnClickListener(v -> saveMovie());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    private void saveMovie() {
        String title = editTextMovieTitle.getText().toString();
        String genre = editTextMovieGenre.getText().toString();
        float rating = ratingBarMovie.getRating();

        if (!title.isEmpty() && !genre.isEmpty() && imageUri != null) {
            Movie newMovie = new Movie(title, genre, rating, imageUri.toString(), false);
            movieList.add(newMovie);
            saveMovies(movieList);

            Toast.makeText(this, R.string.movie_added_successfully, Toast.LENGTH_SHORT).show();

            // Reset fields
            editTextMovieTitle.setText("");
            editTextMovieGenre.setText("");
            ratingBarMovie.setRating(0);
            movieImageView.setImageResource(R.drawable.ic_launcher_background);
        } else {
            Toast.makeText(this, R.string.please_fill_all_the_fields_and_select_an_image, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(this, R.string.permission_required_images, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
