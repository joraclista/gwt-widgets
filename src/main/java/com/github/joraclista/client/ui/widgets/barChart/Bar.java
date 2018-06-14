package com.github.joraclista.client.ui.widgets.barChart;

import com.github.joraclista.client.ui.widgets.barChart.css.BarChartBundle;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Created by Alisa
 * version 1.0.
 */
public class Bar extends FlowPanel implements HasClickHandlers {

    private Label label;
    private Label bar;

    public Bar(BarChartBundle.Css css, String text, double height) {
        addStyleName(css.barHolder());
        add(label = new Label(text));
        add(bar = new Label());
        label.addStyleName(css.label());
        bar.addStyleName(css.bar());
        getElement().getStyle().setProperty("height", "100%");
        bar.getElement().getStyle().setProperty("height", Math.abs(height) * 100 + "%");
        bar.getElement().setAttribute("color", height < 0 ? "invalid" : "normal");
        if (height < 0) {
            getElement().getStyle().setProperty("justifyContent", "flex-start");
            getElement().setAttribute("bar", "negative");
        }
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return bar.addClickHandler(handler);
    }
}
