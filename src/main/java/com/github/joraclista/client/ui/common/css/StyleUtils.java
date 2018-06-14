package com.github.joraclista.client.ui.common.css;

import com.google.gwt.user.client.ui.IsWidget;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alisa
 * version 1.0.
 */
public final class StyleUtils {

    private StyleUtils() {}


    public static void addStyleNames(IsWidget widget, String... styles) {
        addStyleNames(widget, Arrays.asList(styles));
    }

    public static void addStyleNames(IsWidget widget, List<String> styles) {
        styles.forEach(style -> widget.asWidget().addStyleName(style));
    }

    public static void removeStyleNames(IsWidget widget, String... styles) {
        removeStyleNames(widget, Arrays.asList(styles));
    }

    public static void removeStyleNames(IsWidget widget, List<String> styles) {
        styles.forEach(style -> widget.asWidget().removeStyleName(style));
    }

    public static <T extends IsWidget> T withStyleNames(T widget, String... styles) {
        addStyleNames(widget, styles);
        return widget;
    }

    public static <T extends IsWidget> T withStyleNames(T widget, List<String> styles) {
        addStyleNames(widget, styles);
        return widget;
    }

    public static <T extends IsWidget> T withoutStyleNames(T widget, String... styles) {
        removeStyleNames(widget, styles);
        return widget;
    }

    public static <T extends IsWidget> T withoutStyleNames(T widget, List<String> styles) {
        removeStyleNames(widget, styles);
        return widget;
    }

    public static <T extends IsWidget> T setStyleProperty(T widget, String property, String value) {
        widget.asWidget().getElement().getStyle().setProperty(property, value);
        return widget;
    }

    public static <T extends IsWidget> T setAttribute(T widget, String attribute, String value) {
        widget.asWidget().getElement().setAttribute(attribute, value);
        return widget;
    }
}
