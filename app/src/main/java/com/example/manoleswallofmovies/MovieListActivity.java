package com.example.manoleswallofmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private ArrayAdapter<Movie> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        listView = findViewById(R.id.movie_list_view);
        loadMovies();

        MovieAdapter adapter = new MovieAdapter(this, movieList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMovies();
        ((MovieAdapter)listView.getAdapter()).notifyDataSetChanged();
    }

    private void loadMovies() {
        SharedPreferences sharedPreferences = getSharedPreferences("ManolesWallOfMoviesPrefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("movies", null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<Movie>>() {}.getType();
            movieList.clear();
            movieList.addAll(new Gson().fromJson(json, type));
        }
    }
}
