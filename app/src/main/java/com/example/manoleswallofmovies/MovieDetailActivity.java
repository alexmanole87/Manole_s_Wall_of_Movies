package com.example.manoleswallofmovies;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView titleTextView = findViewById(R.id.title_text_view);
        TextView genreTextView = findViewById(R.id.genre_text_view);
        TextView ratingTextView = findViewById(R.id.rating_text_view);
        ImageView movieImageView = findViewById(R.id.movie_image_view);

        Movie movie = (Movie) getIntent().getSerializableExtra("MOVIE");

        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            genreTextView.setText(movie.getGenre());
            ratingTextView.setText(String.format("%s/10", movie.getRating()));

            // Încarcă imaginea folosind Glide
            Glide.with(this)
                    .load(movie.getImagePath())
                    .into(movieImageView);
        } else {
            Toast.makeText(this, R.string.no_details, Toast.LENGTH_SHORT).show();
        }
    }
}
