package com.example.manoleswallofmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("A pornit", "Aplicatia a pornit");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()== R.id.menu_opt_1){
           Toast.makeText(this, "Optiunea 1 Deșlectata", Toast.LENGTH_SHORT).show();
       }
       else if (item.getItemId()== R.id.menu_opt_2){
           Toast.makeText(this, "Optinuea a doua selectatda", Toast.LENGTH_LONG).show();
        }
       return true;
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