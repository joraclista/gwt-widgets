package com.github.joraclista.client.ui.widgets.contact;

import com.github.joraclista.client.ui.widgets.contact.css.ContactCss;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

/**
 * Created by Alisa
 * version 1.0.
 */
public class CvContact extends Composite {


    interface ContactUiBinder extends UiBinder<FlowPanel, CvContact> {
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
    Label addInfo;
    @UiField
    FlowPanel main;
    @UiField
    FlowPanel subheader;
    @UiField
    Label clients;
    @UiField
    Label projects;
    @UiField
    FlowPanel skillsPanel;


    public CvContact(ContactCss css) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.css = css;
        this.css.ensureInjected();
        this.asWidget().addStyleName(css.container());
        this.main.addStyleName(css.main());
        this.header.addStyleName(css.header());
        this.name.addStyleName(css.name());
        this.profession.addStyleName(css.profession());
        this.skillsPanel.addStyleName(css.contactInfoPanel());
        this.addInfo.addStyleName(css.addInfo());
        this.image.addStyleName(css.img());
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setProfession(String profession) {
        this.profession.setText(profession);
    }

  
    public void setImageUrl(String url) {
        this.image.setUrl(url);
    }
}