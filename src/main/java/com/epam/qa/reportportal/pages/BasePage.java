package com.epam.qa.reportportal.pages;

import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;

@Component
public class BasePage {

    public static final String SUCCESS_NOTIFICATION = ".//*[contains(@class, 'notificationItem__message-container--eN8Rd')]";

    public SelenideElement getSuccessNotification() {
        return $x(SUCCESS_NOTIFICATION);
    }
}
