package com.github.joraclista.client;

import com.github.joraclista.client.ui.widgets.calendar.css.CalendarBundle;
import com.github.joraclista.client.ui.widgets.calendar.Calendar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class WidgetsShowcase implements EntryPoint {

    private final String ROOT_PANEL_SLOT_ID = "slot";



    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        createUI();
    }

    private void createUI() {

        addToRoot(new Calendar(CalendarBundle.BUNDLE.calendarCss()));

    }

    private void addToRoot(IsWidget widget) {
        RootPanel.get(ROOT_PANEL_SLOT_ID).add(widget);
    }


}
