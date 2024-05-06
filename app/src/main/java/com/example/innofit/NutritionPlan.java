package com.example.innofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class NutritionPlan extends AppCompatActivity {

    ImageButton dashboard, todolist, nutrition, users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nutrition_plan);

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
            Intent intent = new Intent(NutritionPlan.this, Profile.class);
            startActivity(intent);
        }
    });

        todolist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openTodolist(view);
        }


        private void openTodolist(View view) {
            Intent intent = new Intent(NutritionPlan.this, ProgressTrackerActivity.class);
            startActivity(intent);
        }
    });

        nutrition.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openNutrition(view);
        }


        private void openNutrition(View view) {
            Intent intent = new Intent(NutritionPlan.this, NutritionStartActivity.class);
            startActivity(intent);
        }
    });
    }


    }


