package com.github.joraclista.client.ui.widgets.calendar.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface CalendarBundle extends ClientBundle {
    CalendarBundle BUNDLE = GWT.create(CalendarBundle.class);

    @Source({"calendar.css"})
    CalendarCss calendarCss();
}
