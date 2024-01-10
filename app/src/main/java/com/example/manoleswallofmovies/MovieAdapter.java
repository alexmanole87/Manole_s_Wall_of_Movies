package com.example.manoleswallofmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie, parent, false);
        }

        Movie currentMovie = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.movie_title_text_view);
        titleTextView.setText(currentMovie.getTitle());

        TextView genreTextView = convertView.findViewById(R.id.movie_genre_text_view);
        genreTextView.setText(currentMovie.getGenre());

        ImageView imageView = convertView.findViewById(R.id.movie_image_view);
        Uri imageUri = Uri.parse(currentMovie.getImagePath());
        imageView.setImageURI(imageUri);

        CheckBox watchedCheckBox = convertView.findViewById(R.id.movie_watched_checkbox);
        watchedCheckBox.setChecked(currentMovie.isWatched());

        Button detailsButton = convertView.findViewById(R.id.view_details_button);
        detailsButton.setOnClickListener(v -> {
            Context context = getContext();
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("MOVIE", currentMovie);
            context.startActivity(intent);
        });

        return convertView;
    }
}
