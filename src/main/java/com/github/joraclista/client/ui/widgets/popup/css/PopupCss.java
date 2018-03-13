package com.github.joraclista.client.ui.widgets.popup.css;

import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface PopupCss extends CssResource {

    String popup();

    @ClassName("popup-content")
    String content();

    @ClassName("popup-close-button")
    String closeButton();

    @ClassName("popup-with-background")
    String withBackground();

    @ClassName("popup-position-top-left")
    String popupPositionTopLeft();

    @ClassName("popup-position-center-center")
    String popupPositionCenterCenter();

    @ClassName("popup-position-top-right")
    String popupPositionTopRight();

    @ClassName("popup-position-top-center")
    String popupPositionTopCenter();

    String animate();
}
