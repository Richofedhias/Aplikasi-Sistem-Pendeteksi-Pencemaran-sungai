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

public class GrafikSuhuActivity extends AppCompatActivity {

    private LineChartView LCSuhuHarian, LCSuhuMingguan, LCSuhuBulanan;
    private LineChartData data;
    private Button Harian, Mingguan, Bulanan;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int[] yAxisData = {23, 25, 23, 27, 30, 19, 20, 32, 22, 10, 9, 18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_suhu);

        LCSuhuHarian = findViewById(R.id.LC_suhuHarian);
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

        LCSuhuHarian.setLineChartData(data);
        Viewport viewport = new Viewport(LCSuhuHarian.getMaximumViewport());
        viewport.top = 40;
        LCSuhuHarian.setMaximumViewport(viewport);
        LCSuhuHarian.setCurrentViewport(viewport);

        LCSuhuMingguan = findViewById(R.id.LC_suhuMingguan);
        LCSuhuBulanan = findViewById(R.id.LC_suhuBulanan);
        Harian = findViewById(R.id.btn_SuhuHarian);
        Mingguan = findViewById(R.id.btn_SuhuMingguan);
        Bulanan = findViewById(R.id.btn_SuhuBulanan);

        Harian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LCSuhuHarian.setVisibility(View.VISIBLE);
                LCSuhuMingguan.setVisibility(View.GONE);
                LCSuhuBulanan.setVisibility(View.GONE);
            }
        });

        Mingguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikMingguan();
                LCSuhuHarian.setVisibility(View.GONE);
                LCSuhuMingguan.setVisibility(View.VISIBLE);
                LCSuhuBulanan.setVisibility(View.GONE);
            }
        });

        Bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikBulanan();
                LCSuhuHarian.setVisibility(View.GONE);
                LCSuhuMingguan.setVisibility(View.GONE);
                LCSuhuBulanan.setVisibility(View.VISIBLE);
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

        LCSuhuMingguan.setLineChartData(data);
        Viewport viewport = new Viewport(LCSuhuMingguan.getMaximumViewport());
        viewport.top = 40;
        LCSuhuMingguan.setMaximumViewport(viewport);
        LCSuhuMingguan.setCurrentViewport(viewport);
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

        LCSuhuBulanan.setLineChartData(data);
        Viewport viewport = new Viewport(LCSuhuBulanan.getMaximumViewport());
        viewport.top = 40;
        LCSuhuBulanan.setMaximumViewport(viewport);
        LCSuhuBulanan.setCurrentViewport(viewport);
    }
}
