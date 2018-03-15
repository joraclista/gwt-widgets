package com.github.joraclista.client.ui.common.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface CommonBundle extends ClientBundle {

    CommonBundle BUNDLE = GWT.create(CommonBundle.class);

    interface Css extends CssResource {

        String button();

        @ClassName("flex-column")
        String flexColumn();

        @ClassName("flex-row")
        String flexRow();

        String flex();

        String label();

        @ClassName("flex-row-reverse")
        String flexRowReverse();

        @ClassName("flex-column-reverse")
        String flexColumnReverse();

        @ClassName("padding-10")
        String padding10();

        @ClassName("v-padding-10")
        String vPadding10();

        @ClassName("h-padding-10")
        String hPadding10();
    }

    @Source("common.css")
    Css css();
}
