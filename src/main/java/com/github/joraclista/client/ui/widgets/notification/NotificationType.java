package com.github.joraclista.client.ui.widgets.notification;

import com.github.joraclista.client.ui.widgets.notification.css.NotificationCss;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Alisa
 * version 1.0.
 */
public enum  NotificationType {
    SUCCESS {
        @Override
        List<String> style(NotificationCss css) {
            return asList(css.check(), css.success());
        }
    },
    INFO {
        @Override
        List<String> style(NotificationCss css) {
            return asList(css.info());
        }
    },
    WARNING {
        @Override
        List<String> style(NotificationCss css) {
            return asList(css.exclamation(), css.warning());
        }
    },
    ERROR {
        @Override
        List<String> style(NotificationCss css) {
            return asList(css.exclamation(), css.error());
        }
    },
    NONE {
        @Override
        List<String> style(NotificationCss css) {
            return asList(css.vanilla());
        }
    };

    abstract List<String> style(NotificationCss css);
}
