package com.github.joraclista.client.ui.widgets.calendar;

import com.github.joraclista.client.ui.widgets.calendar.strategies.SelectionType;

import java.util.Date;

/**
 * Created by Alisa
 * version 1.0.
 */
public class RedrawModel {
    private Date date;
    private SelectionType selectionType;

    public RedrawModel(Date date, SelectionType selectionType) {
        this.date = date;
        this.selectionType = selectionType;
    }

    public Date getDate() {
        return date;
    }

    public SelectionType getSelectionType() {
        return selectionType;
    }
}
