package com.github.joraclista.client.snippets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface SnippetsBundle extends ClientBundle {

    SnippetsBundle BUNDLE = GWT.create(SnippetsBundle.class);

    @Source("calendar-snippet.txt")
    TextResource calendar();

    @Source("notification-snippet.txt")
    TextResource notification();
}
