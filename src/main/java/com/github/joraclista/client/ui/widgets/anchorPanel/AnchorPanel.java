package com.github.joraclista.client.ui.widgets.anchorPanel;

import com.github.joraclista.client.ui.widgets.WidgetsGroup;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alisa
 * version 1.0.
 */
public class AnchorPanel {
    private static long ID = 0;
    private final AnchorPanelBundle.Css css;
    private Map<String, SimplePanel> anchors = new HashMap<>();
    private ScrollPanel scrollPanel;
    private FlowPanel panel;
    private Anchor anchor;

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
        anchor = new Anchor();
        anchor.setVisible(false);
        panel.add(anchor);
    }

    public void addWidget(String header, List<IsWidget> widgets) {
        IsWidget group = renderWidgetGroup(header, widgets);
        group.asWidget().getElement().setId("anchor-" + ID++);

        if (anchors.get(header) != null) {
            anchors.get(header).setWidget(group);
        } else {
            SimplePanel wrapper = new SimplePanel(group.asWidget());
            wrapper.addStyleName(css.wrapper());
            anchors.put(header, wrapper);
            panel.add(group);
        }

    }

    protected IsWidget renderWidgetGroup(String header, List<IsWidget> widgets) {
        return new WidgetsGroup(header, widgets);
    }

    public void scrollToAnchor(String header) {
        SimplePanel wrapper = anchors.get(header);
        if (wrapper == null) {
            throw new IllegalArgumentException("No such anchor!");
        }
       anchor.setHref("#" + wrapper.getWidget().getElement().getId());
        click(anchor.getElement());
    }

    public static native void click(Element elem) /*-{
        elem.click()
    }-*/;

    public IsWidget asWidget() {
        return scrollPanel;
    }
}
