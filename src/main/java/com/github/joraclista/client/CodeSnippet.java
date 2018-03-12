package com.github.joraclista.client;

import com.github.joraclista.client.snippets.SnippetsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Alisa
 * version 1.0.
 */
public class CodeSnippet extends Composite {
    interface CodeSnippetUiBinder extends UiBinder<FlowPanel, CodeSnippet> {
    }

    private static CodeSnippetUiBinder ourUiBinder = GWT.create(CodeSnippetUiBinder.class);
    @UiField
    Element code;

    public CodeSnippet() {
        initWidget(ourUiBinder.createAndBindUi(this));
        code.setInnerHTML(Arrays.stream(SnippetsBundle.BUNDLE.calendar().getText()
                .split("\n"))
        .map(line -> {
            line = line
                    .replace("new ", " <span class='new'>new </span> ")
                    .replace(" private ", " <span class='modifier'> private </span> ")
                    .replace(" public ", " <span class='modifier'> public </span> ")
                    .replace(" protected ", " <span class='modifier'> protected </span> ")
                    .replace(" static ", " <span class='keyword'> static </span> ")
                    .replace(" void ", " <span class='keyword'> void </span> ")
                    .replace(" interface ", " <span class='keyword'> interface </span> ")
                    .replace(" extends ", " <span class='keyword'> extends </span> ")
                    .replace(" class ", " <span class='keyword'> class </span> ");
            //.replace("\".*\"", "<span class='string'>new </span>");



            return "<span  class='line'>" + line + "</span>";
        })
        .collect(Collectors.joining("\n")));
    }
}