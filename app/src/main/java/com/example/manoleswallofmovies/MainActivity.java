package com.example.manoleswallofmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            if (validateLogin(username, password)) {

                Toast.makeText(MainActivity.this, R.string.auth_succes, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, WallActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this, R.string.auth_fail, Toast.LENGTH_SHORT).show();
            }
        });

        buttonSignUp.setOnClickListener(v -> {
            // Deschide SignUpActivity pentru înregistrare
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private boolean validateLogin(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_file), Context.MODE_PRIVATE);
        String registeredUsername = sharedPreferences.getString(getString(R.string.pref_username), "");
        String registeredPassword = sharedPreferences.getString(getString(R.string.pref_password), "");
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }
}
