package com.epam.qa.reportportal.component;

import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

public class Checkbox extends BasePageElement{

    public static final String CLASS_ATTRIBUTE = "class";
    public static final String CHECKED = "checked";

    /**
     * Constructor.
     *
     * @param element element
     */
    public Checkbox(SelenideElement element) {
        super(element);
    }

    public boolean isChecked() {
        return Objects.requireNonNull(getElement().getAttribute(CLASS_ATTRIBUTE)).contains(CHECKED);
    }

    public boolean isUnchecked() {
        return !isChecked();
    }

    public boolean hasState(String state) {
        if (state.equals(CHECKED)) {
            return isChecked();
        } else {
            return isUnchecked();
        }
    }
}
