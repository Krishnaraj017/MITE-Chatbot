
package com.krish.krishbot;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    Button signup;
    EditText fullName,email,password;
    TextView signin;

    FirebaseAuth auth;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        signup = (Button) findViewById(R.id.button);
        fullName = (EditText) findViewById(R.id.editTextText);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        signin = (TextView) findViewById(R.id.textView2);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();

            }
        });

    }

    private void createUser() {
        String  userName= fullName.getText().toString();
        String userEmail= email.getText().toString();
        String userPassword= password.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Name is empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Email is empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Password is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length() < 6){
            Toast.makeText(this,"Password length must be atleast 8 characters",Toast.LENGTH_SHORT).show();
            return;
        }

        //create User

        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            UserModel userModel = new UserModel(userName,userEmail,userPassword);
                            String id = task.getResult().getUser().getUid();
                            db.getReference("Users").child(id).setValue(userModel);
                            Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Signup.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}