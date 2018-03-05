package com.github.joraclista.client.ui.widgets.calendar.strategies;

import com.github.joraclista.client.ui.widgets.calendar.RenderModel;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;
import static com.google.gwt.user.datepicker.client.CalendarUtil.*;
import static java.util.Arrays.asList;

/**
 * Created by Alisa
 * version 1.0.
 */
class DayInMonthStrategy extends CalendarStrategy {

    private List<HandlerRegistration> handlers = new LinkedList<>();

    @Override
    public void onUpButtonClick() {
        addMonthsToDate(this.getDate(), -1);
    }

    @Override
    public void onDownButtonClick() {
        addMonthsToDate(this.date, 1);
    }

    @Override
    public String getSubheaderLabel() {
        return getFormat(css.monthLabelFormat()).format(date);
    }

    @Override
    public void drawSelectionPanel(FlowPanel selectionPanel) {
        Date start = date;
        selectionPanel.clear();
        handlers.forEach(handler -> handler.removeHandler());
        handlers.clear();

        selectionPanel.setStyleName(css.daysPanel(), true);
        selectionPanel.setStyleName(css.monthSelectPanel(), false);

        asList(css.weekDaysShortcut().split(" ")).forEach(day -> selectionPanel.add(new Label(day)));

        Date current = copyDate(start);
        setToFirstDayOfMonth(current);
        while (current.getDay() != 0) {
            addDaysToDate(current, -1);
        }

        List<Date> days = new ArrayList<>();
        for(int i = 0; i < css.weekDaysShortcut().split(" ").length * css.maxRows(); i++){
            days.add(copyDate(current));
            addDaysToDate(current, 1);
        }

        days.forEach(day -> {
            Label dayLabel = new Label(day.getDate() + "");
            dayLabel.setStyleName(css.otherMonthDayLabel(), day.getMonth() != new Date().getMonth());
            dayLabel.setStyleName(css.selected(), isSameDate(day, new Date()));
            selectionPanel.add(dayLabel);


            handlers.add(dayLabel.addClickHandler(event -> new RenderModel(day, getSelectionType().down())));
        });
    }

    @Override
    protected SelectionType getSelectionType() {
        return SelectionType.SELECT_DAY_IN_A_MONTH;
    }


    public int getYear() {
        return date.getYear() + 1900;
    }

    public int getMonth() {
        return date.getMonth() + 1;
    }


}
