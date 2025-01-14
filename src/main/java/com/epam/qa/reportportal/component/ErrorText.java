package com.epam.qa.reportportal.component;

import com.codeborne.selenide.SelenideElement;

public class ErrorText extends BasePageElement{

    /**
     * Constructor.
     *
     * @param element element
     */
    public ErrorText(SelenideElement element) {
        super(element);
    }

    /**
     * Gets error message.
     *
     * @return error message
     */
    public String getMessage() {
        return getElement().getText();
    }

}
