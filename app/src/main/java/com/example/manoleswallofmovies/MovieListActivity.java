package com.example.manoleswallofmovies;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MovieListActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private ListView listView;
    private Spinner spinnerFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        listView = findViewById(R.id.movie_list_view);
        spinnerFilter = findViewById(R.id.spinnerFilter);
        initializeSpinner();

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Inițial afișează toate filmele
        movieViewModel.getMovieList().observe(this, movies -> {
            MovieAdapter adapter = new MovieAdapter(this, movies);
            listView.setAdapter(adapter);
        });
    }

    private void initializeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.movie_genres_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(adapter);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenre = parent.getItemAtPosition(position).toString();
                filterMovies(selectedGenre);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MovieListActivity.this, getString(R.string.on_nothing_sel_txt), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterMovies(String genre) {
        movieViewModel.getMoviesByGenre(genre).observe(this, movies -> {
            MovieAdapter adapter = new MovieAdapter(this, movies);
            listView.setAdapter(adapter);
        });
    }
}
