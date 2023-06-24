package com.krish.krishbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView getStartedTextView = findViewById(R.id.btnGetStarted);
        getStartedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    public void logoutUser(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this,"Signed out Successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(WelcomeActivity.this, Login.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        //To  Disable the back button i kept it empty
    }
}
