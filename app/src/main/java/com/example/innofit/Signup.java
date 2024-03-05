package com.example.innofit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {


     Button login, signup;
     private ImageView image;
     EditText nametxt, usertxt, emailtxt, passtxt, heighttxt, weighttxt;
     String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


     
     FirebaseDatabase rootNode;
     DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //hooks for sign_up
        image= findViewById(R.id.signup_back_button);
        signup = (Button) findViewById(R.id.signup_btn);
        login = (Button) findViewById(R.id.login_btn);
        nametxt = (EditText) findViewById(R.id.nametxt);
        usertxt = (EditText) findViewById(R.id.usertxt);
        emailtxt = (EditText) findViewById(R.id.emailtxt);
        heighttxt = (EditText) findViewById(R.id.heighttxt);
        weighttxt = (EditText) findViewById(R.id.weighttxt);
        passtxt = (EditText) findViewById(R.id.passtxt);


        FirebaseApp.initializeApp(this);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, MainActivity.class);
                startActivity(intent);
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //get all the values
                String name= nametxt.getText().toString();
                String user= usertxt.getText().toString();
                String email= emailtxt.getText().toString();
                String height= heighttxt.getText().toString();
                String weight= weighttxt.getText().toString();
                String pass= passtxt.getText().toString();



                userHelperClass helperClass= new userHelperClass(name, user, email, height, weight, pass);
                reference.child(name).setValue(helperClass);

                Toast.makeText(Signup.this, "Account Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoLogin();

            }
        });

    }


    public void gotoLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


        }




