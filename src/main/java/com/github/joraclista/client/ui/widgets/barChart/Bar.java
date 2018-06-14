package com.github.joraclista.client.ui.widgets.barChart;

import com.github.joraclista.client.ui.widgets.barChart.css.BarChartBundle;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import static com.github.joraclista.client.ui.common.css.StyleUtils.setAttribute;
import static com.github.joraclista.client.ui.common.css.StyleUtils.setStyleProperty;

/**
 * Created by Alisa
 * version 1.0.
 */
public class Bar extends FlowPanel implements HasClickHandlers {

    private Label legend;
    private Label label;
    private Label bar;

    public Bar(BarChartBundle.Css css, BarModel model, double height) {

        add(label = new Label(model.getValue() + ""));
        add(bar = new Label());
        add(legend = new Label(model.getLegend()));
        addStyles(css);

        setStyleProperty(bar, "height", Math.abs(height) * 100 + "%");
        setAttribute(bar, "color", height < 0 ? "invalid" : "normal");

        if (height <= 0) {
            setStyleProperty(this, "justifyContent", "flex-start");
            setAttribute(this, "bar", "negative");
            setStyleProperty(label, "top", "calc(" + Math.abs(height) * 100 + "% + 10px)");
        } else {
            setStyleProperty(label, "bottom", "calc(" + Math.abs(height) * 100 + "% + 10px)");
        }

    }

    private void addStyles(BarChartBundle.Css css) {
        addStyleName(css.barHolder());
        label.addStyleName(css.label());
        legend.addStyleName(css.legend());
        bar.addStyleName(css.bar());
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return bar.addClickHandler(handler);
    }
}
