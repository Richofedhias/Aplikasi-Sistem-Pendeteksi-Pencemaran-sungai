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

    private LineChartView LCSuhuHarian, LCSuhuTahunan, LCSuhuBulanan;
    private LineChartData data;
    private Button Harian, Tahunan, Bulanan;
    String[] axisDataBulan = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    int[] yAxisDataBulan = {23, 25, 23, 27, 30, 19, 20, 32, 22, 10, 9, 18};

    String[] axisDataTahun = {"2015", "2016", "2017", "2018", "2019", "2020"};
    int[] yAxisDataTahun = {25, 23, 27, 30, 32, 18};

    String[] axisDataHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu"};
    int[] yAxisDataHari = {23, 27, 30, 32, 22, 9, 18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_suhu);

        LCSuhuHarian = findViewById(R.id.LC_suhuHarian);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#055797"));

        for (int i = 0; i < axisDataHari.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisDataHari[i]));
        }

        for (int i = 0; i < yAxisDataHari.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisDataHari[i]));
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

        LCSuhuTahunan = findViewById(R.id.LC_suhuTahun);
        LCSuhuBulanan = findViewById(R.id.LC_suhuBulanan);
        Harian = findViewById(R.id.btn_SuhuHarian);
        Tahunan = findViewById(R.id.btn_SuhuTahun);
        Bulanan = findViewById(R.id.btn_SuhuBulanan);

        Harian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LCSuhuHarian.setVisibility(View.VISIBLE);
                LCSuhuTahunan.setVisibility(View.GONE);
                LCSuhuBulanan.setVisibility(View.GONE);
            }
        });

        Tahunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikTahunan();
                LCSuhuHarian.setVisibility(View.GONE);
                LCSuhuTahunan.setVisibility(View.VISIBLE);
                LCSuhuBulanan.setVisibility(View.GONE);
            }
        });

        Bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikBulanan();
                LCSuhuHarian.setVisibility(View.GONE);
                LCSuhuTahunan.setVisibility(View.GONE);
                LCSuhuBulanan.setVisibility(View.VISIBLE);
            }
        });
    }

    public void GrafikTahunan() {
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#055797"));

        for (int i = 0; i < axisDataTahun.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisDataTahun[i]));
        }

        for (int i = 0; i < yAxisDataTahun.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisDataTahun[i]));
        }

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setName("Dalam Tahunan");
        axis.setTextColor(Color.parseColor("#00BCD4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Grafik Hasil Pendeteksi");
        yAxis.setTextColor(Color.parseColor("#00BCD4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        LCSuhuTahunan.setLineChartData(data);
        Viewport viewport = new Viewport(LCSuhuTahunan.getMaximumViewport());
        viewport.top = 40;
        LCSuhuTahunan.setMaximumViewport(viewport);
        LCSuhuTahunan.setCurrentViewport(viewport);
    }

    public void GrafikBulanan() {
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#055797"));

        for (int i = 0; i < axisDataBulan.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisDataBulan[i]));
        }

        for (int i = 0; i < yAxisDataBulan.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisDataBulan[i]));
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
