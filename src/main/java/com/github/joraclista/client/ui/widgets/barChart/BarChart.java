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

    private FlowPanel container;
    private Label titleLabel;
    private Label axis;
    private ChartValues chartValues = ChartValues.POSITIVE;

    public BarChart(String title) {
        addStyleName(CSS.main());
        add(titleLabel = new Label(title));
        add(container = new FlowPanel());
        container.add(axis = new Label());
        container.addStyleName(CSS.container());
        axis.addStyleName(CSS.axis());
        titleLabel.addStyleName(CSS.titleLabel());
    }

    public void render(List<BarModel> model) {
        double max = model.stream()
                .mapToDouble(m -> Math.abs(m.getValue())).max()
                .getAsDouble();
        chartValues = model.stream().mapToDouble(m -> m.getValue()).filter(v -> v >=0 ).count() == model.size()
                ? ChartValues.POSITIVE :
                (model.stream().mapToDouble(m -> m.getValue()).filter(v -> v <=0 ).count() == 0 ? ChartValues.NEGATIVE : ChartValues.MIXED);
        container.getElement().setAttribute("chart", chartValues.name().toLowerCase());
        model.stream()
                .forEach(m -> {
                    Bar bar = new Bar(CSS, m, m.getValue() / max);
                    bar.addClickHandler(event -> ValueChangeEvent.fire(BarChart.this, m));
                    container.add(bar);
                    bar.setWidth(100 / model.size() + "%");
                });
    }


    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<BarModel> handler) {
        return super.addHandler(handler, ValueChangeEvent.getType());
    }

}
