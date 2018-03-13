package com.github.joraclista.client.ui.widgets.radio.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface RadioButtonBundle extends ClientBundle {

    RadioButtonBundle BUNDLE = GWT.create(RadioButtonBundle.class);

    interface Css extends CssResource {
        String panel();

        String check();

        String label();

        String checked();

        String disabled();

        String unchecked();
    }

    interface CheckCss extends Css {}
    interface RectCss extends Css {}

    @Source("style.css")
    CheckCss css();

    @Source("rect-style.css")
    RectCss rectangledRadioButtonCss();

}
