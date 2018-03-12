package com.github.joraclista.client;

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

    public CodeSnippet(String text) {
        initWidget(ourUiBinder.createAndBindUi(this));
        code.setInnerHTML(Arrays.stream(text.split("\n"))
                .map(line -> {
                    line = line
                            .replaceAll("<", "&lt")
                            .replaceAll(">", "&gt");

                    if (line.trim().startsWith("//")) {
                        line = line.replaceAll("(//)(.*)", "<span class='comment'>$1$2</span>");
                    } else {
                        line = line
                                .replaceAll("(;)", "<span class='punct'>$1</span>")
                                .replaceAll("([(])(this)([)])", "$1<span class='keyword'>$2</span>$3")
                                .replaceAll("(\\.[A-Z_]{1,})(\\W{1,})", "<span class='capital'>$1</span>$2")
                                .replaceAll("(@\\w{2,})", "<span class='annotation'>$1</span>")
                                .replaceAll("([\\s{1}(])(new)(\\s{1})", "$1<span class='new'>$2</span>$3")
                                .replaceAll("(private|public|package)(\\s{1})", "<span class='modifier'>$1</span>$2")
                                .replaceAll("(\\s{1,})(if|else|while|for)(\\s{1,})", "$1<span class='keyword'>$2</span>$3")
                                .replaceAll("\\s{0,1}(void)\\s{1}", "<span class='keyword'> $1 </span>")
                                .replaceAll("(\".*\")", "<span class='string'>$1</span>")
                                .replaceAll("(^package|^import)(\\s+.*;)", "<span class='keyword'>$1</span>$2")
                                .replaceAll("([\\s{1}(&|=])(true|false)([\\s{1})&|=])", "$1<span class='keyword'>$2</span>$3")
                                .replaceAll("(\\s{1})(\\.class|class|interface|extends|implements|static)(\\s{1,})", "$1<span class='keyword'>$2</span>$3");

                    }

                    return "<span  class='line'>" + line + "</span>";
                })
                .collect(Collectors.joining("\n")));
    }
}