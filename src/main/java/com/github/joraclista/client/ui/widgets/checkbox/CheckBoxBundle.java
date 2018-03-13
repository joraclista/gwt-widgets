package com.github.joraclista.client.ui.widgets.checkbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface CheckBoxBundle extends ClientBundle {

    CheckBoxBundle BUNDLE = GWT.create(CheckBoxBundle.class);

    interface Css extends CssResource {
        String panel();

        String check();

        String label();

        String checked();

        String disabled();
    }

    @Source("css.css")
    Css css();
}
