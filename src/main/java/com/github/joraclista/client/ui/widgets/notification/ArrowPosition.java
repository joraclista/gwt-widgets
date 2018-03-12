package com.github.joraclista.client.ui.widgets.notification;

import com.github.joraclista.client.ui.widgets.notification.css.NotificationCss;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum ArrowPosition {
    TOP {
        @Override
        String style(NotificationCss css) {
            return css.arrowTop();
        }
    },
    BOTTOM {
        @Override
        String style(NotificationCss css) {
            return css.arrowBottom();
        }
    },
    LEFT {
        @Override
        String style(NotificationCss css) {
            return css.arrowLeft();
        }
    },
    RIGHT {
        @Override
        String style(NotificationCss css) {
            return css.arrowRight();
        }
    },
    NONE {
        @Override
        String style(NotificationCss css) {
            return css.arrowNone();
        }
    };

    abstract String style(NotificationCss css);
}
