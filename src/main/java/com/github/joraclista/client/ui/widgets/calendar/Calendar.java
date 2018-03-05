package com.github.joraclista.client.ui.widgets.calendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;

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

    public Calendar(CalendarCss css) {
        css.ensureInjected();
        this.css = css;

        initWidget(ourUiBinder.createAndBindUi(this));
        this.date = new Date();

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

        monthLabel.setText(getFormat(css.monthLabelFormat()).format(this.date));

        dateLabel.setText(getFormat(css.dateLabelFormat()).format(this.date));
        timeLabel.setText(getFormat(css.timeLabelFormat()).format(Calendar.this.date));

        Timer timer = new Timer() {

            @Override
            public void run() {
                Date date = new Date();
                timeLabel.setText(getFormat("hh:mm:ss a").format(date));
            }
        };
        timer.scheduleRepeating(1000);
        timer.run();


        draw(new Date());
    }

    private void draw(Date start) {
        daysPanel.clear();
        Arrays.asList("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa").forEach(day -> daysPanel.add(new Label(day)));
        Date current = start;
        CalendarUtil.setToFirstDayOfMonth(start);
        while (current.getDay() != 0) {
            CalendarUtil.addDaysToDate(start, -1);
        }

        List<Date> days = new ArrayList<>();
        for(int i =0; i<42; i++){

            days.add(CalendarUtil.copyDate(current));
            CalendarUtil.addDaysToDate(current, 1);
        }

        days.forEach(day -> {
            Label dayLabel = new Label(day.getDate() + "");
            if (day.getMonth() != this.date.getMonth()) {
                dayLabel.addStyleName(css.otherMonthDayLabel());
            }
            if (CalendarUtil.isSameDate(day, this.date)) {
                dayLabel.addStyleName(css.selected());
            }
            daysPanel.add(dayLabel);
        });
//        upButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//
//                Date pervious = CalendarUtil.copyDate(start);
//                CalendarUtil.addMonthsToDate(pervious, -1);
//                draw(pervious);
//            }
//        });
//        downButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//
//                Date next = CalendarUtil.copyDate(start);
//                CalendarUtil.addMonthsToDate(next, +1);
//                draw(next);
//            }
//        });
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

}