package com.github.joraclista.client.ui.widgets.barChart;

/**
 * Created by Alisa
 * version 1.0.
 */
public class BarModel {
    private double value;
    private String text;

    public BarModel(double value, String text) {
        this.value = value;
        this.text = text;
    }

    public double getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "BarModel{" +
                "value=" + value +
                ", text='" + text + '\'' +
                '}';
    }
}
