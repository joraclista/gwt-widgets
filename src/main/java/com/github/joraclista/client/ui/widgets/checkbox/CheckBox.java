package com.github.joraclista.client.ui.widgets.checkbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import static com.google.gwt.event.logical.shared.ValueChangeEvent.fire;

/**
 * Created by Alisa
 * version 1.0.
 */
public class CheckBox extends Composite implements HasValueChangeHandlers<Boolean> {
    interface BoxUiBinder extends UiBinder<FlowPanel, CheckBox> {
    }

    private static BoxUiBinder ourUiBinder = GWT.create(BoxUiBinder.class);
    @UiField
    Label check;
    @UiField
    Label label;

    private final CheckBoxBundle.Css css;
    private boolean checked;
    private boolean enabled = true;


    public CheckBox(CheckBoxBundle.Css css, String labelText) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.css = css;
        css.ensureInjected();
        label.setText(labelText);

        applyStyles(css);

        addDomHandler(event -> {
            if (!isEnabled()) {
                return;
            }
            setChecked(!isChecked());
            fire(CheckBox.this, CheckBox.this.checked);
        }, ClickEvent.getType());

        setChecked(false);
    }

    public CheckBox(CheckBoxBundle.Css css) {
        this(css, "");
    }

    public CheckBox() {
        this(CheckBoxBundle.BUNDLE.css());
    }

    public CheckBox(String label) {
        this(CheckBoxBundle.BUNDLE.css(), label);
    }
    private void applyStyles(CheckBoxBundle.Css css) {
        addStyleName(css.panel());
        check.addStyleName(css.check());
        label.addStyleName(css.label());
    }

    public CheckBox withLabel(String label) {
        setLabel(label);
        return this;
    }

    public CheckBox withChecked(boolean value) {
        setChecked(value);
        return this;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        setStyleName(css.checked(), checked);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        setStyleName(css.disabled(), !enabled);
    }

    public CheckBox withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
        return super.addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        super.fireEvent(event);
    }
}