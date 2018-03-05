package com.github.joraclista.client.ui.widgets.calendar;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum SelectionStrategies {
    SELECT_DAY_IN_A_MONTH,
    SELECT_MONTH_IN_A_YEAR,
    SELECT_YEAR;

    public SelectionStrategies next() {
        switch (this) {
            case SELECT_DAY_IN_A_MONTH: return SELECT_MONTH_IN_A_YEAR;
            case SELECT_MONTH_IN_A_YEAR: return SELECT_YEAR;
            case SELECT_YEAR: return SELECT_DAY_IN_A_MONTH;
        }
        return this;
    }
}
