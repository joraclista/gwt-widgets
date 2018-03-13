package com.github.joraclista.client.ui.widgets.tocPanel.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface TocPanelBundle extends ClientBundle {

    TocPanelBundle BUNDLE = GWT.create(TocPanelBundle.class);

    @Source("toc.css")
    TocCss css();

    @Source("group.css")
    GroupCss groupCss();
}
