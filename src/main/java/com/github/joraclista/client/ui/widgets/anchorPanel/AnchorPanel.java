package com.github.joraclista.client.ui.widgets.anchorPanel;

import com.github.joraclista.client.ui.widgets.WidgetsGroup;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alisa
 * version 1.0.
 */
public class AnchorPanel {

    private final AnchorPanelBundle.Css css;
    private Map<String, SimplePanel> anchors = new HashMap<>();
    private Map<String, Integer> anchorsPositions = new HashMap<>();
    private ScrollPanel scrollPanel;
    private FlowPanel panel;

    public AnchorPanel() {
        this(AnchorPanelBundle.BUNDLE.css());
    }

    public AnchorPanel(AnchorPanelBundle.Css css) {
        css.ensureInjected();
        this.css = css;
        this.scrollPanel = new ScrollPanel();
        this.scrollPanel.add(panel = new FlowPanel());
        this.scrollPanel.setAlwaysShowScrollBars(true);
        this.scrollPanel.addStyleName(css.scroll());
    }
    public void addWidget(String header, List<IsWidget> widgets) {
        if (anchors.get(header) != null) {
            anchors.get(header).setWidget(new WidgetsGroup(header, widgets));
        } else {
            anchorsPositions.put(header, panel.getOffsetHeight());
            SimplePanel wrapper = new SimplePanel(new WidgetsGroup(header, widgets));
            wrapper.addDomHandler(event -> {
                scrollToAnchor("Label 10");}, ClickEvent.getType());
            wrapper.addStyleName(css.wrapper());
            anchors.put(header, wrapper);
            panel.add(wrapper);
        }

    }

    public void scrollToAnchor(String header) {
        Integer position = anchorsPositions.get(header);
        if (position == null) {
            throw new IllegalArgumentException("No such anchor!");
        }
        scrollPanel.setVerticalScrollPosition(-position);
    }

    public IsWidget asWidget() {
        return scrollPanel;
    }
}
