package com.github.joraclista.client.ui.widgets.tocPanel;

import com.github.joraclista.client.ui.widgets.tocPanel.css.TocCss;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum Layout {
    VERTICAL {
        @Override
        String style(TocCss css) {
            return css.vertical();
        }
    },
    HORIZONTAL {
        @Override
        String style(TocCss css) {
            return css.horizontal();
        }
    };

    abstract String style(TocCss css);
}
