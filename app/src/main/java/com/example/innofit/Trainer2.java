package com.example.innofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Trainer2 extends AppCompatActivity {

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer2);

        back=(ImageView) findViewById(R.id.backbtn2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Trainer2.this, Transaction.class);
                startActivity(intent);
            }
        });


        TextView help2 = (TextView) findViewById(R.id.details2);
        help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(view);
            }
        });
    }


    public void register(View V) {
        AlertDialog.Builder bothError = new AlertDialog.Builder(this);
        bothError.setTitle("HYPERTROPHY TRAINING PROGRAM");
        bothError.setMessage("\n✔4 Week Plan \n✔Step By Step Instructions \n✔Nutrition Tips \n✔Muscle definition and tone \n✔Improved joint stability \n✔Improved flexibility");
        bothError.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bothError.create().show();
    }
}


