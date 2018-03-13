package com.github.joraclista.client.ui.widgets.checkbox.css;

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

        String unchecked();
    }

    interface CheckCss extends Css {}
    interface RectCss extends Css {}
    interface SwitchCss extends Css {}

    @Source("style.css")
    CheckCss css();

    @Source("rect-style.css")
    RectCss rectangledCheckboxCss();

    @Source("switch-style.css")
    SwitchCss switchCheckboxCss();
}
