package com.github.joraclista.client.ui.widgets.radio;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.joraclista.client.ui.widgets.radio.css.RadioButtonBundle.BUNDLE;
import static com.github.joraclista.client.ui.widgets.radio.css.RadioButtonBundle.Css;
import static com.google.gwt.event.logical.shared.ValueChangeEvent.fire;
import static java.util.Optional.ofNullable;

/**
 * Created by Alisa
 * version 1.0.
 */
public class RadioGroup implements HasValueChangeHandlers<String> {
    private final Css css;

    private HandlerManager handlerManager;
    private List<RadioButton> radios = new ArrayList<>();
    private RadioButton checked;

    public RadioGroup(Css css) {
        this.css = css;
        css.ensureInjected();
    }

    public RadioGroup() {
        this(BUNDLE.css());
    }

    public RadioButton createButton(String label, boolean checked) {
        RadioButton radioButton = new RadioButton(css, label);

        radioButton.addValueChangeHandler(event -> onValueChange(radioButton));
        radios.add(radioButton);

        if (checked) {
            radios.stream()
                    .forEach(button -> button.setChecked(button.equals(radioButton)));
            this.checked = radioButton;
        } else {
            radioButton.setChecked(false);
        }

        return radioButton;
    }

    private void onValueChange(RadioButton radioButton) {
        this.checked = radioButton;
        this.radios.stream()
                .forEach(button -> button.setChecked(button.equals(radioButton)));
        fire(this, this.checked.getLabel());
    }

    public RadioButton createButton(String label) {
        return createButton(label, false);
    }

    public Optional<RadioButton> getChecked() {
        return ofNullable(checked);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return ensureHandlers().addHandler(ValueChangeEvent.getType(), handler);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        ensureHandlers().fireEvent(event);
    }

    private HandlerManager ensureHandlers() {
        return handlerManager == null ? handlerManager = createHandlerManager()
                : handlerManager;
    }

    protected HandlerManager createHandlerManager() {
        return new HandlerManager(this);
    }
}
