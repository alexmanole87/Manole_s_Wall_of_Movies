package com.example.manoleswallofmovies;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);
        TextView tvMessage = findViewById(R.id.textViewTitle);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_file), MODE_PRIVATE);
        String name = sharedPreferences.getString(getString(R.string.pref_name), "");
        String username = sharedPreferences.getString(getString(R.string.pref_username), "");

        if (!name.isEmpty()) {
            tvMessage.setText(getString(R.string.welcome_txt, name));
        } else {
            tvMessage.setText(getString(R.string.welcome_txt, username));
        }


        Button bam = findViewById(R.id.buttonAddMovie);
        Button bvm = findViewById(R.id.buttonViewMovies);

        bam.setOnClickListener(view -> {
            Intent intent_add = new Intent(WallActivity.this, AddMovieActivity.class);
            startActivity(intent_add);
        });

        bam.setOnLongClickListener(view -> {
            Toast.makeText(WallActivity.this, R.string.txt_help_add, Toast.LENGTH_LONG).show();

            return true;
        });

        bvm.setOnClickListener(view -> {
            Intent intent_view = new Intent(WallActivity.this, MovieListActivity.class);
            startActivity(intent_view);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void logout() {

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_file), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("Username");
        editor.remove("Password");
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_list) {
            Intent menuIntent = new Intent(this, MovieListActivity.class);
            startActivity(menuIntent);
            return true;
        } else if (id == R.id.menu_wall) {
            Intent wallIntent = new Intent(this, WallActivity.class);
            startActivity(wallIntent);
            return true;
        } else if (id == R.id.menu_log_out) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}