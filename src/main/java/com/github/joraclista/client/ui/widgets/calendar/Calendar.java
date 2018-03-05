package com.github.joraclista.client.ui.widgets.calendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.joraclista.client.ui.common.TaskUtil.scheduleRepeating;
import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;
import static com.google.gwt.user.datepicker.client.CalendarUtil.*;
import static java.util.Arrays.asList;

/**
 * Created by Alisa
 * version 1.0.
 */
public class Calendar extends Composite implements HasValueChangeHandlers<Date> {

    interface CalendarUiBinder extends UiBinder<FlowPanel, Calendar> {
    }

    private static CalendarUiBinder ourUiBinder = GWT.create(CalendarUiBinder.class);


    private final CalendarCss css;
    private Date date;

    @UiField
    Label monthLabel;
    @UiField
    Button upButton;
    @UiField
    Label timeLabel;
    @UiField
    Label dateLabel;
    @UiField
    Button downButton;
    @UiField
    FlowPanel daysPanel;
    @UiField
    FlowPanel header;
    @UiField
    FlowPanel subHeader;
    @UiField
    FlowPanel buttons;
    private Timer repeatingTask;

    public Calendar(CalendarCss css) {
        this.css = css;
        this.css.ensureInjected();

        initWidget(ourUiBinder.createAndBindUi(this));



        this.asWidget().addStyleName(css.main());

        this.header.addStyleName(css.header());
        this.timeLabel.addStyleName(css.timeLabel());
        this.dateLabel.addStyleName(css.dateLabel());

        this.subHeader.addStyleName(css.subHeader());
        this.monthLabel.addStyleName(css.monthLabel());
        this.buttons.addStyleName(css.buttons());
        this.upButton.addStyleName(css.upButton());
        this.downButton.addStyleName(css.downButton());


        this.daysPanel.addStyleName(css.daysPanel());



        drawMonthForDate(new Date());
    }

    private void updateTimeLabel(Date date) {
        timeLabel.setText(getFormat(css.timeLabelFormat()).format(date));
    }

    private void drawMonthForDate(Date start) {
        this.date = start;

        monthLabel.setText(getFormat(css.monthLabelFormat()).format(this.date));
        dateLabel.setText(getFormat(css.dateLabelFormat()).format(new Date()));
        updateTimeLabel(this.date);

        if (this.repeatingTask == null) {
            this.repeatingTask = scheduleRepeating(() -> updateTimeLabel(new Date()), 1000);
        }

        this.repeatingTask = scheduleRepeating(() -> updateTimeLabel(new Date()), 1000);

        daysPanel.clear();
        asList(css.weekDaysShortcut().split(" ")).forEach(day -> daysPanel.add(new Label(day)));

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
            dayLabel.setStyleName(css.otherMonthDayLabel(), day.getMonth() != this.date.getMonth());
            dayLabel.setStyleName(css.selected(), isSameDate(day, new Date()));
            daysPanel.add(dayLabel);
        });

    }


    public void setSelectedDate(Date date) {
        this.date = date;
    }


    public Date getSelectedDate() {
        return date;
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return super.addHandler(handler, ValueChangeEvent.getType());
    }

    @UiHandler("upButton")
    void onUpButtonClick(ClickEvent event) {
        addMonthsToDate(this.date, -1);
        drawMonthForDate(this.date);
    }

    @UiHandler("downButton")
    void onDownButtonClick(ClickEvent event) {
        addMonthsToDate(this.date, 1);
        drawMonthForDate(this.date);
    }
}