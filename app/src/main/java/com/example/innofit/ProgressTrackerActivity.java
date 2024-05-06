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
    private SharedPreferences sharedPreferences;
    private Set<String> noteSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_tracker);

        circularTextView = findViewById(R.id.circular_textview);
        ImageButton dashboard = findViewById(R.id.dashboard);
        ImageButton todolist = findViewById(R.id.todolist);
        ImageButton nutrition = findViewById(R.id.nutrition);

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
                launcher.launch(intent); // Launch the activity and handle the result
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard();
            }
        });

        todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTodolist();
            }
        });

        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNutrition();
            }
        });

        // Load existing notes if available
        if (!noteSet.isEmpty()) {
            for (String note : noteSet) {
                addNewTextView(note);
            }
        }
    }

    private void openDashboard() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    private void openTodolist() {
        Intent intent = new Intent(this, ProgressTrackerActivity.class);
        intent.putExtra("clearNotes", true); // Indicate to clear notes
        startActivity(intent);
        finish(); // Finish the current activity to prevent creating a new instance
    }

    private void openNutrition() {
        Intent intent = new Intent(this, NutritionStartActivity.class);
        startActivity(intent);
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

            newTextView.setTextSize(20);

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

    private void clearNotes() {
        noteSet.clear();
        saveNotes();
    }
}
