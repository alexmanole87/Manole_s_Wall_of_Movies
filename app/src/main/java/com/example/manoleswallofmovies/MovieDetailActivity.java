package com.example.manoleswallofmovies;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        TextView titleTextView = findViewById(R.id.title_text_view);
        TextView genreTextView = findViewById(R.id.genre_text_view);
        TextView ratingTextView = findViewById(R.id.rating_text_view);
        ImageView movieImageView = findViewById(R.id.movie_image_view);
        TextView directorTextView = findViewById(R.id.director_text_view);
        TextView releaseYearTextView = findViewById(R.id.release_year_text_view);
        TextView durationTextView = findViewById(R.id.duration_text_view);
        TextView castTextView = findViewById(R.id.cast_text_view);

        // Preia ID-ul filmului transmis prin intent
        int movieId = getIntent().getIntExtra("MOVIE_ID", -1);

        if (movieId != -1) {
            viewModel.getMovieWithDirectorAndDetails(movieId).observe(this, movieWithDirectorAndDetails -> {
                if (movieWithDirectorAndDetails != null) {
                    Movie movie = movieWithDirectorAndDetails.movie;
                    titleTextView.setText(movie.getTitle());
                    genreTextView.setText(movie.getGenre());
                    ratingTextView.setText(String.format("%s/10", movie.getRating()));

                    Glide.with(this)
                            .load(movie.getImagePath())
                            .into(movieImageView);

                    // Presupunând că DTO include informații despre Director și Details
                    Director director = movieWithDirectorAndDetails.director;
                    if (director != null) {
                        directorTextView.setText(director.getName());
                    }

                    Details details = movieWithDirectorAndDetails.details;
                    if (details != null) {
                        releaseYearTextView.setText(details.getReleaseDate());
                        durationTextView.setText(details.getDuration());
                        castTextView.setText(details.getCast());
                    }
                }
            });
        } else {
            Toast.makeText(this, R.string.no_details, Toast.LENGTH_SHORT).show();
        }
    }
}
