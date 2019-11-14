package com.example.codepath_assignment_instagramreplica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;

import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG  = "SignupActivity";

    private EditText etUsernameSignup;
    private EditText etPasswordSignup;
    private EditText etEmailSignup;
    private Button btnSubmitSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsernameSignup = findViewById(R.id.etUsernameSignup);
        etPasswordSignup = findViewById(R.id.etPasswordSignup);
        etEmailSignup = findViewById(R.id.etEmailSignup);
        btnSubmitSignup = findViewById(R.id.btnSubmitSignup);

        btnSubmitSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsernameSignup.getText().toString();
                String password = etPasswordSignup.getText().toString();
                String email = etEmailSignup.getText().toString();
                signup(username, email, password);
            }
        });
    }

    private void signup(String username, String password, String email) {
        // Create the ParseUser
        ParseUser user = new ParseUser();

        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        //user.setEmail(email); // TODO fix input

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(SignupActivity.this, "Signup error", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Issue with signup");
                    e.printStackTrace();
                    return;
                }
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
}
