package com.github.joraclista.client.ui.widgets.notification.css;

import com.github.joraclista.client.ui.widgets.popup.css.PopupCss;

/**
 * Created by Alisa
 * version 1.0.
 */
public interface NotificationCss extends PopupCss {


    String main();

    String message();

    @ClassName("info-circle")
    String infoCircle();

    String exclamation();

    @ClassName("check-circle")
    String checkCircle();

    String check();

    @ClassName("exclamation-circle")
    String exclamationCircle();

    String info();

    String vanilla();

    String success();

    String warning();

    String error();

    String arrow();

    @ClassName("arrow-top")
    String arrowTop();

    @ClassName("arrow-left")
    String arrowLeft();

    @ClassName("arrow-bottom")
    String arrowBottom();

    @ClassName("arrow-right")
    String arrowRight();

    @ClassName("arrow-none")
    String arrowNone();


}
