package com.github.joraclista.client.ui.showcase;

import com.github.joraclista.client.ui.widgets.barChart.BarChart;
import com.github.joraclista.client.ui.widgets.barChart.BarModel;
import com.github.joraclista.client.ui.widgets.barChart.css.BarChartBundle;
import com.github.joraclista.client.ui.widgets.calendar.Calendar;
import com.github.joraclista.client.ui.widgets.contact.Contact;
import com.github.joraclista.client.ui.widgets.contact.ContactType;
import com.github.joraclista.client.ui.widgets.contact.CvContact;
import com.github.joraclista.client.ui.widgets.contact.Person;
import com.github.joraclista.client.ui.widgets.contact.css.ContactBundle;
import com.github.joraclista.client.ui.widgets.notification.ArrowPosition;
import com.github.joraclista.client.ui.widgets.notification.Notification;
import com.github.joraclista.client.ui.widgets.notification.NotificationType;
import com.github.joraclista.client.ui.widgets.popup.Position;
import com.github.joraclista.client.ui.widgets.snippets.CodeSnippet;
import com.github.joraclista.client.ui.widgets.snippets.bundle.SnippetsBundle;
import com.github.joraclista.client.ui.widgets.tocPanel.TocPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;
import static java.util.Arrays.asList;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class WidgetsShowcase implements EntryPoint {

    private final String ROOT_PANEL_SLOT_ID = "slot";

private TocPanel tocPanel;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        createUI();
    }

    private void createUI() {
        tocPanel = new TocPanel();
        addToRoot(tocPanel.asWidget());

        configureCodeSnippetWidget();

        configureCalendarWidget();

        configureNotificationsWidgets();

        configurePopupsWidgets();

        configureCheckBoxWidgets();

        configureRadioButtonWidgets();

        configureBarWidget();

        configureTocWidgets();

        configureBusinessCardsWidgets();

    }

    private void configureTocWidgets() {
        tocPanel.addWidget("TOC Panel", new TocPanelShowcase());
    }

    private void configureRadioButtonWidgets() {
        tocPanel.addWidget("Radio Buttons", new RadioPanel());
    }

    private void configureCheckBoxWidgets() {
       tocPanel.addWidget("Check Boxes", new CheckboxesPanel());
    }

    private void configurePopupsWidgets() {
        tocPanel.addWidget("Popups", new PopupConfig());
    }

    private void configureNotificationsWidgets() {
        tocPanel.addWidgets("Notifications", asList(
                new Notification("This is warning")
                        .withType(NotificationType.WARNING)
                        .withArrowPosition(ArrowPosition.TOP),
                new Notification("This is error")
                        .withType(NotificationType.ERROR)
                        .withArrowPosition(ArrowPosition.BOTTOM),
                new Notification("This is info")
                        .withType(NotificationType.INFO)
                        .withArrowPosition(ArrowPosition.NONE),
                new Notification("This is success")
                        .withType(NotificationType.SUCCESS)
                        .withArrowPosition(ArrowPosition.LEFT),
                new Notification("This is no specific type")
                        .withType(NotificationType.NONE)));
    }

    private void configureBusinessCardsWidgets() {
        Contact contact = new Contact(ContactBundle.BUNDLE.contactCss());
        contact.setName("Ryan Brown");
        contact.setProfession("Web Developer");
        contact.addContactInfo("+38(069)609-90-99", ContactType.MOBILE);
        contact.addContactInfo("someone@example.com", ContactType.EMAIL);
        contact.addContactInfo("example.com", ContactType.WEBSITE);
        //contact.addContactInfo("www.fb.com/someone.id", ContactType.FACEBOOK);
        contact.setImageUrl("https://media.gettyimages.com/photos/man-with-a-mustache-picture-id516040293");

        CvContact cv = new CvContact(ContactBundle.BUNDLE.cvContactCss());
        cv.setPerson(new Person() {
            {
                setName("Ryan");
                setSurname("Black");
                setPosition("UI Developer / Web Developer");
                setNumOfClients(10);
                setNumOfProjects(23);
                setSkills(asList("3+ years of experience", "Professional performance", "Fast support"));
                setImageUrl("https://media.gettyimages.com/photos/man-with-a-mustache-picture-id516040293");
            }
        });
        tocPanel.addWidgets("Business Cards", asList(contact, cv));
    }

    private void configureCalendarWidget() {
        Calendar calendar = new Calendar();
        calendar.addValueChangeHandler(event ->  new Notification("Selected: " + getFormat("MMM d, yyyy").format(event.getValue()))
                .withType(NotificationType.INFO)
                .withPopupPosition(Position.TOP_RIGHT)
                .withArrowPosition(ArrowPosition.LEFT)
                .withPopupCloseButtonVisibility(true)
                .withPopupCloseOnBackgroundClick(true)
                .show());


        tocPanel.addWidget("Calendars", calendar);
    }

    private void configureCodeSnippetWidget() {
        tocPanel.addWidgets("Code Snippets", asList(
                new CodeSnippet(SnippetsBundle.BUNDLE.snippet1().getText(), SnippetsBundle.BUNDLE.darkCss())
                .withSnippetName("Code Listing in Dark Scheme with line numbers and copy button #1")
                .withLineNumbersEnabled(true)
                .withCodeCopyButtonEnabled(true),

                new CodeSnippet(SnippetsBundle.BUNDLE.snippet2().getText(), SnippetsBundle.BUNDLE.lightCss())
                        .withSnippetName("Code Listing Light Scheme #2")));
    }

    private void configureBarWidget() {
        BarChart barChart1 = new BarChart("Average Number of Students per Specialization");
        BarChart barChart2 = new BarChart("Total Income", BarChartBundle.BUNDLE.cssDiff());
        BarChart barChart3 = new BarChart("Headcount changes");
        tocPanel.addWidgets("Bar Chart", asList(
                barChart1, barChart2, barChart3));
        barChart1.render(asList(new BarModel(88, "2012"),
                new BarModel(98, "2013"),
                new BarModel(85, "2014"),
                new BarModel(77.5, "2015"),
                new BarModel(64.4, "2016"),
                new BarModel(55, "2017")));

        barChart2.render(asList(new BarModel(10, "Jan 2018"),
                new BarModel(98, "Feb 2018"),
                new BarModel(5, "Mar 2018"),
                new BarModel(-37.5, "Apr 2018"),
                new BarModel(-14.4, "May 2018"),
                new BarModel(-25, "June 2018")));

        barChart3.render(asList(new BarModel(-10, "3Q 2017"),
                new BarModel(-0, "4Q 2017"),
                new BarModel(-4.4, "1Q 2018"),
                new BarModel(-77.7, "2Q 2018"),
                new BarModel(-10, "3Q 2018"),
                new BarModel(-98, "4Q 2018")));
        ValueChangeHandler<BarModel> handler = event -> new Notification("Selected: " + event.getValue())
                .withType(NotificationType.INFO)
                .withPopupPosition(Position.CENTER)
                .withArrowPosition(ArrowPosition.LEFT)
                .withPopupCloseButtonVisibility(true)
                .withPopupCloseOnBackgroundClick(true)
                .show();
        barChart1.addValueChangeHandler(handler);
        barChart2.addValueChangeHandler(handler);
        barChart3.addValueChangeHandler(handler);
    }

    private void addToRoot(IsWidget widget) {
        RootPanel.get(ROOT_PANEL_SLOT_ID).add(widget);
    }


}
