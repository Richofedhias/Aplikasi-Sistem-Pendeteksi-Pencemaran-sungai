package com.example.sistempendeteksi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sensor");
                Query query = reference.child("dht").orderByKey().limitToLast(1);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String nameSuhu = "" + ds.child("temperature").getValue();
                            String namepH = "" + ds.child("pH").getValue();
                            String nameTurbidity = "" + ds.child("turbidity").getValue();

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notif").
                                    setSmallIcon(R.drawable.ic_launcher_background)
                                    .setContentTitle("Update terbaru")
                                    .setContentText("Suhu " + nameSuhu + " pH " + namepH + " Turbidity " + nameTurbidity)
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

                            notificationManagerCompat.notify(200,builder.build());
                        }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
