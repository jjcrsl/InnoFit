package com.example.innofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Login extends AppCompatActivity {
    private ImageView image;
    private Button button, login;

    EditText emailtxt, passwordtxt;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        
        //hooks
        image= (ImageView) findViewById(R.id.login_back_button);
        button= (Button) findViewById(R.id.create_btn);
        emailtxt= (EditText) findViewById(R.id.emailtxt);
        passwordtxt= (EditText) findViewById(R.id.passwordtxt);
        login= (Button) findViewById(R.id.loginbtn);
        progressDialog= new ProgressDialog(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformLogin();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup();

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }

    private void PerformLogin() {
        String email= emailtxt.getText().toString();
        String password= passwordtxt.getText().toString();

        if (!email.matches(emailPattern)){
            emailtxt.setError("Invalid Email Address");

        }
        else if(password.isEmpty() || password.length()<3){
            passwordtxt.setError("Invalid Password");
        }
        else {

            isUser();




        }
    }

    private void isUser(){
        String userEnteredEmail= emailtxt.getText().toString().trim();
        String userEnteredPassword= passwordtxt.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUser =  reference.orderByChild("email").equalTo(userEnteredEmail);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String passwordFromDB = dataSnapshot.child(userEnteredEmail).child("pass").getValue(String.class);

                    if (!Objects.equals(passwordFromDB, userEnteredPassword)){

                       String nameFromDB = dataSnapshot.child(userEnteredEmail).child("name").getValue(String.class);
                        String userFromDB = dataSnapshot.child(userEnteredEmail).child("user").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredEmail).child("email").getValue(String.class);
                        String heightFromDB = dataSnapshot.child(userEnteredEmail).child("height").getValue(String.class);
                        String weightFromDB = dataSnapshot.child(userEnteredEmail).child("weight").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext(), Profile.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("user", userFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("height", heightFromDB);
                        intent.putExtra("weight", weightFromDB);

                        startActivity(intent);
                    }
                    else{
                        passwordtxt.setError("Wrong Password");
                        passwordtxt.requestFocus();
                    }
                }
                else{
                    emailtxt.setError("No such email exist");
                    emailtxt.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openSignup(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

}