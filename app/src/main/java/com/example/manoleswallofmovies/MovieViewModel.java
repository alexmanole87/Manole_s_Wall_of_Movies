package com.example.manoleswallofmovies;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    public void loadMovies(SharedPreferences sharedPreferences) {
        new Thread(() -> {
            String json = sharedPreferences.getString("movies", null);
            if (json != null) {
                Type type = new TypeToken<ArrayList<Movie>>() {}.getType();
                ArrayList<Movie> loadedMovies = new Gson().fromJson(json, type);
                movieList.postValue(loadedMovies);
            }
        }).start();
    }
}
