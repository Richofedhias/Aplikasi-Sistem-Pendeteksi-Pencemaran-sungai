package com.example.sistempendeteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    private LineChartView lineChartView;
    private LineChartData data;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

//    EditText xValue, yValue;
//    Button btn_input;
//
//    SimpleDateFormat adf = new SimpleDateFormat("dd:mm");
//    LineChart lineChart;
//    DatabaseReference myRef;
//    FirebaseDatabase database;
//    LineDataSet lineDataSet = new LineDataSet(null,null);
//    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
//    LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lineChartView = findViewById(R.id.chart);

        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#055797"));

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#00BCD4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Grafik Hasil Pendeteksi");
        yAxis.setTextColor(Color.parseColor("#00BCD4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 100;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);

//        yValue = findViewById(R.id.yValue);
//        btn_input = findViewById(R.id.btn_input);
//
//        lineChart = findViewById(R.id.grafik);
//
//        database= FirebaseDatabase.getInstance();
//        myRef = database.getReference("Grafik");
//
//        insertData();
//
//        retrieveData();

    }

//    private void insertData() {
//        btn_input.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String id = myRef.push().getKey();
//                long x = new Date().getTime();
//                int y = Integer.parseInt(yValue.getText().toString());
//
//                PointValue pointValue = new PointValue(x, y);
//                myRef.child(id).setValue(pointValue);
//
//                retrieveData();
//            }
//        });
//    }
//
//    private void retrieveData() {
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ArrayList<Entry> dataVals = new ArrayList<Entry>();
//
//                if (dataSnapshot.hasChildren()) {
//                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                        PointValue point = dataSnapshot1.getValue(PointValue.class);
//                        dataVals.add(new Entry(point.getxValue(), point.getyValue()));
//                    }
//
//                    showChart(dataVals);
//                } else {
//                    lineChart.clear();
//                    lineChart.invalidate();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void showChart(ArrayList<Entry> dataVals) {
//        lineDataSet.setValues(dataVals);
//        lineDataSet.setLabel("DataSet 1");
//        iLineDataSets.clear();
//        iLineDataSets.add(lineDataSet);
//        lineData = new LineData(iLineDataSets);
//        lineChart.clear();
//        lineChart.setData(lineData);
//        lineChart.invalidate();
//    }

}
