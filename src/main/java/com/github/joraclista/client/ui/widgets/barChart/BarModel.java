package com.github.joraclista.client.ui.widgets.barChart;

/**
 * Created by Alisa
 * version 1.0.
 */
public class BarModel {
    private double value;
    private String legend;

    public BarModel(double value, String legend) {
        this.value = value;
        this.legend = legend;
    }

    public double getValue() {
        return value;
    }

    public String getLegend() {
        return legend;
    }

    @Override
    public String toString() {
        return "BarModel{" +
                "value=" + value +
                ", legend='" + legend + '\'' +
                '}';
    }
}
