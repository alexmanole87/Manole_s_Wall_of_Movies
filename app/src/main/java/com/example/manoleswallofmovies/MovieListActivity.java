package com.example.manoleswallofmovies;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MovieListActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        listView = findViewById(R.id.movie_list_view);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Observă LiveData din ViewModel
        movieViewModel.getMovieList().observe(this, movies -> {
            // Actualizează adapter-ul cu lista nouă de filme
            MovieAdapter adapter = new MovieAdapter(this, movies);
            listView.setAdapter(adapter);
        });
    }
}
