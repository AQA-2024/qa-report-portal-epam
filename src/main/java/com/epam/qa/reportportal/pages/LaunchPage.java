package com.epam.qa.reportportal.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.epam.qa.reportportal.component.Checkbox;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class LaunchPage {

    private static final String LAUNCH_NAME = ".tooltip__tooltip-trigger--FBBdw.itemInfo__name--Nz97v";
    private static final String LAUNCH_NUMBER = ".itemInfo__number--uvCUK";

    private static final String GRID_ROW_LAUNCH = ".gridRow__grid-row--X9wIq";
    private static final String GRID_CELL_NAME = ".launchSuiteGrid__name-col--rSvdG.gridCell__grid-cell--EIqeC.gridCell__align-left--DFXWN";

    private static final String EXECUTION_STATISTICS = ".executionStatistics__execution-statistics--lPvLD";

    public static final String ATTRIBUTES_BLOCK = ".attributesBlock__attributes-block--xE2Uz";
    public static final String SPECIFIC_ATTRIBUTE = ".//*[@class='attributesBlock__attribute--F6FmH attributesBlock__cursor--Qg03y' and contains(@title, '%s')]";
    public static final String SPECIFIC_ATTRIBUTE_VALUE = ".attributesBlock__value--z_20L";

    public static final String LAUNCH_CHECKBOX_CELL = ".//*[contains(@class, 'gridCell__align-right--hTa_A')]";
    public static final String LAUNCH_CHECKBOX = ".//*[contains(@class, 'checkIcon__square--Exwkc')]";

    public static final String SELECTED_ITEMS_TOOLBAR = ".selectedItems__selected-items--J7MXj";
    public static final String SELECTED_ITEM_NAME = ".selectedItem__name--s1XmK";

    private static final Map<String, String> LAUNCH_TYPE = Map.of(
            "total", "PASSED,FAILED,SKIPPED,INTERRUPTED",
            "passed", "PASSED",
            "failed", "FAILED,INTERRUPTED",
            "skipped", "SKIPPED");

    public ElementsCollection getLaunchCell() {
        return $$(GRID_CELL_NAME);
    }

    public SelenideElement getLaunchByName(String launchNumber,String launchName) {
        return getLaunchCell().stream()
                .filter(item -> {
                    String name = item.$(LAUNCH_NAME).$("span").getText();
                    String number = item.$(LAUNCH_NUMBER).getText();
                    return name.equals(launchName) && number.equals(launchNumber);
                })
                .findFirst().orElseThrow(() -> new NullPointerException("Launch with specified name and number not found"));
    }

    public String getNumberSpecificLaunchType(String launchType, String launchNumber, String launchName) {
        return getLaunchByName(launchNumber, launchName).closest(GRID_ROW_LAUNCH)
                .$$(EXECUTION_STATISTICS).stream()
                .map(item -> item.$("a"))
                .filter(item -> LAUNCH_TYPE.get(launchType).equals(item.getAttribute("statuses")))
                .map(SelenideElement::getText)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No launch found with type: " + launchType));
    }

    public String getSpecificAttribute(String attributeType, String launchNumber, String launchName) {
        return getLaunchByName(launchNumber, launchName).$(ATTRIBUTES_BLOCK)
                .$x(String.format(SPECIFIC_ATTRIBUTE, attributeType)).$(SPECIFIC_ATTRIBUTE_VALUE).getText();
    }

    public Checkbox getCheckboxForLaunch(String launchNumber, String launchName) {
        return new Checkbox(getLaunchByName(launchNumber, launchName).closest(GRID_ROW_LAUNCH)
                .$x(LAUNCH_CHECKBOX_CELL)
                .$x(LAUNCH_CHECKBOX));
    }

    public ElementsCollection getSelectedItemName() {
        return getSelectedItemToolbar().$$(SELECTED_ITEM_NAME);
    }

    public SelenideElement getSelectedItemToolbar() {
        return $(SELECTED_ITEMS_TOOLBAR);
    }

    public boolean isLaunchSelected(String launchName) {
        return getSelectedItemName().stream()
                .map(SelenideElement::getText)
                .anyMatch(item -> item.equals(launchName));
    }
}
