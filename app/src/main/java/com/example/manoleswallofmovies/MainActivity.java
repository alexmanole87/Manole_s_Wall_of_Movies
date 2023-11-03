package com.example.manoleswallofmovies;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tvMessage = findViewById(R.id.tvMessage);
        Button bam = findViewById(R.id.buttonAddMovie);

        bam.setOnClickListener(view -> {
            Intent intent_add = new Intent(MainActivity.this, AddMovieActivity.class);
            startActivity(intent_add);
        });

        bam.setOnLongClickListener(view -> {
            Toast.makeText(MainActivity.this, R.string.txt_help_add, Toast.LENGTH_LONG).show();

            return true;
        });
        Log.i("A pornit", "Aplicatia a pornit");

    }






    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Pauza", "Aplicatia e pe pauza");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("A repornit", "A repornit");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Bye bye", "Aplicatia a fost inchisa cu succes");
    }
}