package com.github.joraclista.client.ui.widgets.barChart;

import com.github.joraclista.client.ui.widgets.barChart.css.BarChartBundle;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.List;

/**
 * Created by Alisa
 * version 1.0.
 */
public class BarChart extends FlowPanel {

    private static final BarChartBundle.Css CSS  = BarChartBundle.BUNDLE.css();
    static {
        CSS.ensureInjected();
    }

    enum ChartValues {
        POSITIVE, NEGATIVE, MIXED
    }

    private Label axis;
    private ChartValues chartValues = ChartValues.POSITIVE;

    public BarChart() {
        addStyleName(CSS.container());
        add(axis = new Label());
        axis.addStyleName(CSS.axis());
    }

    public void render(List<BarModel> model) {
        double max = model.stream()
                .mapToDouble(m -> Math.abs(m.getValue())).max()
                .getAsDouble();
        int sum = model.stream().mapToInt(m -> m.getValue() >= 0 ? 1 : 0).sum();
        chartValues = sum == model.size() ? ChartValues.POSITIVE : (sum == 0 ? ChartValues.NEGATIVE : ChartValues.MIXED);
        getElement().setAttribute("chart", chartValues.name().toLowerCase());
        model.stream()
                .map(m -> new Bar(CSS, m.getText(), m.getValue() / max))
                .forEach(bar -> {
                    BarChart.this.add(bar);
                    bar.setWidth(100 / model.size() + "%");
                });
    }
}
