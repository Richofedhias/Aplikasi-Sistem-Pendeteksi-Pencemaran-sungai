package com.example.sistempendeteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
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
        suhu = findViewById(R.id.tV_nilaiSuhu);
        kekeruhan = findViewById(R.id.tV_nilaiKeruh);
        pH = findViewById(R.id.tV_nilaipH);
        ambilData();
        createNotif();

        Intent intent = new Intent(MainActivity.this,ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long tenSecond = 1000 * 10;

        alarmManager.set(AlarmManager.RTC_WAKEUP,tenSecond,pendingIntent);

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
//        Intent intent = new Intent(MainActivity.this, GrafikSuhuActivity.class);
//        startActivity(intent);
    }

    public void GraphpH(View view) {
//        Intent intent = new Intent(MainActivity.this, GrafikpHActivity.class);
//        startActivity(intent);
    }

    public void GraphKekeruhan(View view) {
//        Intent intent = new Intent(MainActivity.this, GrafikKekeruhanActivity.class);
//        startActivity(intent);
    }

    private void ambilData(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sensor");
        Query query = reference.child("dht").orderByKey().limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String nameSuhu = "" + ds.child("temperature").getValue();
                    String namepH = "" + ds.child("pH").getValue();
                    String nameTurbidity = "" + ds.child("turbidity").getValue();
                    Log.d("suhu",nameSuhu);
                    suhu.setText(nameSuhu);
                    pH.setText(namepH);
                    kekeruhan.setText(nameTurbidity);
                }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void createNotif(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name ="Poltec";
            String description = "Deskripsi";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notif",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
