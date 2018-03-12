package com.github.joraclista.client.ui.widgets.notification;

import com.github.joraclista.client.ui.widgets.notification.css.NotificationCss;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum  NotificationType {
    OK {
        @Override
        String style(NotificationCss css) {
            return css.check();
        }
    },
    INFO {
        @Override
        String style(NotificationCss css) {
            return css.info();
        }
    },
    WARNING {
        @Override
        String style(NotificationCss css) {
            return css.exclamation();
        }
    },
    ERROR {
        @Override
        String style(NotificationCss css) {
            return css.exclamation();
        }
    },
    NONE {
        @Override
        String style(NotificationCss css) {
            return css.vanilla();
        }
    };

    abstract String style(NotificationCss css);
}
