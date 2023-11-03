package com.example.manoleswallofmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {

    private ArrayList<Movies> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ListView listView = findViewById(R.id.movie_list_view);
        movieList = loadMovies();

        ArrayAdapter<Movies> adapter = new ArrayAdapter<Movies>(this, R.layout.list_item_movie, R.id.movie_title_text_view, movieList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie, parent, false);
                }


                TextView titleTextView = convertView.findViewById(R.id.movie_title_text_view);
                Button detailsButton = convertView.findViewById(R.id.view_details_button);


                Movies movie = getItem(position);
                if (movie != null) {
                    titleTextView.setText(movie.getTitle());
                }


                detailsButton.setOnClickListener(v -> {
                    Intent intent = new Intent(MovieListActivity.this, MovieDetailActivity.class);
//                    Movies selectedMovie = movieList.get(position);
                    intent.putExtra("MOVIE", position);
                    startActivity(intent);
                });

                return convertView;
            }
        };

        listView.setAdapter(adapter);
    }

    private ArrayList<Movies> loadMovies() {
        // Implementația metodei de încărcare a filmelor
        // ...
        return new ArrayList<>(); // Returnează lista de filme
    }
}
