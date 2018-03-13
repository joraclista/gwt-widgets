package com.github.joraclista.client.ui.widgets.tocPanel;

import com.github.joraclista.client.ui.widgets.tocPanel.css.TocCss;
import com.github.joraclista.client.ui.widgets.tocPanel.css.TocPanelBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alisa
 * version 1.0.
 */
public class TocPanel extends Composite {
    interface TocPanelUiBinder extends UiBinder<FlowPanel, TocPanel> {
    }

    private static TocPanelUiBinder ourUiBinder = GWT.create(TocPanelUiBinder.class);

    private static long ID = 0;

    private final TocCss css;
    private Map<String, IsWidget> anchors = new HashMap<>();

    @UiField
    FlowPanel content;
    @UiField
    FlowPanel toc;
    @UiField
    FlowPanel tocContainer;
    @UiField
    Anchor anchor;


    public TocPanel() {
        this(TocPanelBundle.BUNDLE.css());
    }

    public TocPanel(TocCss css) {
        initWidget(ourUiBinder.createAndBindUi(this));
        css.ensureInjected();
        this.css = css;
        this.content.addStyleName(css.content());
        this.tocContainer.addStyleName(css.tocContainer());
        this.toc.addStyleName(css.toc());
        this.asWidget().addStyleName(css.main());
        withLayout(Layout.HORIZONTAL);

    }

    public void addWidget(String header, List<IsWidget> widgets) {
        IsWidget group = renderWidgetGroup(header, widgets);
        group.asWidget().getElement().setId("anchor-" + ID++);
        anchors.put(header, group);
        toc.add(renderTocLabel(header, "#" + group.asWidget().getElement().getId()));
        content.add(group);

    }

    public TocPanel withLayout(Layout layout) {
        Layout _layout = layout == null ? Layout.HORIZONTAL : layout;
        Arrays.stream(Layout.values())
                .forEach(layOut -> this.asWidget().setStyleName(layOut.style(css), _layout.equals(layOut)));
        return this;
    }

    protected IsWidget renderTocLabel(String header, String url) {
        return new Anchor(header, HasDirection.Direction.DEFAULT, url);
    }

    protected IsWidget renderWidgetGroup(String header, List<IsWidget> widgets) {
        return new WidgetsGroup()
                .withHeader(header)
                .withWidgets(widgets);
    }

    public void scrollToAnchor(String header) {
        IsWidget widget = anchors.get(header);
        if (widget == null) {
            throw new IllegalArgumentException("No such anchor!");
        }
        anchor.setHref("#" + widget.asWidget().getElement().getId());
        click(anchor.getElement());
    }

    public static native void click(Element elem) /*-{
        elem.click()
    }-*/;

}