package com.example.innofit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class ProgressTrackerActivity extends AppCompatActivity {

    private TextView circularTextView;
    private CheckBox checkBox;
    private SharedPreferences sharedPreferences;
    ImageButton dashboard, todolist, nutrition, users;
    private Set<String> noteSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_tracker);

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
                Intent intent = new Intent(ProgressTrackerActivity.this, StepCounter.class);
                startActivity(intent);
            }
        });

        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTodolist(view);
            }


            private void openTodolist(View view) {
                Intent intent = new Intent(ProgressTrackerActivity.this, ProgressTrackerActivity.class);
                startActivity(intent);
            }
        });

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNutrition(view);
            }


            private void openNutrition(View view) {
                Intent intent = new Intent(ProgressTrackerActivity.this, NutritionPlan.class);
                startActivity(intent);
            }
        });

        circularTextView = findViewById(R.id.circular_textview);

        sharedPreferences = getSharedPreferences("notes", MODE_PRIVATE);
        noteSet = sharedPreferences.getStringSet("notes_set", new HashSet<>());

        // Register for activity result
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String note = data.getStringExtra("note");
                            addNewTextView(note);
                            noteSet.add(note);
                            saveNotes();
                        }
                    }
                }
        );

        circularTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProgressTrackerActivity.this, AddTaskActivity.class);
                launcher.launch(intent);
            }
        });

        circularTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                checkBox.setVisibility(View.VISIBLE);
                return true;
            }
        });

        noteSet.clear();

        if (!noteSet.isEmpty()) {
            for (String note : noteSet) {
                addNewTextView(note);
            }
        }
    }

    private void addNewTextView(String note) {
        if (note != null && !note.isEmpty()) {
            LinearLayout linearLayout = findViewById(R.id.linearLayout);

            TextView newTextView = new TextView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    convertDpToPixel(100)
            );
            params.setMargins(
                    convertDpToPixel(8), // left margin
                    convertDpToPixel(8), // top margin
                    convertDpToPixel(8), // right margin
                    convertDpToPixel(8)  // bottom margin
            );
            newTextView.setLayoutParams(params);

            newTextView.setBackgroundResource(R.drawable.note_tracker);
            newTextView.setPadding(16, 16, 16, 16);
            newTextView.setText(note);

            newTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    linearLayout.removeView(newTextView);
                    noteSet.remove(note);
                    saveNotes();
                    return true;
                }
            });

            linearLayout.addView(newTextView);
        }
    }

    private int convertDpToPixel(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void saveNotes() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("notes_set", noteSet);
        editor.apply();
    }
}
