package com.github.joraclista.client.ui.widgets.calendar;

import java.util.Date;

/**
 * Created by Alisa
 * version 1.0.
 */
public class RedrawModel {
    private Date date;
    private SelectionStrategies selectionStrategies;

    public RedrawModel(Date date, SelectionStrategies selectionStrategies) {
        this.date = date;
        this.selectionStrategies = selectionStrategies;
    }

    public Date getDate() {
        return date;
    }

    public SelectionStrategies getSelectionStrategies() {
        return selectionStrategies;
    }
}
