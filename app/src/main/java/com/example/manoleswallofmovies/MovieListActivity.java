package com.example.manoleswallofmovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.content.SharedPreferences;
import android.widget.TextView;

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
        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, R.layout.list_item_movie, movieList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = convertView;
                if (itemView == null) {
                    itemView = getLayoutInflater().inflate(R.layout.list_item_movie, parent, false);
                }

                Movie currentMovie = movieList.get(position);

                TextView titleTextView = itemView.findViewById(R.id.movie_title_text_view);
                titleTextView.setText(currentMovie.getTitle());

                TextView synopsisTextView = itemView.findViewById(R.id.movie_synopsis_text_view);
                synopsisTextView.setText(currentMovie.getSynopsis());

                Button detailsButton = itemView.findViewById(R.id.view_details_button);
                detailsButton.setOnClickListener(v -> {
                    Intent intent = new Intent(MovieListActivity.this, MovieDetailActivity.class);
                    intent.putExtra("MOVIE", currentMovie);
                    startActivity(intent);
                });
                return itemView;
            }
        };
     listView.setAdapter(adapter);
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

