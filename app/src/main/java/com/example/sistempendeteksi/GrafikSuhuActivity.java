package com.example.sistempendeteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class GrafikSuhuActivity extends AppCompatActivity {

    LineChart lineChart;
    LineDataSet lineDataSet = new LineDataSet(null,null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;

    FirebaseDatabase database;
    DatabaseReference reference;

//    private LineChartView lineChartView;
//    private LineChartData data;
//    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
//            "Oct", "Nov", "Dec"};
//    int[] yAxisData = {23, 25, 23, 27, 30, 19, 20, 32, 22, 10, 9, 18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_suhu);

        lineChart = findViewById(R.id.LC_suhu);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("sensor");
//
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
//        viewport.top = 40;
//        lineChartView.setMaximumViewport(viewport);
//        lineChartView.setCurrentViewport(viewport);
        retrieveData();
    }

    private void retrieveData() {
        reference.child("dht").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> dataVals = new ArrayList<Entry>();

                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        PointValues pointValues = snapshot.getValue(PointValues.class);

                        dataVals.add(new Entry((float) pointValues.getxValue(), pointValues.getyValue()));
                    }


                    showChart(dataVals);
                } else {
                    lineChart.clear();
                    lineChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("Data");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}
