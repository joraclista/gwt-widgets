package com.github.joraclista.client.ui.showcase;

import com.github.joraclista.client.ui.widgets.checkbox.CheckBox;
import com.github.joraclista.client.ui.widgets.popup.Popup;
import com.github.joraclista.client.ui.widgets.popup.Position;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Created by Alisa
 * version 1.0.
 */
public class PopupConfig extends Composite {
    interface PopupConfigUiBinder extends UiBinder<FlowPanel, PopupConfig> {
    }

    private static PopupConfigUiBinder ourUiBinder = GWT.create(PopupConfigUiBinder.class);
    @UiField
    CheckBox autoHide;
    @UiField
    CheckBox bgClick;
    @UiField
    CheckBox addBackground;
    @UiField
    CheckBox enableCloseButton;

    private Popup popup = new Popup();

    public PopupConfig() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("showPopup")
    void showPopup(ClickEvent event) {
        popup
                .withAutoHideEnabled(autoHide.isChecked())
                .withCloseOnBackgroundClick(bgClick.isChecked())
                .withAppliedBackground(addBackground.isChecked())
                .withCloseButtonVisibility(enableCloseButton.isChecked())
                .show();
    }

    @UiHandler("TOP_CENTER")
    void onPositionChange1(ClickEvent event) {
        popup.withPosition(Position.TOP_CENTER);
    }

    @UiHandler("TOP_LEFT")
    void onPositionChange2(ClickEvent event) {
        popup.withPosition(Position.TOP_LEFT);
    }

    @UiHandler("TOP_RIGHT")
    void onPositionChange3(ClickEvent event) {
        popup.withPosition(Position.TOP_RIGHT);
    }

    @UiHandler("CENTER")
    void onPositionChange4(ClickEvent event) {
        popup.withPosition(Position.CENTER);
    }
}