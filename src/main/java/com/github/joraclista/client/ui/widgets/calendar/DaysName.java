package com.github.joraclista.client.ui.widgets.calendar;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum DaysName {
    SU(0, "Su"), MO(1, "Mo"), TU(2, "Tu"), WE(3, "We"), TH(4, "Th"), FR(5, "Fr"), SA(6, "Sa");

    private final int idx;
    private final String headerName;

    DaysName(int idx, String headerName) {
        this.idx = idx;
        this.headerName = headerName;
    }


    public int getIdx() {
        return idx;
    }

    public String getHeaderName() {
        return headerName;
    }

}
