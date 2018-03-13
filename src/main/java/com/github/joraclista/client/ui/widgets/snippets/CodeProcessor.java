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
            .replaceAll("(//)(.*)", "<span class='" + item.getCss().comment() + "'>$1$2</span>")
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
            .replaceAll("([\\s{1,}(=])(new )", "$1<span class='" + item.getCss().keyword() + "'>$2</span>")
    );

    CodeProcessor DIGITS_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("([\\s{1,}(=;])([0-9]{1}[0-9]{0,})([\\s{1,})=;])", "$1<span class='" + item.getCss().digits() + "'>$2</span>$3")
    );
    CodeProcessor IMPORT_PROCESSOR = item -> item.withLine(
            item.getLine()
            .replaceAll("(\\s{0,})(package |import\\s{1,}static |import )", "<span class='" + item.getCss().keyword() + "'>$2</span>")
    );
    CodeProcessor THIS_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("(\\s{0,})(return\\s{1,}this)([\\s{0,};])", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
                    .replaceAll("([(=\\s{1,}])(this)([);])", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
                    .replaceAll("([(=\\s{1,}])(this\\.)(.*)", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
    );
    CodeProcessor CAPITAL_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("([\\s{1,}])([A-Z_]{1,}[A-Z_0-9]{0,})([\\s{1,}.])", "$1<span class='" + item.getCss().capital() + "'>$2</span>$3")
                    .replaceAll("([(=])([A-Z_]{1,}[A-Z_0-9]{0,})([);=])", "$1<span class='" + item.getCss().capital() + "'>$2</span>$3")
                    .replaceAll("(\\.[A-Z_]{1,}[A-Z_0-9]{0,})(\\W{1,})", "<span class='" + item.getCss().capital() + "'>$1</span>$2")
    );
    CodeProcessor ANNOTATION_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("(@\\w{2,})", "<span class='" + item.getCss().annotation() + "'>$1</span>")
    );
    CodeProcessor KEYWORDS_1_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("( class |^class | interface |^interface |extends |implements )", "<span class='" + item.getCss().keyword() + "'>$1</span>")
    );
    CodeProcessor KEYWORDS_2_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("([\\s{1}(&|=])(true|false)([\\s{1})&|=])", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
    );
    CodeProcessor KEYWORDS_3_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("(\\s{1,})(if|else|while|for)([\\s{1,}({])", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
    );
    CodeProcessor KEYWORDS_4_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("(^public final|^public static|^private final|^private static|^final|^static)(\\s{1})", "<span class='" + item.getCss().keyword() + "'>$1</span>$2")
                    .replaceAll("(\\s{1})(public final|public static|private final|private static|final|static)(\\s{1})", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
                    .replaceAll("(^void)(\\s{1})", "<span class='" + item.getCss().keyword() + "'>$1</span>$2")
                    .replaceAll("(\\s{1})(void)(\\s{1})", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
                    .replaceAll("(^native)(\\s{1})", "<span class='" + item.getCss().keyword() + "'>$1</span>$2")
                    .replaceAll("(\\s{1})(native)(\\s{1})", "$1<span class='" + item.getCss().keyword() + "'>$2</span>$3")
    );
    CodeProcessor MODIFIER_PROCESSOR = item -> item.withLine(
            item.getLine()
                    .replaceAll("(^private|^public|^package)(\\s{1})", "<span class='" + item.getCss().modifier() + "'>$1</span>$2")
                    .replaceAll("(\\s{1})(private|public|package)(\\s{1})", "$1<span class='" + item.getCss().modifier() + "'>$2</span>$3")
    );
    Processor CODE_LINE_PROCESSORS = item -> SYMBOLS_ESCAPE
            .andThen(DIGITS_PROCESSOR)
            .andThen(IMPORT_PROCESSOR)
            .andThen(PUNCTUATION_PROCESSOR)
            .andThen(THIS_PROCESSOR)
            .andThen(CAPITAL_PROCESSOR)
            .andThen(STRINGS_PROCESSOR)
            .andThen(MODIFIER_PROCESSOR)
            .andThen(ANNOTATION_PROCESSOR)
            .andThen(KEYWORDS_1_PROCESSOR)
            .andThen(KEYWORDS_2_PROCESSOR)
            .andThen(KEYWORDS_3_PROCESSOR)
            .andThen(KEYWORDS_4_PROCESSOR)
            .andThen(NEW_KEYWORD_PROCESSOR)
            .andThen(_item -> _item.getLine())
            .apply(item);

    Processor COMMENT_LINE_PROCESSORS = item -> SYMBOLS_ESCAPE
            .andThen(COMMENT_PROCESSOR)
            .andThen(_item -> _item.getLine())
            .apply(item);

}
