package com.epam.qa.reportportal.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public abstract class BasePageElement {

    private SelenideElement element;

    /**
     * Constructor.
     *
     * @param element element
     */
    protected BasePageElement(SelenideElement element) {
        this.element = element;
    }

    /**
     * Checks if element is displayed.
     *
     * @return true if element is displayed, otherwise false
     */
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    protected SelenideElement getElement() {
        return element;
    }

    /**
     * Wait until given element meets given condition (with given timeout).
     *
     * @param condition condition
     * @param timeout timeout
     * @return element
     */
    public SelenideElement shouldBe(Condition condition, Duration timeout) {
        return getElement().shouldBe(condition, timeout);
    }
}
