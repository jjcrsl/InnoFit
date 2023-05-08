package com.example.innofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.login_btn);
        button2 = (Button) findViewById(R.id.signup_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });


    }
    public void openSignup(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
    public void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


}