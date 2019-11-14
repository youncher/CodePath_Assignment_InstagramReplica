package com.example.codepath_assignment_instagramreplica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

// Login screen for user to enter Username/Password
public class LoginActivity extends AppCompatActivity {

    private static final String TAG  = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username, password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSignupActivity();
            }
        });
    }

    private void login(String username, String password) {

        // Use Parse to check if user was signed in successfully
        ParseUser.logInInBackground(username, password, new LogInCallback() {

            // ParseException e will be null if login succeeeded, not null otherwise
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Issue with login");
                    e.printStackTrace();
                    return;
                }
                // Navigate to new activity if the user has signed in properly
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Log.d(TAG, "Navigating to Main Activity");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        // Clears Login from backstack so when user clicks back, they are not directed back to LoginActivity
        finish();
    }

    private void goSignupActivity() {
        Log.d(TAG, "Navigating to Signup Activity");
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}
