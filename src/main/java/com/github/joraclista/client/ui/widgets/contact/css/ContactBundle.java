package com.github.joraclista.client.ui.widgets.contact.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface ContactBundle extends ClientBundle {
    ContactBundle BUNDLE = GWT.create(ContactBundle.class);

    @Source({"contact.css"})
    ContactCss contactCss();
}
