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

public class NutritionStartActivity extends AppCompatActivity {
    ImageButton Normal, Overweight, Underweight, dashboard, todolist, nutrition, users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nutrition_start);

        Normal = (ImageButton) findViewById(R.id.imageButton7);
        Overweight = (ImageButton) findViewById(R.id.imageButton2);
        Underweight = (ImageButton) findViewById(R.id.imageButton6);
        dashboard = (ImageButton) findViewById(R.id.dashboard);
        todolist = (ImageButton) findViewById(R.id.todolist);
        nutrition = (ImageButton) findViewById(R.id.nutrition);

        Normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openNormal (view);
            }

            private void openNormal(View view) {
                Intent intent = new Intent(NutritionStartActivity.this, NutritionPlan.class);
                startActivity(intent);
            }
        });
        Overweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openOverweight (view);
            }

            private void openOverweight(View view) {
                Intent intent = new Intent(NutritionStartActivity.this, NutritionPlan2Activity.class);
                startActivity(intent);
            }
        });
        Underweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openUnderweight (view);
            }

            private void openUnderweight(View view) {
                Intent intent = new Intent(NutritionStartActivity.this, NutritionPlan3Activity.class);
                startActivity(intent);
            }
        });


        // start of intents in navbar
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard (view);
            }

            private void openDashboard(View view) {
                Intent intent = new Intent(NutritionStartActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTodolist(view);
            }


            private void openTodolist(View view) {
                Intent intent = new Intent(NutritionStartActivity.this, ProgressTrackerActivity.class);
                startActivity(intent);
            }
        });

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNutrition(view);
            }


            private void openNutrition(View view) {
                Intent intent = new Intent(NutritionStartActivity.this, NutritionStartActivity.class);
                startActivity(intent);
            }
        });
    }
}