package com.example.manoleswallofmovies;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieViewModel extends AndroidViewModel {
    private final MovieDao movieDao;
    private final LiveData<List<Movie>> movieList;
    private final ExecutorService executorService;

    public MovieViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        movieDao = db.movieDao();
        movieList = movieDao.getAllMovies();
        executorService = Executors.newFixedThreadPool(2); // Numărul de thread-uri poate fi ajustat
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }
    public LiveData<List<Movie>> getMoviesByGenre(String genre) {
        return movieDao.getMoviesByGenre(genre);
    }

    public void insertMovie(Movie movie) {
        executorService.execute(() -> movieDao.insert(movie));
    }

    public void updateMovie(Movie movie) {
        executorService.execute(() -> movieDao.update(movie));
    }

    public void deleteMovie(Movie movie) {
        executorService.execute(() -> movieDao.delete(movie));
    }

    public LiveData<MovieWithDirectorAndDetails> getMovieWithDirectorAndDetails(int movieId) {
        return movieDao.getMovieWithDirectorAndDetails(movieId);
    }


}
