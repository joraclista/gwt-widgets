package com.github.joraclista.client.ui.widgets.snippets;

import com.github.joraclista.client.ui.widgets.snippets.bundle.css.CodeSnippetCss;
import com.github.joraclista.client.ui.widgets.snippets.bundle.SnippetsBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

import java.util.Arrays;

import static com.github.joraclista.client.ui.widgets.snippets.CodeProcessor.CODE_LINE_PROCESSORS;
import static com.github.joraclista.client.ui.widgets.snippets.CodeProcessor.COMMENT_LINE_PROCESSORS;
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
    @UiField
    Label copy;
    @UiField
    TextArea area;
    private String text;


    public CodeSnippet(String text, CodeSnippetCss css) {
        initWidget(BINDER.createAndBindUi(this));
        this.css = css;
        applyStyles(css);
        processCode(text);
        this.text = text;
        this.area.setText(text);
    }

    private void applyStyles(CodeSnippetCss css) {
        this.css.ensureInjected();
        this.main.addStyleName(css.main());
        this.snippet.addStyleName(css.snippet());
        this.snippetName.addStyleName(css.snippetName());
        this.pre.addClassName(css.pre());
        this.code.addClassName(css.code());
        this.copy.addStyleName(css.copy());
    }

    public CodeSnippet(String text) {
        this(text, SnippetsBundle.BUNDLE.darkCss());
    }

    private void processCode(String text) {
        code.setInnerHTML(Arrays.stream(text.split("\n"))
                .map(line -> {

                    if (line.trim().startsWith("//")) {
                        line = COMMENT_LINE_PROCESSORS.apply(new ProcessingItem(line, css));
                    } else {
                        line = CODE_LINE_PROCESSORS.apply(new ProcessingItem(line, css));
//                        line = line
//                             .replaceAll("(private|public|package)(\\s{1})", "<span class='" + css.modifier() + "'>$1</span>$2")
//                                .replaceAll("\\s{0,1}(void)\\s{1}", "<span class='" + css.keyword() + "'> $1 </span>")
//
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

    public CodeSnippet withCodeCopyButtonEnabled(boolean enabled) {
        this.copy.setVisible(enabled);
        return this;
    }

    @UiHandler("copy")
    void onCopy(ClickEvent event) {
        copy(this.area.getElement());
    }

    public static native void copy(Element elem) /*-{
        elem.select();
        $wnd.document.execCommand("Copy");
    }-*/;
}