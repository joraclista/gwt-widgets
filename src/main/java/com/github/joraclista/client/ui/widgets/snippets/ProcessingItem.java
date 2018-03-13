package com.github.joraclista.client.ui.widgets.snippets;

import com.github.joraclista.client.ui.widgets.snippets.bundle.css.CodeSnippetCss;

import java.util.Properties;

/**
 * Created by Alisa
 * version 1.0.
 */
public class ProcessingItem {

    private String line;
    private CodeSnippetCss css;

    public ProcessingItem(String line, CodeSnippetCss css) {
        this.line = line;
        this.css = css;
    }

    public ProcessingItem withLine(String line) {
        this.line = line;
        return this;
    }

    public ProcessingItem withCss(CodeSnippetCss css) {
        this.css = css;
        return this;
    }

    public String getLine() {
        return line;
    }

    public CodeSnippetCss getCss() {
        return css;
    }

    @Override
    public String toString() {
        return "ProcessingItem{" +
                "line='" + line + '\'' +
                ", css=" + css +
                '}';
    }
}
