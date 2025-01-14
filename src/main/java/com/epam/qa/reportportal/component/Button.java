package com.epam.qa.reportportal.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class Button extends BasePageElement{

    /**
     * Constructor.
     *
     * @param element element
     */
    public Button(SelenideElement element) {
        super(element);
    }

    /**
     * Gets button text.
     *
     * @return button caption
     */
    public String getButtonText() {
        return getElement().getText();
    }

    /**
     * Clicks on the button.
     */
    public void click() {
        getElement().click();
    }

    /**
     * Hover over the button.
     */
    public void hover() {
        getElement().hover();
    }

    /**
     * Gets status of the element.
     *
     * @return true if button is active
     */
    public boolean isActive() {
        return getElement().shouldBe(Condition.visible).isEnabled();
    }

    /**
     * Get button`s tooltip.
     *
     * @return button`s tooltip
     */
    public String getButtonTooltip() {
        return getElement().attr("title");
    }
}
