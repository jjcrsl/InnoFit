package com.example.innofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class lower_exercise4 extends AppCompatActivity {
    ImageView next, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lower_exercise4);
        //start
        next = findViewById(R.id.nextBtn);
        back = findViewById(R.id.backBtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNextPage(view);
            }


            private void openNextPage(View view) {
                Intent intent = new Intent(lower_exercise4.this, Lower_exercise5.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBackPage(view);
            }


            private void openBackPage(View view) {
                Intent intent = new Intent(lower_exercise4.this, lower_exercise3.class);
                startActivity(intent);
            }

        });
        //end

    }
}