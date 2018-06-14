package com.github.joraclista.client.ui.widgets.barChart;

import com.github.joraclista.client.ui.widgets.barChart.css.BarChartBundle;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.List;

/**
 * Created by Alisa
 * version 1.0.
 */
public class BarChart extends FlowPanel implements HasValueChangeHandlers<BarModel> {

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
        chartValues = model.stream().mapToDouble(m -> m.getValue()).filter(v -> v >=0 ).count() == model.size()
                ? ChartValues.POSITIVE :
                (model.stream().mapToDouble(m -> m.getValue()).filter(v -> v <=0 ).count() == 0 ? ChartValues.NEGATIVE : ChartValues.MIXED);
        getElement().setAttribute("chart", chartValues.name().toLowerCase());
        model.stream()
                .forEach(m -> {
                    Bar bar = new Bar(CSS, m.getText(), m.getValue() / max);
                    bar.addClickHandler(event -> ValueChangeEvent.fire(BarChart.this, m));
                    BarChart.this.add(bar);
                    bar.setWidth(100 / model.size() + "%");
                });
    }


    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<BarModel> handler) {
        return super.addHandler(handler, ValueChangeEvent.getType());
    }

}
