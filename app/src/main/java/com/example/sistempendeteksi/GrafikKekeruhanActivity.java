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

    private LineChartView LCKeruhHarian, LCKeruhMingguan, LCKeruhBulanan;
    private LineChartData data;
    private Button Harian, Mingguan, Bulanan;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_kekeruhan);

        LCKeruhHarian = findViewById(R.id.LC_kekeruhanHarian);
        LCKeruhMingguan = findViewById(R.id.LC_kekeruhanMingguan);
        LCKeruhBulanan = findViewById(R.id.LC_kekeruhanBulanan);
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

        LCKeruhHarian.setLineChartData(data);
        Viewport viewport = new Viewport(LCKeruhHarian.getMaximumViewport());
        viewport.top = 1000;
        LCKeruhHarian.setMaximumViewport(viewport);
        LCKeruhHarian.setCurrentViewport(viewport);
        Harian = findViewById(R.id.btn_KekeruhanHarian);
        Mingguan = findViewById(R.id.btn_KekeruhanMingguan);
        Bulanan = findViewById(R.id.btn_KekeruhanBulanan);

        Harian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LCKeruhHarian.setVisibility(View.VISIBLE);
                LCKeruhMingguan.setVisibility(View.GONE);
                LCKeruhBulanan.setVisibility(View.GONE);
            }
        });

        Mingguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikMingguan();
                LCKeruhHarian.setVisibility(View.GONE);
                LCKeruhMingguan.setVisibility(View.VISIBLE);
                LCKeruhBulanan.setVisibility(View.GONE);
            }
        });

        Bulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GrafikBulanan();
                LCKeruhHarian.setVisibility(View.GONE);
                LCKeruhMingguan.setVisibility(View.GONE);
                LCKeruhBulanan.setVisibility(View.VISIBLE);
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

        LCKeruhMingguan.setLineChartData(data);
        Viewport viewport = new Viewport(LCKeruhMingguan.getMaximumViewport());
        viewport.top = 1000;
        LCKeruhMingguan.setMaximumViewport(viewport);
        LCKeruhMingguan.setCurrentViewport(viewport);
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

        LCKeruhBulanan.setLineChartData(data);
        Viewport viewport = new Viewport(LCKeruhBulanan.getMaximumViewport());
        viewport.top = 1000;
        LCKeruhBulanan.setMaximumViewport(viewport);
        LCKeruhBulanan.setCurrentViewport(viewport);
    }
}
