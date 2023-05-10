package com.example.innofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ImageButton disc = (ImageButton) findViewById(R.id.discs);
        disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trans (view);
            }

            private void trans(View view) {
                Intent intent = new Intent(Gallery.this, Profile.class);
                startActivity(intent);
            }
        });

        ImageButton gallery = (ImageButton) findViewById(R.id.gallerys);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gallery.this, Gallery.class);
                startActivity(intent);
            }
        });
        ImageButton transac = (ImageButton) findViewById(R.id.transac);
        transac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gallery.this, Transaction.class);
                startActivity(intent);
            }
        });
        ImageButton other = (ImageButton) findViewById(R.id.others);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gallery.this, Others.class);
                startActivity(intent);
            }
        });
    }
}

