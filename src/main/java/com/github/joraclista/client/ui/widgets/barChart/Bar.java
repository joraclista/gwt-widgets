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

    private Label legend;
    private Label label;
    private Label bar;

    public Bar(BarChartBundle.Css css, BarModel model, double height) {
        addStyleName(css.barHolder());
        add(label = new Label(model.getValue() + ""));
        add(bar = new Label());
        add(legend = new Label(model.getLegend()));
        label.addStyleName(css.label());
        legend.addStyleName(css.legend());
        bar.addStyleName(css.bar());
        getElement().getStyle().setProperty("height", "100%");
        bar.getElement().getStyle().setProperty("height", Math.abs(height) * 100 + "%");
        bar.getElement().setAttribute("color", height < 0 ? "invalid" : "normal");
        if (height <= 0) {
            getElement().getStyle().setProperty("justifyContent", "flex-start");
            getElement().setAttribute("bar", "negative");
            label.getElement().getStyle().setProperty("top", "calc(" + Math.abs(height) * 100 + "% + 10px)");
        } else {
            label.getElement().getStyle().setProperty("bottom", "calc(" + Math.abs(height) * 100 + "% + 10px)");
        }

    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return bar.addClickHandler(handler);
    }
}
