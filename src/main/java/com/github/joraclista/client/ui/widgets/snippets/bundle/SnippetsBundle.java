package com.github.joraclista.client.ui.widgets.snippets.bundle;

import com.github.joraclista.client.ui.widgets.snippets.bundle.css.CodeSnippetCss;
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

    @Source("source/snippet-1.txt")
    TextResource snippet1();

    @Source("source/snippet-2.txt")
    TextResource snippet2();

    @Source({"css/dark-codeSnippet.css"})
    DarkCss darkCss();

    @Source({"css/light-codeSnippet.css"})
    LightCss lightCss();
}
