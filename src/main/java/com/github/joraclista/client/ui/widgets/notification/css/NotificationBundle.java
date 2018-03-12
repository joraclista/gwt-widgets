package com.github.joraclista.client.ui.widgets.notification.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface NotificationBundle extends ClientBundle {
    NotificationBundle BUNDLE = GWT.create(NotificationBundle.class);

    @Source({"notification.css"})
    NotificationCss baseCss();
}
