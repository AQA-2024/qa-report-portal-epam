package com.epam.qa.reportportal.steps;

import com.epam.qa.reportportal.pages.BasePage;
import com.epam.qa.reportportal.pages.Sidebar;
import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class SidebarSteps {

    private static final Logger LOGGER = LoggerFactoryProvider.getLogger();

    private final Sidebar sidebar = new Sidebar();
    private final BasePage basePage = new BasePage();

    @When("user switches to the '$tab' tab")
    public void switchToTab(String tab) {
        LOGGER.info(String.format("Switching to the '%s' tab", tab));
        sidebar.getSidebarButton(tab).click();
        LOGGER.info(String.format("Tab '%s' clicked successfully", tab));
    }

    @When("user clicks the 'User icon' on the Sidebar")
    public void clickUserIcon() {
        LOGGER.info("Clicking the 'User icon' on the Sidebar");
        dismissSuccessNotification();
        sidebar.getUserAvatarIcon().click();
        LOGGER.info("User avatar icon clicked successfully");
        assertThat(sidebar.getUserAvatarMenu().isDisplayed())
                .withFailMessage("User avatar menu is not displayed")
                .isTrue();
        LOGGER.info("User avatar menu is displayed successfully");
    }

    private void dismissSuccessNotification() {
        if (basePage.getSuccessNotification().isDisplayed()) {
            LOGGER.info("Success notification is displayed, dismissing it");
            basePage.getSuccessNotification().click();
            LOGGER.info("Success notification dismissed");
        }
    }

    @When("user clicks the [$item] item in the menu")
    public void clickItemInUserMenu(String item) {
        LOGGER.info(String.format("Clicking the '%s' item in the user menu", item));
        sidebar.getUserMenuItem(item).click();
        LOGGER.info(String.format("Menu item '%s' clicked successfully", item));
    }

    @When("user opens the Project selector")
    public void openProjectSelector() {
        LOGGER.info("Opening the Project selector");
        sidebar.getProjectSelector().click();
        LOGGER.info("Project selector opened successfully");
    }

    @When("user selects the [$project] project")
    public void selectProject(String project) {
        LOGGER.info(String.format("Selecting the '%s' project", project));
        sidebar.getProjectSelector().getProjectByName(project).click();
        LOGGER.info(String.format("Project '%s' selected successfully", project));
    }

}
