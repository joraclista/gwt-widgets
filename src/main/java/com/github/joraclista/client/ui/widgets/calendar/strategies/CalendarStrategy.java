package com.github.joraclista.client.ui.widgets.calendar.strategies;

import com.github.joraclista.client.ui.widgets.calendar.RedrawModel;
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

/**
 * Created by Alisa
 * version 1.0.
 */
public abstract class CalendarStrategy implements HasValueChangeHandlers<RedrawModel> {

    protected CalendarCss css = CalendarBundle.BUNDLE.calendarCss();
    protected Date date;
    private HandlerManager handlerManager;

    public CalendarStrategy withCss(CalendarCss css) {
        this.css = css;
        return this;
    }
    public CalendarStrategy withDate(Date date) {
        this.date = date;
        return this;
    }

    public abstract void  onUpButtonClick();

    public abstract void onDownButtonClick();

    public abstract String getSubheaderLabel();

    public abstract void drawSelectionPanel(FlowPanel selectionPanel);

    public static CalendarStrategy from(SelectionType selectionStrategies) {
        switch (selectionStrategies) {
            case SELECT_DAY_IN_A_MONTH: return new MonthStrategy();
            case SELECT_YEAR: return new YearStrategy();
            case SELECT_MONTH_IN_A_YEAR: return new YearStrategy();
        }
        return new MonthStrategy();
    }


    public Date getDate() {
        return date;
    }


    public CalendarCss getCss() {
        return css;
    }

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
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<RedrawModel> handler) {
        return ensureHandlers().addHandler(ValueChangeEvent.getType(), handler);
    }
}
