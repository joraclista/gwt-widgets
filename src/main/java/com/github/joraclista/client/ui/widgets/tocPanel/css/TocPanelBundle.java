package com.github.joraclista.client.ui.widgets.tocPanel.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface TocPanelBundle extends ClientBundle {

    TocPanelBundle BUNDLE = GWT.create(TocPanelBundle.class);

    interface Css extends CssResource {

        String toc();

        String main();

        String content();

        String tocContainer();
    }

    @Source("toc.css")
    Css css();

    @Source("group.css")
    GroupCss groupCss();
}
