package com.example.manoleswallofmovies;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {
    public MovieAdapter(Context context, List<Movie> movies){
        super(context, 0, movies);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie, parent, false);
        }

        Movie currentMovie = getItem(position);
        if (currentMovie != null) {
            TextView titleTextView = convertView.findViewById(R.id.movie_title_text_view);
            if (titleTextView != null) {
                titleTextView.setText(currentMovie.getTitle());
            }
            else if(currentMovie == null) {
                Toast.makeText(this.getContext(), R.string.current_movie_error, Toast.LENGTH_LONG).show();
            }

            TextView genreTextView = convertView.findViewById(R.id.movie_genre_text_view);
            if (genreTextView != null) {
                genreTextView.setText(currentMovie.getGenre());
            }

            else if(genreTextView == null) {Toast.makeText(this.getContext(), R.string.genere_show_err, Toast.LENGTH_LONG).show();}

            TextView ratingTextView = convertView.findViewById(R.id.movie_rating_text_view);
            if (ratingTextView != null) {
                ratingTextView.setText(String.valueOf(currentMovie.getRating()));
            }

            ImageView imageView = convertView.findViewById(R.id.movie_image_view);
            if (imageView != null && currentMovie.getImagePath() != null && !currentMovie.getImagePath().isEmpty()) {
                Uri imageUri = Uri.parse(currentMovie.getImagePath());
                imageView.setImageURI(imageUri);
            }

            CheckBox watchedCheckBox = convertView.findViewById(R.id.movie_watched_checkbox);
            if (watchedCheckBox != null) {
                watchedCheckBox.setChecked(currentMovie.isWatched());
            }
        }

        return convertView;
    }

}
