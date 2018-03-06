package com.github.joraclista.client.ui.widgets.contact;

import com.github.joraclista.client.ui.widgets.contact.css.ContactCss;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * Created by Alisa
 * version 1.0.
 */
public class Contact extends Composite {


    interface ContactUiBinder extends UiBinder<FlowPanel, Contact> {
    }

    private static ContactUiBinder ourUiBinder = GWT.create(ContactUiBinder.class);

    private final ContactCss css;

    @UiField
    Image image;
    @UiField
    FlowPanel header;
    @UiField
    Label name;
    @UiField
    Label profession;
    @UiField
    FlowPanel contactInfo;
    @UiField
    Label infoType;
    @UiField
    Label info;
    @UiField
    Label addInfo;
    @UiField
    FlowPanel main;


    public Contact(ContactCss css) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.css = css;
        this.css.ensureInjected();
        this.asWidget().addStyleName(css.container());
        this.main.addStyleName(css.main());
        this.header.addStyleName(css.header());
        this.name.addStyleName(css.name());
        this.profession.addStyleName(css.profession());
        this.contactInfo.addStyleName(css.contactInfo());
        this.infoType.addStyleName(css.infoType());
        this.info.addStyleName(css.info());
        this.addInfo.addStyleName(css.addInfo());
        this.image.addStyleName(css.img());
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setProfession(String profession) {
        this.profession.setText(profession);
    }

    public void addContactInfo(String contactInfo) {
        this.info.setText(contactInfo);
    }

    public void setImageUrl(String url) {
        this.image.setUrl(url);
    }
}