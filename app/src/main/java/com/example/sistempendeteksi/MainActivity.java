package com.example.sistempendeteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btn_history;
    private TextView suhu,kekeruhan,pH, index, keterangan;
    String nameSuhu, namepH, nameTurbidity;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_history = findViewById(R.id.btn_history);
        suhu = findViewById(R.id.tV_nilaiSuhu);
        kekeruhan = findViewById(R.id.tV_nilaiKeruh);
        pH = findViewById(R.id.tV_nilaipH);
        index = findViewById(R.id.tV_index);
        keterangan = findViewById(R.id.tV_keterangan);
        layout = findViewById(R.id.layout);
        ambilData();
        createNotif();

        Intent intent = new Intent(MainActivity.this,ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long tenSecond = 1000 * 10;

        alarmManager.set(AlarmManager.RTC_WAKEUP,tenSecond,pendingIntent);

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ambilData(){
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sensor");
        Query query = reference.child("dht").orderByKey().limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    nameSuhu = "" + ds.child("temperature").getValue();
                    namepH = "" + ds.child("pH").getValue();
                    nameTurbidity = "" + ds.child("turbidity").getValue();
                    Log.d("suhu",nameSuhu);
                    suhu.setText(nameSuhu + "\u2103");
                    pH.setText(namepH);
                    kekeruhan.setText(nameTurbidity);
                }

                Double Suhu = Double.parseDouble(nameSuhu);
                Double pHH = Double.parseDouble(namepH);
                Double keruh = Double.parseDouble(nameTurbidity);
                if (Suhu <= 30 && Suhu >= 25.01  && pHH <= 7 && pHH >= 5.01 && keruh <= 500 && keruh >= 300.01) {
                    index.setText("Tidak Tercemar");
                    layout.setBackgroundResource(R.drawable.bg_tidak_tercemar);
                    keterangan.setText("Air dapat digunakan untuk air minum, menyiram tanaman dan mengairi sawah");
                } else if ((Suhu <= 30 && Suhu >= 25.01)  && (pHH <= 10 && pHH >= 7.01) && (keruh <= 500 && keruh >= 300.01)){
                    index.setText("Hampir Tidak Tercemar");
                    layout.setBackgroundResource(R.drawable.bg_hampir_tercemar);
                    keterangan.setText("Air dapat digunakan untuk menyiram tanaman dan mengairi sawah");
                } else if ((Suhu >= 30.01) && (pHH <= 7 && pHH >= 5.01) && (keruh <= 500 && keruh >= 300.01)){
                    index.setText("Hampir Tidak Tercemar");
                    layout.setBackgroundResource(R.drawable.bg_hampir_tercemar);
                    keterangan.setText("Air dapat digunakan untuk menyiram tanaman dan mengairi sawah");
                } else if ((Suhu <= 30 && Suhu >= 25.01) && (pHH <= 7 && pHH >= 5.01) && (keruh <= 1000 && keruh >= 500.01)){
                    index.setText("Hampir Tidak Tercemar");
                    layout.setBackgroundResource(R.drawable.bg_hampir_tercemar);
                    keterangan.setText("Air dapat digunakan untuk menyiram tanaman dan mengairi sawah");
                } else if ((Suhu >= 30.01) && (pHH >= 10) && (keruh >= 1000.01)){
                    index.setText("Sudah Mulai Tercemar");
                    layout.setBackgroundResource(R.drawable.bg_hampir_tercemar);
                    keterangan.setText("Air dapat digunakan untuk menyiram tanaman dan mengairi sawah");
                } else {
                    index.setText("Tercemar");
                    layout.setBackgroundResource(R.drawable.bg_tercemar);
                    keterangan.setText("Air tidak dapat digunakan untuk air minum, menyiram tanaman dan mengairi sawah");
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
