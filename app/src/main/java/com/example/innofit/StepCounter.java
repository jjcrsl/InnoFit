package com.example.innofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class StepCounter extends AppCompatActivity implements SensorEventListener {
    ImageButton dashboard, todolist, nutrition, users;

    private SensorManager sensorManager;
    private TextView steps;
    private ProgressBar progressBar;
    private int totalSteps = 0;
    private int previousTotalSteps = 0;
    private boolean isCounting = true;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        dashboard = (ImageButton) findViewById(R.id.dashboard);
        todolist = (ImageButton) findViewById(R.id.todolist);
        nutrition = (ImageButton) findViewById(R.id.nutrition);

        steps = findViewById(R.id.steps);
        progressBar = findViewById(R.id.progressBar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sharedPreferences = getSharedPreferences("stepCounter", MODE_PRIVATE);
        previousTotalSteps = sharedPreferences.getInt("previousTotalSteps", 0);
        totalSteps = sharedPreferences.getInt("totalSteps", 0) - previousTotalSteps;
        steps.setText(String.valueOf(totalSteps));
        progressBar.setProgress(totalSteps);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard (view);
            }

            private void openDashboard(View view) {
                Intent intent = new Intent(StepCounter.this, Profile.class);
                startActivity(intent);
            }
        });

        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTodolist(view);
            }


            private void openTodolist(View view) {
                Intent intent = new Intent(StepCounter.this, ProgressTrackerActivity.class);
                startActivity(intent);
            }
        });

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNutrition(view);
            }


            private void openNutrition(View view) {
                Intent intent = new Intent(StepCounter.this, NutritionPlan.class);
                startActivity(intent);
            }
        });




    }






    @Override
    protected void onResume() {
        super.onResume();
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            // Show a message to the user that their device does not support step counting
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences.edit().putInt("totalSteps", totalSteps + previousTotalSteps).apply();
        if (isEndOfDay()) {
            sharedPreferences.edit().putInt("previousTotalSteps", totalSteps + previousTotalSteps).apply();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isCounting && event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalSteps++;
            steps.setText(String.valueOf(totalSteps));
            progressBar.setProgress(totalSteps);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private boolean isEndOfDay() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 23;  // Check if it's after 11 PM
    }
}
