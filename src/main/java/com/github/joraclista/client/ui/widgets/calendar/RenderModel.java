package com.github.joraclista.client.ui.widgets.calendar;

import com.github.joraclista.client.ui.widgets.calendar.strategies.SelectionType;

import java.util.Date;

/**
 * Created by Alisa
 * version 1.0.
 */
public class RenderModel {
    private Date date;
    private SelectionType selectionType;

    public RenderModel(Date date, SelectionType selectionType) {
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
