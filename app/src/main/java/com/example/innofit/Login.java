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

public class Login extends AppCompatActivity {
    private ImageView image;
    private Button button, login;


    EditText emailtxt, passwordtxt;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        image= (ImageView) findViewById(R.id.login_back_button);
        button= (Button) findViewById(R.id.create_btn);
        emailtxt= (EditText) findViewById(R.id.emailtxt);
        passwordtxt= (EditText) findViewById(R.id.passwordtxt);
        login= (Button) findViewById(R.id.loginbtn);
        progressDialog= new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

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
        else if(password.isEmpty() || password.length()<8){
            passwordtxt.setError("Invalid Password");
        }
        else {
            progressDialog.setMessage("Loging in");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(Login.this, "Logged In", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
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