package com.github.joraclista.client.ui.widgets.popup.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface PopupBundle extends ClientBundle {
    PopupBundle BUNDLE = GWT.create(PopupBundle.class);

    @Source({"popup.css"})
    PopupCss css();
}
