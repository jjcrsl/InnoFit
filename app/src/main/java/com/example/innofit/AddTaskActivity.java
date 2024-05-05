package com.example.innofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        Button confirmButton = findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText taskNameEditText = findViewById(R.id.task_title_edittext);
                String taskName = taskNameEditText.getText().toString();
                EditText taskDescEditText = findViewById(R.id.task_desc_edittext);
                String taskDesc = taskDescEditText.getText().toString();


                Intent resultIntent = new Intent();
                resultIntent.putExtra("note", "Title : " + taskName + "\n" + "Description : " + taskDesc);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        Button cancelButton = findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
