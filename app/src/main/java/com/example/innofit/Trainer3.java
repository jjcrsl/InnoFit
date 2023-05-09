package com.example.innofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Trainer3 extends AppCompatActivity {

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer3);

        back=(ImageView) findViewById(R.id.backbtn3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Trainer3.this, Transaction.class);
                startActivity(intent);
            }
        });


        TextView help3 = (TextView) findViewById(R.id.details3);
        help3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(view);
            }
        });
    }


    public void register(View V) {
        AlertDialog.Builder bothError = new AlertDialog.Builder(this);
        bothError.setTitle("CARDIO TRAINING PROGRAM");
        bothError.setMessage("\n✔4 Week Plan \n✔Step By Step Instructions \n✔Nutrition Tips \n✔Improved lung function and capacity \n✔Improved mood and stress relief \n✔Enhanced athletic performance");
        bothError.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bothError.create().show();
    }
}


