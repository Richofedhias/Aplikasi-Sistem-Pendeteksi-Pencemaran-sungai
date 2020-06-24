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

    private LineChartView LCpHHarian, LCpHTahunan, LCpHBulanan;
    private LineChartData data;
    private Button Harian, Tahunan, Bulanan;
    String[] axisDataBulan = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    int[] yAxisDataBulan = {5, 2, 5, 3, 5, 6, 5, 4, 4, 10, 9, 8};

    String[] axisDataTahun = {"2015", "2016", "2017", "2018", "2019", "2020"};
    int[] yAxisDataTahun = {5, 5, 7, 10, 7, 8};

    String[] axisDataHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu"};
    int[] yAxisDataHari = {8, 5, 7, 5, 4, 9, 8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_ph);

        LCpHHarian = findViewById(R.id.LC_pHHarian);
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

        LCpHHarian.setLineChartData(data);
        Viewport viewport = new Viewport(LCpHHarian.getMaximumViewport());
        viewport.top = 15;
        LCpHHarian.setMaximumViewport(viewport);
        LCpHHarian.setCurrentViewport(viewport);

        LCpHTahunan = findViewById(R.id.LC_pHTahun);
        LCpHBulanan = findViewById(R.id.LC_pHBulanan);
        Harian = findViewById(R.id.btn_pHHarian);
        Tahunan = findViewById(R.id.btn_pHTahun);
        Bulanan = findViewById(R.id.btn_pHBulanan);

        Harian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LCpHHarian.setVisibility(View.VISIBLE);
                LCpHTahunan.setVisibility(View.GONE);
                LCpHBulanan.setVisibility(View.GONE);
            }
        });

        Tahunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikTahunan();
                LCpHHarian.setVisibility(View.GONE);
                LCpHTahunan.setVisibility(View.VISIBLE);
                LCpHBulanan.setVisibility(View.GONE);
            }
        });

        Bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikBulanan();
                LCpHHarian.setVisibility(View.GONE);
                LCpHTahunan.setVisibility(View.GONE);
                LCpHBulanan.setVisibility(View.VISIBLE);
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

        LCpHTahunan.setLineChartData(data);
        Viewport viewport = new Viewport(LCpHTahunan.getMaximumViewport());
        viewport.top = 15;
        LCpHTahunan.setMaximumViewport(viewport);
        LCpHTahunan.setCurrentViewport(viewport);
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

        LCpHBulanan.setLineChartData(data);
        Viewport viewport = new Viewport(LCpHBulanan.getMaximumViewport());
        viewport.top = 15;
        LCpHBulanan.setMaximumViewport(viewport);
        LCpHBulanan.setCurrentViewport(viewport);
    }
}
