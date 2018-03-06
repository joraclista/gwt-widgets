package com.github.joraclista.client;

import com.github.joraclista.client.ui.widgets.calendar.css.CalendarBundle;
import com.github.joraclista.client.ui.widgets.calendar.Calendar;
import com.github.joraclista.client.ui.widgets.contact.Contact;
import com.github.joraclista.client.ui.widgets.contact.ContactType;
import com.github.joraclista.client.ui.widgets.contact.css.ContactBundle;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

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

        addToRoot(new Calendar(CalendarBundle.BUNDLE.calendarCss()));
        Contact contact = new Contact(ContactBundle.BUNDLE.contactCss());
        contact.setName("Ryan Brown");
        contact.setProfession("Web Developer");
        contact.addContactInfo("+38(069)609-90-99", ContactType.MOBILE);
        contact.addContactInfo("someone@example.com", ContactType.EMAIL);
        contact.addContactInfo("example.com", ContactType.WEBSITE);
        //contact.addContactInfo("www.fb.com/someone.id", ContactType.FACEBOOK);
        contact.setImageUrl("https://media.gettyimages.com/photos/man-with-a-mustache-picture-id516040293");
        addToRoot(contact);
    }

    private void addToRoot(IsWidget widget) {
        RootPanel.get(ROOT_PANEL_SLOT_ID).add(widget);
    }


}
