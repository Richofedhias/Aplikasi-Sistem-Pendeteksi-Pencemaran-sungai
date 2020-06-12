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
import android.graphics.Color;
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
    private TextView suhu,kekeruhan,pH,kategori,penjelasan;
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
        kategori = findViewById(R.id.nilaiCemar);
        penjelasan = findViewById(R.id.tV_penjelasan);
        setingNotif = findViewById(R.id.notif);
        ambilData();

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

    private void KualitasAir(String suhu, String ph, String kekeruhan) {
        dKekeruhan = Double.valueOf(kekeruhan);
        dPh = Double.valueOf(ph);
        intSuhu = Integer.valueOf(suhu);
        int value = 10;
        if (dKekeruhan>value && intSuhu>value && dPh >value ){
            kategori.setText("Value lebih 10");
        }else {
            kategori.setText("Value kurang 10");
        }

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
                    KualitasAir(nameSuhu, namepH, nameTurbidity);



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
