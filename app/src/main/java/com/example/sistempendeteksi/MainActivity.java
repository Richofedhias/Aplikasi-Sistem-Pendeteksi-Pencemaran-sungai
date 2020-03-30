package com.example.sistempendeteksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_history = findViewById(R.id.btn_history);
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void GraphSuhu(View view) {
        Intent intent = new Intent(MainActivity.this, GrafikSuhuActivity.class);
        startActivity(intent);
    }

    public void GraphpH(View view) {
        Intent intent = new Intent(MainActivity.this, GrafikpHActivity.class);
        startActivity(intent);
    }

    public void GraphKekeruhan(View view) {
        Intent intent = new Intent(MainActivity.this, GrafikKekeruhanActivity.class);
        startActivity(intent);
    }
}
