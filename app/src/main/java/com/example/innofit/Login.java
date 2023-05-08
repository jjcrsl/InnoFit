package com.example.innofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Login extends AppCompatActivity {
    private ImageView image;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        image= (ImageView) findViewById(R.id.login_back_button);
        button= (Button) findViewById(R.id.create_btn);

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
    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openSignup(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

}