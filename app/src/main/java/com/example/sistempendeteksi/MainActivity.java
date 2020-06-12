package com.example.sistempendeteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn_history;
    private TextView suhu,kekeruhan,pH, index, keterangan;
    String nameSuhu, namepH, nameTurbidity;
    LinearLayout layout;
    private Switch setingNotif;
    private TimePickerDialog timePickerDialog;
    TextView txt_nama;
    Button waktu;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    Calendar myCalendar = Calendar.getInstance();
    Calendar calSet = (Calendar) myCalendar.clone();
    String nama;
    int hour, minute, hour1, minute1;
    private Double dPh, dKekeruhan;
    private int intSuhu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_history = findViewById(R.id.btn_history);
        suhu = findViewById(R.id.tV_nilaiSuhu);
        kekeruhan = findViewById(R.id.tV_nilaiKeruh);
        pH = findViewById(R.id.tV_nilaipH);
        index = findViewById(R.id.nilaiCemar);
        keterangan = findViewById(R.id.tV_penjelasan);
        setingNotif = findViewById(R.id.notif);

        layout = findViewById(R.id.layout);
        ambilData();


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

        setingNotif.setChecked(false);
        setingNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //keluar Dialog Jam
                    DialogForm();


                }
                else{

                }
            }
        });
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



//                    try{
//                        int hitungSuhu = (int) ds.child("temperature").getValue();
//                        int hitungpH = (int) ds.child("pH").getValue();
//                        int hitungTurbidity = (int) ds.child("turbidity").getValue();
//
//                        if(hitungSuhu > 20 && hitungpH > 5 && hitungTurbidity > 0 ){
//                            kategori.setText("Bersih");
//                            penjelasan.setText("Testing");
//                        }
//                    } catch(NumberFormatException ex){ // handle your exception
//                        Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
//                    }


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




    private void show(){

        hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        minute = myCalendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                //ehour.setText(selectedHour + ":" + selectedMinute);

                hour1 = selectedHour;
                minute1 = selectedMinute;

                calSet.set(Calendar.HOUR_OF_DAY, hour1);
                calSet.set(Calendar.MINUTE, minute1);
                txt_nama.setText(selectedHour +":"+ selectedMinute);



                if(calSet.compareTo(myCalendar) <= 0){
                    //Today Set time passed, count to tomorrow
                    calSet.add(Calendar.DATE, 1);
                }
            }
        }, hour, minute, true);//false bcz not 24 hour time format
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }

    private void DialogForm() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_data, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Atur Notifikasi");

        txt_nama    =  dialogView.findViewById(R.id.txt_nama);
        waktu = dialogView.findViewById(R.id.btn_wkt);

        waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });




        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                 nama    = txt_nama.getText().toString();

                Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + hour1 + minute1, pendingIntent);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), pendingIntent);
                //Toast.makeText(this, "Alarm set in " + second + " mili seconds", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Alarm set at " + hour1 + ":" + minute1 + " hours", Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }




}
