package com.github.joraclista.client.snippets;

import com.github.joraclista.client.snippets.bundle.CodeSnippetCss;
import com.github.joraclista.client.snippets.bundle.SnippetsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

/**
 * Created by Alisa
 * version 1.0.
 */
public class CodeSnippet extends Composite {

    private final CodeSnippetCss css;

    interface CodeSnippetBinder extends UiBinder<FlowPanel, CodeSnippet> {
    }


    public static CodeSnippetBinder BINDER = GWT.create(CodeSnippetBinder.class);

    @UiField
    Element code;
    @UiField
    Label snippetName;
    @UiField
    PreElement pre;
    @UiField
    FlowPanel main;
    @UiField
    HTMLPanel snippet;


    public CodeSnippet(String text, CodeSnippetCss css) {
        initWidget(BINDER.createAndBindUi(this));
        this.css = css;
        applyStyles(css);
        processCode(text);
    }

    private void applyStyles(CodeSnippetCss css) {
        this.css.ensureInjected();
        this.main.addStyleName(css.main());
        this.snippet.addStyleName(css.snippet());
        this.snippetName.addStyleName(css.snippetName());
        this.pre.addClassName(css.pre());
        this.code.addClassName(css.code());
    }

    public CodeSnippet(String text) {
        this(text, SnippetsBundle.BUNDLE.darkCss());
    }

    private void processCode(String text) {
        code.setInnerHTML(Arrays.stream(text.split("\n"))
                .map(line -> {
                    line = line
                            .replaceAll("<", "&lt")
                            .replaceAll(">", "&gt");

                    if (line.trim().startsWith("//")) {
                        line = line.replaceAll("(//)(.*)", "<span class='comment'>$1$2</span>");
                    } else {
                        line = line
                                .replaceAll("(;)", "<span class='" + css.punct() + "'>$1</span>")
                                .replaceAll("([(])(this)([)])", "$1<span class='" + css.keyword() + "'>$2</span>$3")
                                .replaceAll("(\\.[A-Z_]{1,})(\\W{1,})", "<span class='" + css.capital() + "'>$1</span>$2")
                                .replaceAll("(@\\w{2,})", "<span class='" + css.annotation() + "'>$1</span>")
                                .replaceAll("([\\s{1}(])(new)(\\s{1})", "$1<span class='" + css.keyword() + "'>$2</span>$3")
                                .replaceAll("(private|public|package)(\\s{1})", "<span class='" + css.modifier() + "'>$1</span>$2")
                                .replaceAll("(\\s{1,})(if|else|while|for)(\\s{1,})", "$1<span class='" + css.keyword() + "'>$2</span>$3")
                                .replaceAll("\\s{0,1}(void)\\s{1}", "<span class='" + css.keyword() + "'> $1 </span>")
                                .replaceAll("(\".*\")", "<span class='" + css.string() + "'>$1</span>")
                                .replaceAll("(^package|^import)(\\s+.*;)", "<span class='" + css.keyword() + "'>$1</span>$2")
                                .replaceAll("([\\s{1}(&|=])(true|false)([\\s{1})&|=])", "$1<span class='" + css.keyword() + "'>$2</span>$3")
                                .replaceAll("(\\s{1})(\\.class|class|interface|extends|implements|static)(\\s{1,})", "$1<span class='" + css.keyword() + "'>$2</span>$3");

                    }

                    return "<span  class='" + css.line() + "'>" + line + "</span>";
                })
                .collect(joining("\n")));
    }

    public CodeSnippet withSnippetName(String name) {
        this.snippetName.setVisible(name != null && !name.trim().isEmpty());
        this.snippetName.setText(name);
        return this;
    }

    public CodeSnippet withLineNumbersEnabled(boolean enabled) {
        this.main.setStyleName(css.numbered(), enabled);
        return this;
    }
}