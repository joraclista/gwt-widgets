package com.github.joraclista.client.ui.widgets.tocPanel;

import com.github.joraclista.client.ui.common.css.StyleUtils;
import com.github.joraclista.client.ui.widgets.tocPanel.css.GroupCss;
import com.github.joraclista.client.ui.widgets.tocPanel.css.TocPanelBundle;
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
    private final GroupCss css;

    @UiField
    Label header;
    @UiField
    FlowPanel panel;

    public WidgetsGroup() {
        this(TocPanelBundle.BUNDLE.css(), "");
    }

    public WidgetsGroup(GroupCss css) {
        this(css, "");
    }

    public WidgetsGroup(String header) {
        this(TocPanelBundle.BUNDLE.css(), header);
    }

    public WidgetsGroup(GroupCss css, String header) {
        initWidget(uiBinder.createAndBindUi(this));
        applyStyles(css);
        this.header.setText(header);
        this.css = css;
        css.ensureInjected();
    }

    private void applyStyles(GroupCss css) {
        this.asWidget().addStyleName(css.group());
        this.panel.addStyleName(css.groupPanel());
        this.header.addStyleName(css.groupHeaderLabel());
    }

    public void setWidgets(List<IsWidget> widgets) {
        panel.clear();
        ofNullable(widgets)
                .ifPresent(widgetsList -> widgetsList.forEach(widget -> panel.add(widget)));
    }

    public void setHeader(String header) {
        this.header.setText(header);
    }

    public WidgetsGroup withHeader(String header) {
        this.header.setText(header);
        return this;
    }

    public WidgetsGroup withHeaderStyleNames(String... styleNames) {
        StyleUtils.addStyleNames(header, styleNames);
        return this;
    }

    public WidgetsGroup withWidgets(List<IsWidget> widgets) {
        panel.clear();
        ofNullable(widgets)
                .ifPresent(widgetsList -> widgetsList.forEach(widget -> panel.add(widget)));
        return this;
    }

}