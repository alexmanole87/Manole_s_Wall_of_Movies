package com.example.manoleswallofmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inițializează Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(v -> {
            String email = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            loginUser(email, password);
        });

        buttonSignUp.setOnClickListener(v -> {
            // Deschide SignUpActivity pentru înregistrare
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Logare reușită
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Autentificare reușită.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, WallActivity.class);
                        startActivity(intent);
                    } else {
                        // Eșec la logare
                        Toast.makeText(MainActivity.this, "Autentificare eșuată.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Verifică dacă utilizatorul este deja logat (non-null) și actualizează UI-ul corespunzător.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Navighează la WallActivity sau altă activitate dacă utilizatorul este deja logat
            Intent intent = new Intent(MainActivity.this, WallActivity.class);
            startActivity(intent);
        }
    }
}
