package com.github.joraclista.client.ui.showcase;

import com.github.joraclista.client.ui.common.css.CommonBundle;
import com.github.joraclista.client.ui.widgets.radio.RadioButton;
import com.github.joraclista.client.ui.widgets.radio.RadioGroup;
import com.github.joraclista.client.ui.widgets.tocPanel.Layout;
import com.github.joraclista.client.ui.widgets.tocPanel.TocPanel;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.List;
import java.util.function.BiFunction;

import static com.github.joraclista.client.ui.common.css.StyleUtils.addStyleNames;
import static com.github.joraclista.client.ui.common.css.StyleUtils.withStyleNames;
import static java.util.Arrays.asList;

/**
 * Created by Alisa
 * version 1.0.
 */
public class TocPanelShowcase extends FlowPanel {

    private CommonBundle.Css css = CommonBundle.BUNDLE.css();

    {
        css.ensureInjected();
    }

    void configureRadioPanel(String label, List<String> radioLabels, BiFunction<RadioGroup, String, RadioButton> mapper, ValueChangeHandler<String> onValueChangeHandler) {
        FlowPanel radioPanel = withStyleNames(new FlowPanel(), css.flex(), css.flexRow(), css.vPadding10());
        radioPanel.add(withStyleNames(new Label(label), css.label()));
        add(radioPanel);


        RadioGroup radioGroup = new RadioGroup();

        radioLabels.stream()
                .map(_label -> mapper.apply(radioGroup, _label))
                .forEach(radioButton -> radioPanel.add(radioButton));

        radioGroup.addValueChangeHandler(onValueChangeHandler);
    }

    public TocPanelShowcase() {


        addStyleNames(this, css.flex(), css.flexColumn());

        TocPanel tocPanel = new TocPanel();

        configureRadioPanel(
                "Layout : ",
                asList("Horizontal Layout", "Vertical Layout"),
                (radioGroup, label) -> radioGroup.createButton(label, "Horizontal Layout".equalsIgnoreCase(label)),
                event -> tocPanel.withLayout("Horizontal Layout".equalsIgnoreCase(event.getValue())
                        ? Layout.HORIZONTAL
                        : Layout.VERTICAL));
        configureRadioPanel(
                "Numbered headers : ",
                asList("Yes", "No"),
                (radioGroup, label) -> radioGroup.createButton(label, "No".equalsIgnoreCase(label)),
                event -> tocPanel.withHeaderNumbersEnabled("Yes".equalsIgnoreCase(event.getValue())));

        add(tocPanel);



        tocPanel.addWidget("History", new Label("Android is a mobile operating system developed by Google, " +
                "based on a modified version of the Linux kernel and other open source software " +
                "and designed primarily for touchscreen mobile devices such as smartphones and tablets. " +
                "In addition, Google has further developed Android TV for televisions, Android Auto for cars, " +
                "and Android Wear for wrist watches, each with a specialized user interface. Variants of Android " +
                "are also used on game consoles, digital cameras, PCs and other electronics."));
        tocPanel.addWidget("Mascot", new Label("The mascot of Android is a green android robot, " +
                "as related to the software's name. Although it has no official name," +
                " the Android team at Google reportedly call it \"Bugdroid\"." +
                "Due to Android's high popularity in the 2010s, it has become one of the most recognizable" +
                " icons in the technology world.\n" +
                "\n" +
                "It was designed by then-Google graphic designer Irina Blok on " +
                "November 5, 2007 when Android was announced. Contrary to reports that" +
                " she was tasked with a project to create an icon, Blok confirmed in " +
                "an interview that she independently developed it and made it open source." +
                " The robot design was initially not presented to Google, but it quickly became commonplace " +
                "in the Android development team, with various different variations of it " +
                "created by the developers there who liked the figure, as it was free under a " +
                "Creative Commons license.Its popularity amongst the development team " +
                "eventually led to Google adopting it as an official icon as part of the Android " +
                "logo when it launched to consumers in 2008.\n" +
                "\n" +
                "Since then the robot figure has had plenty of other variations created."));
        tocPanel.addWidget("Features", new Label("Android's default user interface is mainly based on direct manipulation," +
                " using touch inputs that loosely correspond " +
                "to real-world actions, like swiping, tapping, pinching, and reverse pinching to manipulate on-screen objects, " +
                "along with a virtual keyboard.[66] Game controllers and full-size physical keyboards are supported via Bluetooth " +
                "or USB.The response to user input is designed to be immediate and provides a fluid touch interface, " +
                "often using the vibration capabilities of the device to provide haptic feedback to the user. " +
                "Internal hardware, such as accelerometers, gyroscopes and proximity sensors are used by some " +
                "applications to respond to additional user actions, for example adjusting the screen " +
                "from portrait to landscape depending on how the device is oriented," +
                "or allowing the user to steer a vehicle in a racing game by rotating the device," +
                " simulating control of a steering wheel."));
    }


}
