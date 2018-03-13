package com.github.joraclista.client.ui.widgets.snippets.bundle.css;

import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface CodeSnippetCss extends CssResource {

    String main();

    String snippet();

    String numbered();

    String copy();

    String annotation();

    String capital();

    String string();

    String line();

    String modifier();

    String comment();

    String punct();

    String keyword();

    @ClassName("snippet-name")
    String snippetName();

    String pre();

    String code();

    String digits();
}
