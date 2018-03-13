package com.github.joraclista.client.ui;

import com.github.joraclista.client.ui.widgets.checkbox.CheckBox;
import com.github.joraclista.client.ui.widgets.checkbox.css.CheckBoxBundle;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Alisa
 * version 1.0.
 */
public class CheckboxesPanel extends FlowPanel {

    public CheckboxesPanel() {
        List<String> labels = asList("Milk", "Chocolate", "Meat", "Bread");
        List<CheckBox> group1 = new ArrayList<>();
        VerticalPanel vp1 = new VerticalPanel();
        VerticalPanel vp2 = new VerticalPanel();
        VerticalPanel vp3 = new VerticalPanel();

        labels.stream().map(label -> new CheckBox(label)
                .withEnabled(!"Meat".equalsIgnoreCase(label)))
                .forEach(cb -> vp1.add(cb));


        labels.stream().map(label -> new CheckBox(CheckBoxBundle.BUNDLE.rectangledCheckboxCss(), label)
                .withEnabled(!"Meat".equalsIgnoreCase(label)))
                .forEach(cb -> vp2.add(cb));


        labels.stream().map(label -> new CheckBox(CheckBoxBundle.BUNDLE.switchCheckboxCss(), label)
                .withEnabled(!"Meat".equalsIgnoreCase(label)))
                .forEach(cb -> vp3.add(cb));


        add(vp1);
        add(vp2);
        add(vp3);
    }
}
