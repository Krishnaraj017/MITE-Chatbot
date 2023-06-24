package com.krish.krishbot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button signIn;
    EditText email,password;
    TextView signup;
    FirebaseAuth auth;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        auth = FirebaseAuth.getInstance();
        signIn = findViewById(R.id.button);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        signup = findViewById(R.id.textView3);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String UserEmail = email.getText().toString();
        String UserPassword = password.getText().toString();
        if (TextUtils.isEmpty(UserEmail)) {
            Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(UserPassword)) {
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (UserPassword.length() < 8) {
            Toast.makeText(this, "Password must contain at least 8 letters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Login
        auth.signInWithEmailAndPassword(UserEmail, UserPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                            // Navigate to WelcomeActivity
                            Intent intent = new Intent(Login.this, WelcomeActivity.class);
                            startActivity(intent);
                            finish(); // Optional: Finish the Login activity to prevent navigating back to it
                        } else {
                            Toast.makeText(Login.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
