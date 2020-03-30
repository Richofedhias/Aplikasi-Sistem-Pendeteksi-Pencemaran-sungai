package com.example.sistempendeteksi;

public class PointValues {

    long xValue;
    int yValue;

    public PointValues(long xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public PointValues() {
    }

    public long getxValue() {
        return xValue;
    }

    public void setxValue(long xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }
}
