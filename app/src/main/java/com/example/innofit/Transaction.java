package com.example.innofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Transaction extends AppCompatActivity {

    private Button trainer1, trainer2, trainer3, trainer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        trainer1 = (Button) findViewById(R.id.trainer1btn);
        trainer2 = (Button) findViewById(R.id.trainer2btn);
        trainer3 = (Button) findViewById(R.id.trainer3btn);
        trainer4 = (Button) findViewById(R.id.trainer4btn);


        trainer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transaction.this, Trainer1.class);
                startActivity(intent);

            }
        });

        trainer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transaction.this, Trainer2.class);
                startActivity(intent);

            }
        });

        trainer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transaction.this, Trainer3.class);
                startActivity(intent);

            }
        });

        trainer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transaction.this, Trainer4.class);
                startActivity(intent);

            }
        });
        ImageButton disc = (ImageButton) findViewById(R.id.discs);

        disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trans (view);
            }

            private void trans(View view) {
                Intent intent = new Intent(Transaction.this, Home.class);
                startActivity(intent);
            }
        });

        ImageButton gallery = (ImageButton) findViewById(R.id.gallerys);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transaction.this, Gallery.class);
                startActivity(intent);
            }
        });
        ImageButton transac = (ImageButton) findViewById(R.id.transac);
        transac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transaction.this, Transaction.class);
                startActivity(intent);
            }
        });
        ImageButton other = (ImageButton) findViewById(R.id.others);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transaction.this, Others.class);
                startActivity(intent);
            }
        });

    }
}

