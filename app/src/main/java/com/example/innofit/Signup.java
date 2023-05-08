package com.example.innofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
     private Button button, signup;
     EditText nametxt, usertxt, emailtxt, passtxt, cpasstxt;
     String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
     ProgressDialog progressDialog;
     FirebaseAuth mAuth;
     FirebaseUser mUser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = (Button) findViewById(R.id.signup_btn);
        button = (Button) findViewById(R.id.loginn_btn);
        nametxt = (EditText) findViewById(R.id.nametxt);
        usertxt = (EditText) findViewById(R.id.usertxt);
        emailtxt = (EditText) findViewById(R.id.emailtxt);
        passtxt = (EditText) findViewById(R.id.passtxt);
        cpasstxt = (EditText) findViewById(R.id.cpasstxt);
        progressDialog= new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Authentication();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
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

    public void Authentication(){

        String email= emailtxt.getText().toString();
        String password= passtxt.getText().toString();
        String conpassword= cpasstxt.getText().toString();
        String name= nametxt.getText().toString();
        String user= usertxt.getText().toString();


        if (!email.matches(emailPattern)){
            emailtxt.setError("Enter a proper Email Address");

        }
        else if(user.isEmpty()){
            usertxt.setError("Please input a username");
        }
        else if(name.isEmpty()){
            nametxt.setError("Please input your name");
        }
        else if(password.isEmpty() || password.length()<8){
            passtxt.setError("Must be 8 characters and above");
        }
        else if (!password.equals(conpassword)){
            cpasstxt.setError("Password did not match");
        }
        else {
                progressDialog.setMessage("Signing up");
                progressDialog.setTitle("Sign up");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            sendUserToNextActivity();
                            Toast.makeText(Signup.this, "Account Created", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(Signup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }

    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}