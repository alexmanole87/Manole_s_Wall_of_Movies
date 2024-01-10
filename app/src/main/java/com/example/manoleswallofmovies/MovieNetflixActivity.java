package com.example.manoleswallofmovies;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieNetflixActivity extends AppCompatActivity {

    private GlobalNetflixAPI service;
    private ListView listView;
    private ArrayAdapter<Movie> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_netflix);

        listView = findViewById(R.id.movie_list_view);
        service = RetrofitClientInstance.getRetrofitInstance().create(GlobalNetflixAPI.class);

        loadMovies();
    }

    private void loadMovies() {
        service.searchTitles().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new ArrayAdapter<>(MovieNetflixActivity.this,
                            android.R.layout.simple_list_item_1,
                            response.body());
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                // Tratează eroarea
            }
        });
    }
}
