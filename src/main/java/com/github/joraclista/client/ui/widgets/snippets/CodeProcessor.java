package com.github.joraclista.client.ui.widgets.snippets;

import java.util.function.Function;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface CodeProcessor extends Function<ProcessingItem, ProcessingItem> {

    interface Processor extends Function<ProcessingItem, String>{}

    CodeProcessor SYMBOLS_ESCAPE = item -> item.withLine(
            item.getLine()
                    .replaceAll("<", "&lt")
                    .replaceAll(">", "&gt")
    );

    CodeProcessor COMMENT_PROCESSOR = item -> item.withLine(
            item.getLine()
            .replaceAll("(//)(.*)", "<span class='" + item.getCss().punct() + "'>$1$2</span>")
    );

    CodeProcessor PUNCTUATION_PROCESSOR = item -> item.withLine(
            item.getLine()
            .replaceAll("(;)", "<span class='" + item.getCss().punct() + "'>$1</span>")
    );

    CodeProcessor STRINGS_PROCESSOR = item -> item.withLine(
            item.getLine()
            .replaceAll("(\"[^\"]{0,}\")", "<span class='" + item.getCss().string() + "'>$1</span>")
    );


    CodeProcessor NEW_KEYWORD_PROCESSOR = item -> item.withLine(
            item.getLine()
            .replaceAll("([\\s{1}(])(new)(\\s{1})", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
    );


    CodeProcessor IMPORT_PROCESSOR = item -> item.withLine(
            item.getLine()
            .replaceAll("(\\s{0,})(package|import)(\\s{1,}.*;)", "<span class='" + item.getCss().keyword() + "'>$2</span>$3")
    );

    Processor CODE_LINE_PROCESSORS = item -> SYMBOLS_ESCAPE
            .andThen(PUNCTUATION_PROCESSOR)
            .andThen(STRINGS_PROCESSOR)
            .andThen(IMPORT_PROCESSOR)
            .andThen(NEW_KEYWORD_PROCESSOR)
            .andThen(_item -> _item.getLine())
            .apply(item);
    Processor COMMENT_LINE_PROCESSORS = item -> SYMBOLS_ESCAPE
            .andThen(COMMENT_PROCESSOR)
            .andThen(_item -> _item.getLine())
            .apply(item);
}
