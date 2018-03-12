package com.github.joraclista.client.ui.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * Created by Alisa
 * version 1.0.
 */
public class WidgetsGroup extends Composite {
    interface Binder extends UiBinder<FlowPanel, WidgetsGroup> {
    }

    private static Binder uiBinder = GWT.create(Binder.class);

    @UiField
    Label header;
    @UiField
    FlowPanel panel;

    public WidgetsGroup(String header, List<IsWidget> widgets) {
        this(header);
        setWidgets(widgets);
    }

    public WidgetsGroup(String header) {
        initWidget(uiBinder.createAndBindUi(this));
        this.header.setText(header);
    }

    public void setWidgets(List<IsWidget> widgets) {
        panel.clear();
        ofNullable(widgets)
                .ifPresent(widgetsList -> widgetsList.forEach(widget -> panel.add(widget)));
    }

    public void setHeader(String header) {
        this.header.setText(header);
    }

    public void addWidgets(List<IsWidget> widgets) {
        ofNullable(widgets)
                .ifPresent(widgetsList -> widgetsList.forEach(widget -> panel.add(widget)));
    }
}