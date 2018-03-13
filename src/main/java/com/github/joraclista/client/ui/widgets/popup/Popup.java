package com.github.joraclista.client.ui.widgets.popup;

import com.github.joraclista.client.ui.widgets.popup.css.PopupBundle;
import com.github.joraclista.client.ui.widgets.popup.css.PopupCss;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;

import java.util.Arrays;

import static com.github.joraclista.client.ui.common.TaskUtil.scheduleWithDelay;

/**
 * Created by Alisa
 * version 1.0.
 */
public class Popup extends Composite {


    public static final Label NO_WIDGET = new Label("no widget");
    private static final int AUTO_HIDE_DELAY_DEFAULT_MS = 5000;

    interface Binder extends UiBinder<FlowPanel, Popup> {
    }

    private static Binder binder = GWT.create(Binder.class);

    private final PopupCss css;
    @UiField
    FlowPanel content;
    @UiField
    Label closeButton;
    @UiField
    FlowPanel popup;
    @UiField
    SimplePanel contentWidget;
    private IsWidget widget;
    private boolean closeOnBackgroundClick;
    private boolean autoHideEnabled;
    private Timer timer;
    private int autoHideDelay = AUTO_HIDE_DELAY_DEFAULT_MS;
    private boolean animationEnabled;

    public Popup() {
        this(PopupBundle.BUNDLE.css(), NO_WIDGET);
    }

    public Popup(IsWidget widget) {
        this(PopupBundle.BUNDLE.css(), widget);
    }

    public Popup(PopupCss css) {
        this(css, NO_WIDGET);
    }

    public Popup(PopupCss css, IsWidget widget) {
        initWidget(binder.createAndBindUi(this));
        this.css = css;
        css.ensureInjected();
        applyStyles();
        withWidget(widget);

        addHandlers();
    }

    private void addHandlers() {

        this.popup.addDomHandler(event -> {
            if (closeOnBackgroundClick) close();
        }, ClickEvent.getType());

        this.closeButton.addClickHandler(event -> close());

        this.popup.addDomHandler(event -> close(), TransitionEndEvent.getType());
    }


    public Popup withWidget(IsWidget widget) {
        this.widget = widget;
        this.contentWidget.clear();
        this.contentWidget.add(widget);
        return this;
    }

    public Popup withAppliedBackground(boolean apply) {
        this.popup.setStyleName(css.withBackground(), apply);
        return this;
    }

    public Popup withCloseOnBackgroundClick(boolean apply) {
        this.closeOnBackgroundClick = apply;
        return this;
    }

    public Popup withCloseButtonVisibility(boolean visibility) {
        this.closeButton.setVisible(visibility);
        return this;
    }

    public Popup withAutoHideEnabled(boolean autoHideEnabled) {
        this.autoHideEnabled = autoHideEnabled;
        return this;
    }

    public Popup withAutoHideDelayMs(int milliseconds) {
        this.autoHideDelay = milliseconds;
        return this;
    }

    public Popup withAnimationEnabled(boolean animationEnabled) {
        this.animationEnabled = animationEnabled;
        return this;
    }

    public Popup withPosition(Position position) {
        Position _position = position == null ? Position.CENTER : position;
        Arrays.stream(Position.values())
                .forEach(positionType ->
                        this.popup.setStyleName(positionType.style(css), _position.equals(positionType)));
        return this;
    }

    private void applyStyles() {
        this.popup.addStyleName(css.popup());
        this.content.addStyleName(css.content());
        this.closeButton.addStyleName(css.closeButton());
        withPosition(Position.CENTER);
    }

    public void show() {
        cancelTimer();
        RootPanel.get().add(this.asWidget());




    }

    @Override
    protected void onAttach() {
        super.onAttach();
        if (autoHideEnabled) {
            timer = scheduleWithDelay(() -> close(), autoHideDelay);
        }
        if (animationEnabled) {
            scheduleWithDelay(() -> popup.addStyleName(css.animate()), 200);
        }

    }

    @Override
    protected void onDetach() {
        super.onDetach();
    }

    private void cancelTimer() {
        if (timer != null) timer.cancel();
    }


    public void close() {
        cancelTimer();
        this.asWidget().removeFromParent();
    }
}