package com.example.innofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Trainer4 extends AppCompatActivity {

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer4);

        back=(ImageView) findViewById(R.id.backbtn4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Trainer4.this, Transaction.class);
                startActivity(intent);
            }
        });


        TextView help4 = (TextView) findViewById(R.id.details4);
        help4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(view);
            }
        });
    }


    public void register(View V) {
        AlertDialog.Builder bothError = new AlertDialog.Builder(this);
        bothError.setTitle("FUNCTIONAL TRAINING PROGRAM");
        bothError.setMessage("\n✔4 Week Plan \n✔Step By Step Instructions \n✔Nutrition Tips \n✔Improved movement quality and efficiency \n✔Improved balance and stability \n✔Improved coordination and agility");
        bothError.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bothError.create().show();
    }
}


