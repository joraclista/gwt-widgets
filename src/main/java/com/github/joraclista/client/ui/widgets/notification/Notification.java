package com.github.joraclista.client.ui.widgets.notification;

import com.github.joraclista.client.ui.widgets.notification.css.NotificationBundle;
import com.github.joraclista.client.ui.widgets.notification.css.NotificationCss;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import static java.util.Arrays.stream;

/**
 * Created by Alisa
 * version 1.0.
 */
public class Notification extends Composite {

    interface Binder extends UiBinder<FlowPanel, Notification> {
    }

    private static Binder binder = GWT.create(Binder.class);
    private final NotificationCss css;

    @UiField
    Label message;
    @UiField
    Label arrow;

    public Notification(NotificationCss css, String message, NotificationType type) {
        this.css = css;
        css.ensureInjected();
        initWidget(binder.createAndBindUi(this));
        this.asWidget().addStyleName(css.main());
        this.message.addStyleName(css.message());
        this.arrow.addStyleName(css.arrow());
        this.message.setText(message);
        withType(type).withArrowPosition(ArrowPosition.NONE);
    }

    public Notification(String message) {
        this(NotificationBundle.BUNDLE.baseCss(), message, NotificationType.NONE);
    }

    public Notification(String message, NotificationType type) {
        this(NotificationBundle.BUNDLE.baseCss(), message, type);
    }

    public Notification withType(NotificationType type) {
        final NotificationType _type = type == null ? NotificationType.NONE : type;
        stream(NotificationType.values()).forEach(notificationType -> notificationType.style(css)
                .forEach(style -> this.asWidget().setStyleName(style, _type.equals(notificationType))));
        return this;
    }

    public Notification withArrowPosition(ArrowPosition arrowPosition) {
        final ArrowPosition _arrowPosition = arrowPosition == null ? ArrowPosition.NONE : arrowPosition;
        stream(ArrowPosition.values()).forEach(position -> arrow.setStyleName(position.style(css), _arrowPosition.equals(position)));
        return this;
    }
}