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

public class Core_exercise3 extends AppCompatActivity {
    ImageView next, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_core_exercise3);
        //start
        next = findViewById(R.id.nextBtn);
        back = findViewById(R.id.backBtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNextPage(view);
            }


            private void openNextPage(View view) {
                Intent intent = new Intent(Core_exercise3.this, core_exercise4.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBackPage(view);
            }


            private void openBackPage(View view) {
                Intent intent = new Intent(Core_exercise3.this, Core_exercise2.class);
                startActivity(intent);
            }

        });
        //end

    }
}