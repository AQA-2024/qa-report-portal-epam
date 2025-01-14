package com.epam.qa.reportportal.component;

import com.codeborne.selenide.SelenideElement;

public class Selector extends BasePageElement{

    public static final String PROJECT_NAME = ".//a[@href='#%s']";
    public static final String PROJECT_SELECTOR_LIST = ".projectSelector__projects-list--EKkEN";

    /**
     * Constructor.
     *
     * @param element element
     */
    public Selector(SelenideElement element) {
        super(element);
    }

    public SelenideElement getProjectByName(String project) {
        return getElement().$(PROJECT_SELECTOR_LIST).$x(String.format(PROJECT_NAME, project));
    }
}
