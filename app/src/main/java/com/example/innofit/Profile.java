package com.example.innofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    TextView nameGet, heightGet, weightGet, stepsGet;
    Button signout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameGet = findViewById(R.id.name);
        heightGet = findViewById(R.id.height);
        weightGet = findViewById(R.id.weight);
/*        signout = findViewById(R.id.signout_btn);*/

        //show all the data

        showAllUserData();
    }

    private void showAllUserData(){
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_height = intent.getStringExtra("height");
        String user_weight = intent.getStringExtra("weight");

        nameGet.setText(user_name);
        heightGet.setText(user_height);
        weightGet.setText(user_weight);
    }
}