package com.github.joraclista.client.ui.widgets.contact;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum ContactType {
    MOBILE("Mobile"), PHONE("Phone"), EMAIL("Email"), WEBSITE("Website");

    private String type;


    ContactType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
