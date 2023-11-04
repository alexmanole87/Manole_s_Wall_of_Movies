package com.example.manoleswallofmovies;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView titleTextView = findViewById(R.id.title_text_view);
        TextView genreTextView = findViewById(R.id.genre_text_view);
        TextView ratingTextView = findViewById(R.id.rating_text_view);

        Movie movie = (Movie) getIntent().getSerializableExtra("MOVIE");
        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            genreTextView.setText(movie.getGenre());
            ratingTextView.setText(String.valueOf(movie.getRating()));
        } else {
            Toast.makeText(this, "Detalii film indisponibile.", Toast.LENGTH_SHORT).show();
        }
    }
}
