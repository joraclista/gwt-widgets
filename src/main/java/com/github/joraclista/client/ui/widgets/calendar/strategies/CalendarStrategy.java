package com.github.joraclista.client.ui.widgets.calendar.strategies;

import com.github.joraclista.client.ui.widgets.calendar.DaysName;
import com.github.joraclista.client.ui.widgets.calendar.RenderModel;
import com.github.joraclista.client.ui.widgets.calendar.css.CalendarBundle;
import com.github.joraclista.client.ui.widgets.calendar.css.CalendarCss;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alisa
 * version 1.0.
 */
public abstract class CalendarStrategy implements HasValueChangeHandlers<RenderModel> {
    protected List<HandlerRegistration> handlers = new LinkedList<>();
    protected CalendarCss css = CalendarBundle.BUNDLE.calendarCss();
    protected Date date;
    private HandlerManager handlerManager;
    protected DaysName startOfWeek;
    protected Map<DaysName, String> dayNamesHeadersMap;

    public CalendarStrategy withCss(CalendarCss css) {
        this.css = css;
        return this;
    }
    public CalendarStrategy withDate(Date date) {
        this.date = date;
        return this;
    }
    public CalendarStrategy withStartOfWeek(DaysName startOfWeek) {
        this.startOfWeek = startOfWeek;
        return this;
    }

    public CalendarStrategy withDayNamesHeadersMap(Map<DaysName, String> dayNamesHeadersMap) {
        if (dayNamesHeadersMap == null || dayNamesHeadersMap.size() != DaysName.values().length) {
            throw new IllegalArgumentException("Days Names Header Map should have all possible day names");
        }
        this.dayNamesHeadersMap = dayNamesHeadersMap;
        return this;
    }

    public abstract void  onUpButtonClick();

    public abstract void onDownButtonClick();

    public abstract String getSubheaderLabel();

    public abstract void drawSelectionPanel(FlowPanel selectionPanel);

    public static CalendarStrategy from(SelectionType selectionStrategies) {
        switch (selectionStrategies) {
            case SELECT_DAY_IN_A_MONTH: return new DayInMonthStrategy();
            case SELECT_YEAR_IN_YEARS: return new YearInYearsStrategy();
            case SELECT_MONTH_IN_A_YEAR: return new MonthInAYearStrategy();
        }
        return new DayInMonthStrategy();
    }


    public Date getDate() {
        return date;
    }


    public CalendarCss getCss() {
        return css;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        ensureHandlers().fireEvent(event);
    }

    private HandlerManager ensureHandlers() {
        return handlerManager == null ? handlerManager = createHandlerManager()
                : handlerManager;
    }

    protected HandlerManager createHandlerManager() {
        return new HandlerManager(this);
    }


    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<RenderModel> handler) {
        return ensureHandlers().addHandler(ValueChangeEvent.getType(), handler);
    }

    protected abstract SelectionType getSelectionType();


    protected void clearHandlers() {
        handlers.forEach(handler -> handler.removeHandler());
        handlers.clear();
    }
}
