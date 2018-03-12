package com.github.joraclista.client.ui.widgets.contact;

import com.github.joraclista.client.ui.widgets.contact.css.ContactCss;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import static java.util.Optional.ofNullable;

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
        this.skillsPanel.addStyleName(css.skillsPanel());
        this.addInfo.addStyleName(css.addInfo());
        this.image.addStyleName(css.img());
        this.subheader.addStyleName(css.subheader());
    }

    public void setPerson(Person person) {
        this.name.setText(person.getFullName());
        this.profession.setText(person.getPosition());
        this.clients.setText(person.getNumOfClients() + "");
        this.projects.setText(person.getNumOfProjects() + "");
        this.image.setUrl(person.getImageUrl());
        renderSkillsPanel(person);
    }

    protected void renderSkillsPanel(Person person) {
        this.skillsPanel.clear();
        ofNullable(person.getSkills()).ifPresent(skills -> skills
                .forEach(item -> this.skillsPanel.add(new Label(item))));
    }
}