package com.github.joraclista.client.snippets.bundle;

import com.google.gwt.resources.client.CssResource;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface CodeSnippetCss extends CssResource {

    String main();

    String snippet();

    String numbered();

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
}
