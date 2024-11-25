package com.epam.qa.reportportal.component;

import com.codeborne.selenide.SelenideElement;

public class Input extends BasePageElement{

    /**
     * Constructor.
     *
     * @param element element
     */
    public Input(SelenideElement element) {
        super(element);
    }

    /**
     * Inputs text.
     *
     * @param text text.
     */
    public void inputText(String text) {
        clear();
        getElement().sendKeys(text);
    }

    /**
     * Clears input.
     */
    public void clear() {
        getElement().clear();
    }

    /**
     * Gets input error.
     *
     * @return error object
     */
    public ErrorText getError() {
        return new ErrorText(getElement().parent().$("p"));
    }
}
