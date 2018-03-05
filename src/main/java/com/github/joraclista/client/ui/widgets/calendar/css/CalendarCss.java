package com.github.joraclista.client.ui.widgets.calendar.css;

import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface CalendarCss extends CssResource {

    String monthLabelFormat();

    String timeLabelFormat();

    String dateLabelFormat();

    String yearLabelFormat();

    String weekDaysShortcut();

    String monthShortcut();

    int _width();

    int _height();

    int maxRows();

    String dateLabel();

    String buttons();

    String upButton();

    String downButton();

    String header();

    String main();

    String daysPanel();

    String monthLabel();

    String timeLabel();

    String subHeader();

    String otherMonthDayLabel();

    String selected();

    String monthSelectPanel();

    String yearSelectPanel();
}
