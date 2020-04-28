package com.example.sistempendeteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn_history;
    private TextView suhu,kekeruhan,pH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_history = findViewById(R.id.btn_history);
        suhu = findViewById(R.id.tV_suhu);
        kekeruhan = findViewById(R.id.tV_keruh);
        pH = findViewById(R.id.tV_pH);
        ambilData();

//        Query lastQuery = reference.child("dht").orderByKey().limitToLast(1);
//
//        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//
//                    Log.d("suhu",dataSnapshot.child("temperature").getValue().toString());
//                    Toast.makeText(MainActivity.this, "NJING", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this, "NYET", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

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

    private void ambilData(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sensor").child("dht");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()== true){
                    Toast.makeText(MainActivity.this, "Ada data", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Tidak ADa data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
