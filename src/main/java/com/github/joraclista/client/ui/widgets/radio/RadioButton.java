package com.github.joraclista.client.ui.widgets.radio;

import com.github.joraclista.client.ui.widgets.radio.css.RadioButtonBundle;
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
public class RadioButton extends Composite implements HasValueChangeHandlers<Boolean> {
    interface Binder extends UiBinder<FlowPanel, RadioButton> {
    }

    private static Binder ourUiBinder = GWT.create(Binder.class);

    @UiField
    Label label;

    private final RadioButtonBundle.Css css;
    private boolean checked;
    private boolean enabled = true;

    RadioButton(RadioButtonBundle.Css css, String labelText) {
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
            fire(RadioButton.this, RadioButton.this.checked);
        }, ClickEvent.getType());

        setChecked(false);
    }

    private void applyStyles(RadioButtonBundle.Css css) {
        addStyleName(css.panel());
        label.addStyleName(css.check());
        label.addStyleName(css.label());
    }

    public String getLabel() {
        return label.getText();
    }

    public boolean isChecked() {
        return checked;
    }

    void setChecked(boolean checked) {
        this.checked = checked;
        setStyleName(css.checked(), checked);
        setStyleName(css.unchecked(), !checked);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        setStyleName(css.disabled(), !enabled);
    }

    public RadioButton withEnabled(boolean enabled) {
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