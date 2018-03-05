package com.github.joraclista.client.ui.widgets.calendar.strategies;

import com.github.joraclista.client.ui.widgets.calendar.RenderModel;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.google.gwt.event.logical.shared.ValueChangeEvent.fire;
import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;
import static com.google.gwt.user.datepicker.client.CalendarUtil.addMonthsToDate;
import static com.google.gwt.user.datepicker.client.CalendarUtil.copyDate;
import static java.util.stream.IntStream.range;

/**
 * Created by Alisa
 * version 1.0.
 */
class YearInYearsStrategy extends CalendarStrategy {
    private static final int DEFAULT_OFFSET = 10;

    private int offset = DEFAULT_OFFSET;

    private List<HandlerRegistration> handlers = new LinkedList<>();

    @Override
    public void onUpButtonClick() {
        addMonthsToDate(date, -12 * offset);
    }

    @Override
    public void onDownButtonClick() {
        addMonthsToDate(date, 12 * offset);
    }

    @Override
    public String getSubheaderLabel() {
        return getFormat(css.yearLabelFormat()).format(getStartDate()) + "-" + getFormat(css.yearLabelFormat()).format(getEndDate());
    }

    @Override
    public void drawSelectionPanel(FlowPanel selectionPanel) {
        selectionPanel.clear();

        handlers.forEach(handler -> handler.removeHandler());
        handlers.clear();

        selectionPanel.setStyleName(css.daysPanel(), false);
        selectionPanel.setStyleName(css.monthSelectPanel(), false);
        selectionPanel.setStyleName(css.yearSelectPanel(), true);


        range(-offset, offset).forEach(_offset -> {
            Date _date = copyDate(date);
            addMonthsToDate(_date, _offset * 12);
            Label label = new Label(getFormat(css.yearLabelFormat()).format(_date));
            label.setStyleName(css.selected(), _date.getYear() == new Date().getYear());

            selectionPanel.add(label);

            handlers.add(label.addClickHandler(event -> fire(YearInYearsStrategy.this, new RenderModel(_date, getSelectionType().down()))));
        });
    }

    private Date getStartDate() {
        Date _date = copyDate(this.date);
        addMonthsToDate(_date, -12 * offset);
        return _date;
    }

    private Date getEndDate() {
        Date _date = copyDate(this.date);
        addMonthsToDate(_date, 12 * offset);
        return _date;
    }

    public YearInYearsStrategy withOffset(int offset) {
        this.offset = offset;
        return this;
    }


    @Override
    protected SelectionType getSelectionType() {
        return SelectionType.SELECT_MONTH_IN_A_YEAR;
    }

}
