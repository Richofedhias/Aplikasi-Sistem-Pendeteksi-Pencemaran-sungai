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

public class GrafikKekeruhanActivity extends AppCompatActivity {

    private LineChartView LCKeruhHarian, LCKeruhTahunan, LCKeruhBulanan;
    private LineChartData data;
    private Button Harian, Tahunan, Bulanan;
    String[] axisDataBulan = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    int[] yAxisDataBulan = {535, 205, 34, 709, 1000, 2569, 366, 897, 452, 111, 90, 25};

    String[] axisDataHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu", "Minggu"};
    int[] yAxisDataHari = {1000, 2569, 30, 709, 897, 535, 452};

    String[] axisDataTahun = {"2015", "2016", "2017", "2018", "2019", "2020"};
    int[] yAxisDataTahun = {709, 90, 40, 111, 205, 2569};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_kekeruhan);

        LCKeruhHarian = findViewById(R.id.LC_kekeruhanHarian);
        LCKeruhTahunan = findViewById(R.id.LC_kekeruhanTahun);
        LCKeruhBulanan = findViewById(R.id.LC_kekeruhanBulanan);
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

        LCKeruhHarian.setLineChartData(data);
        Viewport viewport = new Viewport(LCKeruhHarian.getMaximumViewport());
        viewport.top = 3000;
        LCKeruhHarian.setMaximumViewport(viewport);
        LCKeruhHarian.setCurrentViewport(viewport);
        Harian = findViewById(R.id.btn_KekeruhanHarian);
        Tahunan = findViewById(R.id.btn_KekeruhanTahun);
        Bulanan = findViewById(R.id.btn_KekeruhanBulanan);

        Harian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LCKeruhHarian.setVisibility(View.VISIBLE);
                LCKeruhTahunan.setVisibility(View.GONE);
                LCKeruhBulanan.setVisibility(View.GONE);
            }
        });

        Bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikBulanan();
                LCKeruhHarian.setVisibility(View.GONE);
                LCKeruhTahunan.setVisibility(View.GONE);
                LCKeruhBulanan.setVisibility(View.VISIBLE);
            }
        });

        Tahunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikTahunan();
                LCKeruhHarian.setVisibility(View.GONE);
                LCKeruhTahunan.setVisibility(View.VISIBLE);
                LCKeruhBulanan.setVisibility(View.GONE);
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

        LCKeruhTahunan.setLineChartData(data);
        Viewport viewport = new Viewport(LCKeruhTahunan.getMaximumViewport());
        viewport.top = 3000;
        LCKeruhTahunan.setMaximumViewport(viewport);
        LCKeruhTahunan.setCurrentViewport(viewport);
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

        LCKeruhBulanan.setLineChartData(data);
        Viewport viewport = new Viewport(LCKeruhBulanan.getMaximumViewport());
        viewport.top = 3000;
        LCKeruhBulanan.setMaximumViewport(viewport);
        LCKeruhBulanan.setCurrentViewport(viewport);
    }
}
