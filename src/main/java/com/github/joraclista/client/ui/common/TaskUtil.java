package com.github.joraclista.client.ui.common;

import com.google.gwt.user.client.Timer;

/**
 * Created by Alisa
 * version 1.0.
 */
public final class TaskUtil {

    private TaskUtil() {}

    public static Timer schedule(Runnable runnable) {
        Timer timer = new Timer() {

            @Override
            public void run() {
                runnable.run();
            }
        };
        timer.run();
        return timer;
    }

    public static Timer scheduleWithDelay(Runnable runnable, int delayMs) {
        Timer timer = new Timer() {

            @Override
            public void run() {
                runnable.run();
            }
        };
        timer.schedule(delayMs);
        return timer;
    }

    public static Timer scheduleRepeating(Runnable runnable, int repeatPeriod) {
        Timer timer = new Timer() {

            @Override
            public void run() {
                runnable.run();
            }
        };
        timer.scheduleRepeating(repeatPeriod);
        return timer;
    }
}
