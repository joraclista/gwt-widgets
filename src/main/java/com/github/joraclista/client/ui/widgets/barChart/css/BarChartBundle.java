package com.github.joraclista.client.ui.widgets.barChart.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface BarChartBundle extends ClientBundle {

    BarChartBundle BUNDLE = GWT.create(BarChartBundle.class);

    interface Css extends CssResource {

        String bar();

        String label();

        String container();

        @ClassName("bar-holder")
        String barHolder();


        String defaultBg1();
        String defaultBg2();
        String invalidBg();

        String axis();
    }


    @Source("style.css")
    Css css();

}
