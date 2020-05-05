package com.example.sistempendeteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

public class GrafikKekeruhanActivity extends AppCompatActivity {
    GraphView graphView;
    LineGraphSeries series;

    FirebaseDatabase database;
    DatabaseReference reference;

    SimpleDateFormat sdf = new SimpleDateFormat("M");

    private LineChartView lineChartView;
    private LineChartData data;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_kekeruhan);

        graphView = findViewById(R.id.LC_kekeruhan);
        series = new LineGraphSeries();
        graphView.addSeries(series);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("sensor");

        graphView.getGridLabelRenderer().setNumHorizontalLabels(3);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return sdf.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX);
                }

            }
        });

//        List yAxisValues = new ArrayList();
//        List axisValues = new ArrayList();
//
//
//        Line line = new Line(yAxisValues).setColor(Color.parseColor("#055797"));
//
//        for (int i = 0; i < axisData.length; i++) {
//            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
//        }
//
//        for (int i = 0; i < yAxisData.length; i++) {
//            yAxisValues.add(new PointValue(i, yAxisData[i]));
//        }
//
//        List<Line> lines = new ArrayList<Line>();
//        lines.add(line);
//
//        data = new LineChartData();
//        data.setLines(lines);
//
//        Axis axis = new Axis();
//        axis.setValues(axisValues);
//        axis.setTextSize(16);
//        axis.setTextColor(Color.parseColor("#00BCD4"));
//        data.setAxisXBottom(axis);
//
//        Axis yAxis = new Axis();
//        yAxis.setName("Grafik Hasil Pendeteksi");
//        yAxis.setTextColor(Color.parseColor("#00BCD4"));
//        yAxis.setTextSize(16);
//        data.setAxisYLeft(yAxis);
//
//        lineChartView.setLineChartData(data);
//        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
//        viewport.top = 1000;
//        lineChartView.setMaximumViewport(viewport);
//        lineChartView.setCurrentViewport(viewport);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Query query = reference.child("dht");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PointValues pointValues = new PointValues();
                    pointValues.setyValue(Integer.parseInt(snapshot.child("turbidity").getValue().toString()));
                    pointValues.setxValue(Long.parseLong(snapshot.child("bulan").getValue().toString()));


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        reference.child("dht").child("turbidity").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
//                int index = 0;
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    PointValues pointValues = snapshot.getValue(PointValues.class);
//                    dp[index] = new DataPoint(pointValues.getxValue(), pointValues.getyValue());
//                    index++;
//                }
//
//                series.resetData(dp);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
