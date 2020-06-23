package com.example.sistempendeteksi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class GrafikpHActivity extends AppCompatActivity {

    private LineChartView LCpHHarian, LCpHMingguan, LCpHBulanan;
    private LineChartData data;
    private Button Harian, Mingguan, Bulanan;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    int[] yAxisData = {5, 2, 5, 3, 1, 6, 5, 4, 4, 10, 9, 8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_ph);

        LCpHHarian = findViewById(R.id.LC_pHHarian);
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
        axis.setName("Dalam Harian");
        axis.setTextColor(Color.parseColor("#00BCD4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Grafik Hasil Pendeteksi");
        yAxis.setTextColor(Color.parseColor("#00BCD4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        LCpHHarian.setLineChartData(data);
        Viewport viewport = new Viewport(LCpHHarian.getMaximumViewport());
        viewport.top = 20;
        LCpHHarian.setMaximumViewport(viewport);
        LCpHHarian.setCurrentViewport(viewport);

        LCpHMingguan = findViewById(R.id.LC_pHMingguan);
        LCpHBulanan = findViewById(R.id.LC_pHBulanan);
        Harian = findViewById(R.id.btn_pHHarian);
        Mingguan = findViewById(R.id.btn_pHMingguan);
        Bulanan = findViewById(R.id.btn_pHBulanan);

        Harian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LCpHHarian.setVisibility(View.VISIBLE);
                LCpHMingguan.setVisibility(View.GONE);
                LCpHBulanan.setVisibility(View.GONE);
            }
        });

        Mingguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikMingguan();
                LCpHHarian.setVisibility(View.GONE);
                LCpHMingguan.setVisibility(View.VISIBLE);
                LCpHBulanan.setVisibility(View.GONE);
            }
        });

        Bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikBulanan();
                LCpHHarian.setVisibility(View.GONE);
                LCpHMingguan.setVisibility(View.GONE);
                LCpHBulanan.setVisibility(View.VISIBLE);
            }
        });
    }

    public void GrafikMingguan() {
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
        axis.setName("Dalam Mingguan");
        axis.setTextColor(Color.parseColor("#00BCD4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Grafik Hasil Pendeteksi");
        yAxis.setTextColor(Color.parseColor("#00BCD4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        LCpHMingguan.setLineChartData(data);
        Viewport viewport = new Viewport(LCpHMingguan.getMaximumViewport());
        viewport.top = 20;
        LCpHMingguan.setMaximumViewport(viewport);
        LCpHMingguan.setCurrentViewport(viewport);
    }

    public void GrafikBulanan() {
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
        axis.setName("Dalam Bulanan");
        axis.setTextColor(Color.parseColor("#00BCD4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Grafik Hasil Pendeteksi");
        yAxis.setTextColor(Color.parseColor("#00BCD4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        LCpHBulanan.setLineChartData(data);
        Viewport viewport = new Viewport(LCpHBulanan.getMaximumViewport());
        viewport.top = 20;
        LCpHBulanan.setMaximumViewport(viewport);
        LCpHBulanan.setCurrentViewport(viewport);
    }
}
