package com.github.joraclista.client.ui.widgets.calendar;

import com.github.joraclista.client.ui.widgets.calendar.css.CalendarCss;
import com.github.joraclista.client.ui.widgets.calendar.strategies.CalendarStrategy;
import com.github.joraclista.client.ui.widgets.calendar.strategies.SelectionType;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.github.joraclista.client.ui.common.TaskUtil.scheduleRepeating;
import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;
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
    Label strategyPicker;
    @UiField
    Button upButton;
    @UiField
    Label timeLabel;
    @UiField
    Label dateLabel;
    @UiField
    Button downButton;
    @UiField
    FlowPanel selectionPanel;
    @UiField
    FlowPanel header;
    @UiField
    FlowPanel subHeader;
    @UiField
    FlowPanel buttons;

    private Timer repeatingTask;
    private SelectionType strategy = SelectionType.SELECT_DAY_IN_A_MONTH;
    private Map<SelectionType, CalendarStrategy> renderingStrategiesMap = new HashMap<>();

    public Calendar(CalendarCss css) {
        this.css = css;
        this.css.ensureInjected();

        initWidget(ourUiBinder.createAndBindUi(this));



        this.asWidget().addStyleName(css.main());

        this.header.addStyleName(css.header());
        this.timeLabel.addStyleName(css.timeLabel());
        this.dateLabel.addStyleName(css.dateLabel());

        this.subHeader.addStyleName(css.subHeader());
        this.strategyPicker.addStyleName(css.monthLabel());
        this.buttons.addStyleName(css.buttons());
        this.upButton.addStyleName(css.upButton());
        this.downButton.addStyleName(css.downButton());


        this.selectionPanel.addStyleName(css.daysPanel());

        this.dateLabel.setText(getFormat(css.dateLabelFormat()).format(new Date()));
        updateTimeLabel(new Date());

        if (this.repeatingTask == null) {
            this.repeatingTask = scheduleRepeating(() -> updateTimeLabel(new Date()), 1000);
        }

        asList(SelectionType.values()).forEach(strategy -> {
            CalendarStrategy calendarStrategy = CalendarStrategy.from(strategy)
                    .withCss(css);
            renderingStrategiesMap.put(strategy, calendarStrategy);
            calendarStrategy.addValueChangeHandler(event -> {
                date = event.getValue().getDate();
                setStrategy(event.getValue().getSelectionStrategies());
            });
        });

        render(new Date());
    }

    private void updateTimeLabel(Date date) {
        timeLabel.setText(getFormat(css.timeLabelFormat()).format(date));
    }

    private void render(Date date) {
        this.date = date;

        CalendarStrategy calendarStrategy = renderingStrategiesMap.get(strategy);

        Logger.getLogger("").info("render = " + calendarStrategy.getClass().getName());
        calendarStrategy.withDate(date);
        calendarStrategy.drawSelectionPanel(selectionPanel);

        strategyPicker.setText(calendarStrategy.getSubheaderLabel());


    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return super.addHandler(handler, ValueChangeEvent.getType());
    }

    @UiHandler("upButton")
    void onUpButtonClick(ClickEvent event) {
        CalendarStrategy calendarStrategy = renderingStrategiesMap.get(strategy);
        calendarStrategy.onUpButtonClick();
        render(calendarStrategy.getDate());

    }

    @UiHandler("downButton")
    void onDownButtonClick(ClickEvent event) {
        CalendarStrategy calendarStrategy = renderingStrategiesMap.get(strategy);
        calendarStrategy.onDownButtonClick();
        render(calendarStrategy.getDate());
    }

    @UiHandler("strategyPicker")
    void onMonthLabelClick(ClickEvent event) {
        setStrategy(this.strategy.up());
    }

    private void setStrategy(SelectionType strategy) {
        this.strategy = strategy;
        render(date);
    }

}