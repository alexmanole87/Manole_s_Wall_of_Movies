package com.example.manoleswallofmovies;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.util.StringJoiner;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView titleTextView = findViewById(R.id.title_text_view);
        TextView genreTextView = findViewById(R.id.genre_text_view);
        TextView ratingTextView = findViewById(R.id.rating_text_view);
        ImageView movieImageView = findViewById(R.id.movie_image_view);

        TextView directorTextView = findViewById(R.id.director_text_view);
        TextView releaseYearTextView = findViewById(R.id.release_year_text_view);
        TextView durationTextView = findViewById(R.id.duration_text_view);
        TextView castTextView = findViewById(R.id.cast_text_view);

        Movie movie = (Movie) getIntent().getSerializableExtra("MOVIE");

        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            genreTextView.setText(movie.getGenre());
            ratingTextView.setText(String.format("%s/10", movie.getRating()));

            Glide.with(this)
                    .load(movie.getImagePath())
                    .into(movieImageView);

            Movie.MovieDetails details = movie.getDetails();
            if (details != null) {
                directorTextView.setText(details.getDirector());
                releaseYearTextView.setText(String.valueOf(details.getReleaseYear()));
                durationTextView.setText(details.getDuration());
            }

            if (movie.getCast() != null && !movie.getCast().isEmpty()) {
                StringJoiner joiner = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    joiner = new StringJoiner(", ");
                }
                for (String actor : movie.getCast()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        joiner.add(actor);
                    }
                }
                castTextView.setText(joiner.toString());
            }
        } else {
            Toast.makeText(this, R.string.no_details, Toast.LENGTH_SHORT).show();
        }
    }
}
