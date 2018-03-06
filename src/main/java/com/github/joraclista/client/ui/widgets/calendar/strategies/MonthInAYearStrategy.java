package com.github.joraclista.client.ui.widgets.calendar.strategies;

import com.github.joraclista.client.ui.widgets.calendar.RenderModel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.Date;

import static com.google.gwt.event.logical.shared.ValueChangeEvent.fire;
import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;
import static com.google.gwt.user.datepicker.client.CalendarUtil.addMonthsToDate;
import static java.util.stream.IntStream.range;

/**
 * Created by Alisa
 * version 1.0.
 */
class MonthInAYearStrategy extends CalendarStrategy {


    @Override
    public void onUpButtonClick() {
        addMonthsToDate(date, -12);
    }

    @Override
    public void onDownButtonClick() {
        addMonthsToDate(date, 12);
    }

    @Override
    public String getSubheaderLabel() {
        return getFormat("yyyy").format(date);
    }

    @Override
    public void drawSelectionPanel(FlowPanel selectionPanel) {
        selectionPanel.clear();
        clearHandlers();
        selectionPanel.setStyleName(css.daysPanel(), false);
        selectionPanel.setStyleName(css.monthSelectPanel(), true);
        selectionPanel.setStyleName(css.yearSelectPanel(), false);

        String[] months = css.monthShortcut().split(" ");

        range(0, months.length).forEach(monthIdx -> {
            Label monthLabel = new Label(months[monthIdx]);
            monthLabel.setStyleName(css.selected(), monthIdx == new Date().getMonth() && date.getYear() == new Date().getYear());
            selectionPanel.add(monthLabel);

            handlers.add(monthLabel.addClickHandler(event -> {
                Date newDate = date;
                date.setMonth(monthIdx);
                fire(MonthInAYearStrategy.this, new RenderModel(newDate, getSelectionType().down()));
            }));
        });
    }

    public int getYear() {
        return date.getYear() + 1900;
    }

    @Override
    protected SelectionType getSelectionType() {
        return SelectionType.SELECT_MONTH_IN_A_YEAR;
    }

}
