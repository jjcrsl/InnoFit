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

public class Login extends AppCompatActivity {
    private ImageView image;
    private Button button, login;
    EditText names, users, emails, passwords;


    EditText nametxt, usertxt, emailtxt, passtxt, passwordtxt;
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



            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                        storedatas();
                    }
                }
            });
        }
    }

    private void storedatas() {



        String names = nametxt.getText().toString().trim();
        String users = usertxt.getText().toString().trim();
        String emails = emailtxt.getText().toString().trim();
        String passwords = passtxt.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");

        Query checkUser = reference.orderByChild("username").equalTo(users);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    String passwordsFromDB = snapshot.child(users).child("passwords").getValue(String.class);

                    if (passwordsFromDB.equals(users)) {

                        String namesFromDB = snapshot.child(users).child("names").getValue(String.class);
                        String emailsFromDB = snapshot.child(users).child("emails").getValue(String.class);
                        String usersFromDB = snapshot.child(users).child("users").getValue(String.class);

                       /* Intent intent = new Intent(getApplicationContext(), Profile.class);
                        intent.putExtra("names",namesFromDB);
                        intent.putExtra("users",usersFromDB);
                        intent.putExtra("emails",emailsFromDB);
                        intent.putExtra("passwords",passwordsFromDB);
                        startActivity(intent);*/

                    }
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
        storedatas();
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