package com.github.joraclista.client;

import com.github.joraclista.client.snippets.CodeSnippet;
import com.github.joraclista.client.snippets.bundle.SnippetsBundle;
import com.github.joraclista.client.ui.widgets.WidgetsGroup;
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
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;
import static java.util.Arrays.asList;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class WidgetsShowcase implements EntryPoint {

    private final String ROOT_PANEL_SLOT_ID = "slot";



    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        createUI();
    }

    private void createUI() {
       // addToRoot(new WidgetsGroup("Calendars", asList(new DarkCodeSnippet())));
        configureCalendarWidget();

        configureCodeSnippetWidget();

        configureNotificationsWidgets();

        configureBusinessCardsWidgets();

        }

    private void configureNotificationsWidgets() {
        addToRoot(new WidgetsGroup("Notifications", asList(
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
                        .withType(NotificationType.NONE))));
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
        addToRoot(new WidgetsGroup("Business Cards", asList(contact, cv)));
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


        addToRoot(new WidgetsGroup("Calendars", asList(calendar)));
    }

    private void configureCodeSnippetWidget() {
        addToRoot(new WidgetsGroup("Code Snippets", asList(
                new CodeSnippet(SnippetsBundle.BUNDLE.calendar().getText(), SnippetsBundle.BUNDLE.darkCss())
                .withSnippetName("Code Listing in Dark Scheme with line numbers and copy button #1")
                .withLineNumbersEnabled(true)
                .withCodeCopyButtonEnabled(true),

                new CodeSnippet(SnippetsBundle.BUNDLE.notification().getText(), SnippetsBundle.BUNDLE.lightCss())
                        .withSnippetName("Code Listing Light Scheme #2"))));
    }

    private void addToRoot(IsWidget widget) {
        RootPanel.get(ROOT_PANEL_SLOT_ID).add(widget);
    }


}
