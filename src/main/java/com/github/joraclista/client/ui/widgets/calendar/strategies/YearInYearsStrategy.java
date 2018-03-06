package com.github.joraclista.client.ui.widgets.calendar.strategies;

import com.github.joraclista.client.ui.widgets.calendar.RenderModel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.Date;

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


    private int leftOffset = 10;
    private int rightOffset = 0;

    @Override
    public void onUpButtonClick() {
        addMonthsToDate(date, -12 * 10);
    }

    @Override
    public void onDownButtonClick() {
       addMonthsToDate(date, 12 * 10);
    }

    @Override
    public String getSubheaderLabel() {
        return getFormat(css.yearLabelFormat()).format(getStartDate()) + "-" + getFormat(css.yearLabelFormat()).format(getEndDate());
    }

    @Override
    public void drawSelectionPanel(FlowPanel selectionPanel) {
        selectionPanel.clear();

        clearHandlers();

        selectionPanel.setStyleName(css.daysPanel(), false);
        selectionPanel.setStyleName(css.monthSelectPanel(), false);
        selectionPanel.setStyleName(css.yearSelectPanel(), true);

        int decOffset = this.date.getYear() % 10;

        range(-leftOffset - decOffset, (10 - decOffset) + rightOffset).forEach(_offset -> {
            Date _date = copyDate(date);
            addMonthsToDate(_date, _offset * 12);
            Label label = new Label(getFormat(css.yearLabelFormat()).format(_date));
            label.setStyleName(css.selected(), _date.getYear() == new Date().getYear());

            selectionPanel.add(label);

            handlers.add(label.addClickHandler(event -> fire(YearInYearsStrategy.this, new RenderModel(_date, getSelectionType().down()))));
        });
    }

    private Date getStartDate() {
        int decOffset = this.date.getYear() % 10;
        Date _date = copyDate(this.date);
        addMonthsToDate(_date, -12 * (decOffset + leftOffset));
        return _date;
    }

    private Date getEndDate() {
        int decOffset = this.date.getYear() % 10;
        Date _date = copyDate(this.date);
        addMonthsToDate(_date, 12 * ((10 - decOffset) + rightOffset - 1));
        return _date;
    }


    @Override
    protected SelectionType getSelectionType() {
        return SelectionType.SELECT_MONTH_IN_A_YEAR;
    }

}
