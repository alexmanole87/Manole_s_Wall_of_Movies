package com.example.manoleswallofmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class WallActivity extends AppCompatActivity {

    private FirebaseAuth mAuth; // Adaugă instanța Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);

        mAuth = FirebaseAuth.getInstance(); // Inițializează Firebase Auth

        TextView tvMessage = findViewById(R.id.textViewTitle);
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

    private void logout() {
        mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
