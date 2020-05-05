package com.example.sistempendeteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class HistoryActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    RecyclerView rv_list;
    DatabaseReference reference;
    HistoryAdapter adapter;
    ArrayList<HistoryList> data = new ArrayList<>();
    ProgressDialog pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rv_list = findViewById(R.id.rv_history);
        rv_list.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv_list.setLayoutManager(lm);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv_list.addItemDecoration(divider);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        pg = new ProgressDialog(this);
        pg.setMessage("Tunggu ...");
        pg.show();
        init();
    }

    private void init() {
        Query query = reference.child("sensor").child("dht");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HistoryList list = new HistoryList();
                    list.setHari(snapshot.child("tanggal").getValue().toString());
                    list.setBulan(snapshot.child("bulan").getValue().toString());
                    list.setTahun(snapshot.child("tahun").getValue().toString());
                    list.setSuhu(snapshot.child("temperature").getValue().toString());
                    list.setPh(snapshot.child("pH").getValue().toString());
                    list.setKekruhan(snapshot.child("turbidity").getValue().toString());

                    data.add(list);
                    if (list == null){
                        pg.show();
                    }else{
                        pg.dismiss();
                    }
                }
                adapter = new HistoryAdapter(HistoryActivity.this, data);
                rv_list.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
