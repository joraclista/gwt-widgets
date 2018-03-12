package com.github.joraclista.client.snippets.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface SnippetsBundle extends ClientBundle {

    SnippetsBundle BUNDLE = GWT.create(SnippetsBundle.class);

    interface DarkCss extends CodeSnippetCss {}

    interface LightCss extends CodeSnippetCss {}

    @Source("calendar-snippet.txt")
    TextResource calendar();

    @Source("notification-snippet.txt")
    TextResource notification();

    @Source({"dark-codeSnippet.css"})
    DarkCss darkCss();

    @Source({"light-codeSnippet.css"})
    LightCss lightCss();
}
