package com.github.joraclista.client.ui.widgets.anchorPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface AnchorPanelBundle extends ClientBundle {

    AnchorPanelBundle BUNDLE = GWT.create(AnchorPanelBundle.class);

    interface Css extends CssResource {
        String scroll();

        String wrapper();
    }

    @Source("anchorPanel.css")
    Css css();
}
