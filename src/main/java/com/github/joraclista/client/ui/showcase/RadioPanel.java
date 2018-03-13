package com.github.joraclista.client.ui.showcase;

import com.github.joraclista.client.ui.widgets.radio.RadioGroup;
import com.github.joraclista.client.ui.widgets.radio.css.RadioButtonBundle;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Alisa
 * version 1.0.
 */
public class RadioPanel extends FlowPanel {

    public RadioPanel() {
        List<String> labels = asList("Milk", "Chocolate", "Meat", "Bread");

        VerticalPanel vp1 = new VerticalPanel();
        VerticalPanel vp2 = new VerticalPanel();

        RadioGroup radioGroup1 = new RadioGroup();
        RadioGroup radioGroup2 = new RadioGroup(RadioButtonBundle.BUNDLE.rectangledRadioButtonCss());

        configurePanel(labels, vp1, radioGroup1);
        configurePanel(labels, vp2, radioGroup2);
    }

    private void configurePanel(List<String> labels, Panel vp, RadioGroup radioGroup) {
        labels.stream().map(label -> radioGroup.createButton(label, true))
                .forEach(cb -> vp.add(cb));

        Label groupChecked = new Label("Selected: " + radioGroup.getChecked().map(rb -> rb.getLabel()).orElse("none"));

        vp.add(groupChecked);

        radioGroup.addValueChangeHandler(event -> groupChecked.setText("Selected: " + event.getValue()));

        vp.setWidth("300px");

        add(vp);
    }
}
