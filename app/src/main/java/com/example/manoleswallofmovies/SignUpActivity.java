package com.example.manoleswallofmovies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextSeenMoviesNo;
    private EditText editTextName;
    private Spinner spinnerYearOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextSeenMoviesNo = findViewById(R.id.editTextSeenMoviesNo);
        editTextName = findViewById(R.id.editTextName);
        spinnerYearOfBirth = findViewById(R.id.spinnerYearOfBirth);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        setupYearOfBirthSpinner();

        buttonSignUp.setOnClickListener(v -> attemptSignUp());
    }

    private void setupYearOfBirthSpinner() {
        ArrayList<String> years = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear; i >= 1900; i--) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYearOfBirth.setAdapter(adapter);
    }

    private boolean isUsernameExist(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_file), MODE_PRIVATE);
        // Aici presupunem că toate username-urile sunt salvate cu un prefix sau într-o structură anume.
        // De exemplu, toate username-urile sunt salvate ca "user_username".
        String userKey = "user_" + username;
        return sharedPreferences.contains(userKey);
    }

    private void attemptSignUp() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String seenMoviesNo = editTextSeenMoviesNo.getText().toString();
        String name = editTextName.getText().toString();
        String yearOfBirth = spinnerYearOfBirth.getSelectedItem().toString();

        if (!username.isEmpty() && !password.isEmpty()) {
            saveUserDetails(username, password, seenMoviesNo, name, yearOfBirth);
            Toast.makeText(this, R.string.auth_login, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, R.string.sign_up_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserDetails(String username, String password, String seenMoviesNo, String name, String yearOfBirth) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_file), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.pref_username), username);
        editor.putString(getString(R.string.pref_password), password);
        editor.putString(getString(R.string.pref_seen_movies_no), seenMoviesNo);
        editor.putString(getString(R.string.pref_name), name);
        editor.putString(getString(R.string.pref_year_of_birth), yearOfBirth);
        editor.apply();
    }
}
