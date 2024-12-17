package com.epam.qa.reportportal.steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import com.epam.qa.reportportal.pages.LaunchPage;
import com.epam.qa.reportportal.utils.PageUtils;
import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class LaunchSteps {

    private static final String PART_OF_LAUNCHES_URL = "launches";

    private final LaunchPage launchPage = new LaunchPage();

    private static final Logger LOGGER = LoggerFactoryProvider.getLogger();

    @Then("'Launches' page is opened")
    public void verifyLaunchPageOpened() {
        LOGGER.info("Verifying if 'Launches' page is opened...");
        launchPage.getLaunchCell().shouldBe(CollectionCondition.sizeGreaterThan(0));
        String currentUrl = PageUtils.getPageUrl();
        assertThat(currentUrl).as("Check if the URL contains 'launches'").contains(PART_OF_LAUNCHES_URL);
        LOGGER.info("Page URL verified: " + currentUrl);
    }

    @Then("the [$number] launch with [$name] name is displayed")
    public void verifyLaunchName(String launchNumber, String launchName) {
        assertTrue(launchPage.getLaunchByName(launchNumber, launchName).isDisplayed(),
                "Launch with number " + launchNumber + " and name " + launchName + " should be displayed.");
        LOGGER.info(String.format("Launch with number '%s' and name '%s' is displayed", launchNumber, launchName));
    }

    @Then("the [$count] $type number of launches is displayed for [$number] launch with [$name] name")
    public void verifyLaunchCount(String count, String launchType, String launchNumber, String launchName) {
        LOGGER.info(String.format("Verifying '%s' number of launches of type '%s' for launch '%s' with name '%s'",
                count, launchType, launchNumber, launchName));
        String actualCount = fetchLaunchCount(launchType, launchNumber, launchName);
        assertThat(actualCount).as("Check if the number of launches matches the expected count")
                .isEqualTo(count);
        LOGGER.info(String.format("Expected number of launches for '%s' with name '%s' is '%s'.",
                launchNumber, launchName, count));
    }

    private String fetchLaunchCount(String launchType, String launchNumber, String launchName) {
        try {
            return launchPage.getNumberSpecificLaunchType(launchType, launchNumber, launchName);
        } catch (ElementNotFound e) {
            LOGGER.warn(String.format("Launch type '%s' for launch '%s' with name '%s' not found, defaulting to 'EMPTY'.",
                    launchType, launchNumber, launchName));
            return "EMPTY";
        }
    }


    @Then("[$attribute] $type attribute is displayed for [$number] launch with [$name] name")
    public void verifySpecificAttributeForLaunch(String attributeValue, String attributeType, String launchNumber, String launchName) {
        LOGGER.info(String.format("Verifying if '%s' attribute of type '%s' is displayed for launch '%s' with name '%s'",
                attributeValue, attributeType, launchNumber, launchName));
        assertThat(launchPage.getSpecificAttribute(attributeType, launchNumber, launchName))
                .as("Check if the attribute matches the expected value")
                .isEqualTo(attributeValue);
        LOGGER.info(String.format("Attribute '%s' of type '%s' for launch '%s' with name '%s' is verified.",
                attributeValue, attributeType, launchNumber, launchName));
    }

    @When("user checks the checkbox for [$number] launch with [$name] name")
    public void checkSpecificLaunch(String launchNumber, String launchName) {
        LOGGER.info(String.format("User is checking the checkbox for launch '%s' with name '%s'", launchNumber, launchName));
        launchPage.getLaunchByName(launchNumber, launchName).scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"nearest\"}");
        launchPage.getCheckboxForLaunch(launchNumber, launchName).shouldBe(Condition.visible, Duration.ofSeconds(5));
        launchPage.getCheckboxForLaunch(launchNumber, launchName).click();
        LOGGER.info(String.format("Checkbox for launch '%s' with name '%s' is checked.", launchNumber, launchName));
    }

    @When("user unchecks the checkbox for [$number] launch with [$name] name")
    public void uncheckSpecificLaunch(String launchNumber, String launchName) {
        LOGGER.info(String.format("User unchecks the checkbox for launch '%s' with name '%s'", launchNumber, launchName));
        launchPage.getCheckboxForLaunch(launchNumber, launchName).click();
    }

    @Then("[$number] launch with [$name] name is [$state]")
    public void verifyCheckboxStateForLaunch(String launchNumber, String launchName, String state) {
        launchPage.getCheckboxForLaunch(launchNumber, launchName).hasState(state);
        LOGGER.info(String.format("The %s launch with %s name is %s", launchNumber, launchName, state));
    }

    @Then("[$name] launch is displayed in the 'Selected Items' toolbar")
    public void verifySelectedLaunch(String launchName) {
        assertThat(launchPage.isLaunchSelected(launchName))
                .withFailMessage("Launch with name '%s' is not displayed in the 'Selected Items' toolbar", launchName)
                .isTrue();
        LOGGER.info(String.format("The %s launch is displayed in the 'Selected Items' toolbar", launchName));
    }

    @Then("'Selected Items' toolbar is not visible")
    public void verifySelectedItemsNotVisible() {
        assertThat(launchPage.getSelectedItemToolbar().isDisplayed()).isFalse();
        LOGGER.info("The 'Selected Items' toolbar is not visible on the 'Launch' page");
    }

}
