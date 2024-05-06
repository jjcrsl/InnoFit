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
    TextView nameGet, heightGet, weightGet, coreExercise, upperExercise, lowerExercise, stepsGet;
    ImageButton dashboard, todolist, nutrition, users;
    Button signout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dashboard = (ImageButton) findViewById(R.id.dashboard);
        todolist = (ImageButton) findViewById(R.id.todolist);
        nutrition = (ImageButton) findViewById(R.id.nutrition);



        // start of intents in navbar
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard (view);
            }

            private void openDashboard(View view) {
                Intent intent = new Intent(Profile.this, StepCounter.class);
                startActivity(intent);
            }
        });

        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTodolist(view);
            }


        private void openTodolist(View view) {
            Intent intent = new Intent(Profile.this, ProgressTrackerActivity.class);
            startActivity(intent);
        }
    });

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNutrition(view);
            }


            private void openNutrition(View view) {
                Intent intent = new Intent(Profile.this, NutritionPlan.class);
                startActivity(intent);
            }
        });







        nameGet = findViewById(R.id.name);
        heightGet = findViewById(R.id.height);
        weightGet = findViewById(R.id.weight);

        coreExercise = findViewById(R.id.coreBody);
        upperExercise = findViewById(R.id.upperBody);
        lowerExercise = findViewById(R.id.lowerBody);
/*        signout = findViewById(R.id.signout_btn);*/

        //show all the data

        showAllUserData();


        //exercise
        coreExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNextPage(view);
            }
            private void openNextPage(View view) {
                Intent intent = new Intent(Profile.this, Core_exercise1.class);
                startActivity(intent);
            }
        });
        upperExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNextPage(view);
            }
            private void openNextPage(View view) {
                Intent intent = new Intent(Profile.this, low_exercise1.class);
                startActivity(intent);
            }
        });
        lowerExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNextPage(view);
            }
            private void openNextPage(View view) {
                Intent intent = new Intent(Profile.this, Lower_exercise1.class);
                startActivity(intent);
            }
        });

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