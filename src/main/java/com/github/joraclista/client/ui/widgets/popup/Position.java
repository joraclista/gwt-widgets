package com.github.joraclista.client.ui.widgets.popup;

import com.github.joraclista.client.ui.widgets.popup.css.PopupCss;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum Position {
    CENTER {
        @Override
        String style(PopupCss css) {
            return css.popupPositionCenterCenter();
        }
    },
    TOP_LEFT {
        @Override
        String style(PopupCss css) {
            return css.popupPositionTopLeft();
        }
    },
    TOP_RIGHT {
        @Override
        String style(PopupCss css) {
            return css.popupPositionTopRight();
        }
    },
    TOP_CENTER {
        @Override
        String style(PopupCss css) {
            return css.popupPositionTopCenter();
        }
    };

    abstract String style(PopupCss css);
}
