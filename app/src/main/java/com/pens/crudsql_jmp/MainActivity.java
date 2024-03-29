package com.pens.crudsql_jmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTambah;
    Button btnTampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTambah = findViewById(R.id.btnTambah);
        btnTampil = findViewById(R.id.btnTampil);

        btnTambah.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, TambahActivity.class);
            startActivity(i);

        });

        btnTampil.setOnClickListener(view -> {

            Intent i = new Intent(MainActivity.this, TampilActivity.class);
            startActivity(i);
        });
    }
}