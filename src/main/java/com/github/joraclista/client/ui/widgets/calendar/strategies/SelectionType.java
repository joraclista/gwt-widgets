package com.github.joraclista.client.ui.widgets.calendar.strategies;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum SelectionType {
    NONE,
    SELECT_DAY_IN_A_MONTH,
    SELECT_MONTH_IN_A_YEAR,
    SELECT_YEAR_IN_YEARS;

    public SelectionType up() {
        switch (this) {
            case SELECT_DAY_IN_A_MONTH: return SELECT_MONTH_IN_A_YEAR;
            case SELECT_MONTH_IN_A_YEAR: return SELECT_YEAR_IN_YEARS;
            case SELECT_YEAR_IN_YEARS: return SELECT_DAY_IN_A_MONTH;
        }
        return this;
    }

    public SelectionType down() {
        switch (this) {
            case SELECT_DAY_IN_A_MONTH: return NONE;
            case SELECT_MONTH_IN_A_YEAR: return SELECT_DAY_IN_A_MONTH;
            case SELECT_YEAR_IN_YEARS: return SELECT_MONTH_IN_A_YEAR;
        }
        return this;
    }
}
