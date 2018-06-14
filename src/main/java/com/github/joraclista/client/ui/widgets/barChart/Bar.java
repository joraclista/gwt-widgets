package com.github.joraclista.client.ui.widgets.barChart;

import com.github.joraclista.client.ui.widgets.barChart.css.BarChartBundle;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Created by Alisa
 * version 1.0.
 */
public class Bar extends FlowPanel {

    private Label label;
    private FlowPanel barContainer;
    private Label bar;

    public Bar(BarChartBundle.Css css, String text, double height) {
        addStyleName(css.barHolder());
        add(label = new Label(text));
        add(barContainer = new FlowPanel());
        barContainer.add(bar = new Label());
        label.addStyleName(css.label());
        bar.addStyleName(css.bar());
        barContainer.addStyleName(css.barContainer());
        bar.setHeight(Math.abs(height) * 100 + "%");
        bar.getElement().setAttribute("color", height < 0 ? "invalid" : "normal");
        if (height < 0) {
            getElement().setAttribute("bar", "negative");
        }
    }
}
