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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Login extends AppCompatActivity {
 ImageView image;
     Button button, login;

    EditText usertxt, passwordtxt;
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
        usertxt= (EditText) findViewById(R.id.usertxt);
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
        String user= usertxt.getText().toString();
        String password= passwordtxt.getText().toString();

        if (user.length()<3){
            usertxt.setError("Invalid User");

        }
        else if(password.isEmpty() || password.length()<3){
            passwordtxt.setError("Invalid Password");
        }
        else {

            isUser();

        }
    }

    private void isUser(){
        final String userEnteredUser= usertxt.getText().toString().trim();
        final String userEnteredPassword= passwordtxt.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUser =  reference.orderByChild("user").equalTo(userEnteredUser);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    usertxt.setError(null);


                    String passwordFromDB = dataSnapshot.child(userEnteredUser).child("pass").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){
                        usertxt.setError(null);
                        Intent intent = new Intent(Login.this, Profile.class);
                        startActivity(intent);


/*                      String nameFromDB = dataSnapshot.child(userEnteredUser).child("name").getValue(String.class);
                        String userFromDB = dataSnapshot.child(userEnteredUser).child("user").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUser).child("email").getValue(String.class);
                        String heightFromDB = dataSnapshot.child(userEnteredUser).child("height").getValue(String.class);
                        String weightFromDB = dataSnapshot.child(userEnteredUser).child("weight").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext(), Profile.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("user", userFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("height", heightFromDB);
                        intent.putExtra("weight", weightFromDB);

                        startActivity(intent);*/
                    }
                    else{
                        passwordtxt.setError("Wrong Password");
                        passwordtxt.requestFocus();
                    }
                }
                else{
                    usertxt.setError("No such user exist");
                    usertxt.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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