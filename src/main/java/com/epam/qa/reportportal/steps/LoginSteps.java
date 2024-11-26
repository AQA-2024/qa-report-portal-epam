package com.epam.qa.reportportal.steps;

import com.codeborne.selenide.Condition;
import com.epam.qa.reportportal.pages.LoginPage;
import com.epam.qa.reportportal.service.PropertiesService;
import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import org.jbehave.core.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Component
public class LoginSteps {

    private static final Logger LOGGER = LoggerFactoryProvider.getLogger();

    private static final String REPORT_PORTAL_URL = "report.portal.url";
    private static final String LOGIN = "user.login";
    private static final String PASSWORD = "user.password";

    @Autowired
    private LoginPage loginPage;
    @Autowired
    private PropertiesService propertiesService;

    /**
     * Opens the Report Portal using the URL from the properties service.
     */
    @Given("user opens the Report Portal")
    public void openReportPortal() {
        String portalUrl = propertiesService.resolveProperty(REPORT_PORTAL_URL);
        LOGGER.info(String.format("Opening Report Portal at URL: %s", portalUrl));
        open(portalUrl);
    }

    /**
     * Enters the user credentials (login and password) into the respective fields.
     */
    @When("user enters login and password")
    public void enterCredentials() {
        LOGGER.info("Waiting for login input to be visible...");
        loginPage.getLoginInput().shouldBe(Condition.visible, Duration.ofSeconds(5));

        String login = propertiesService.resolveProperty(LOGIN);
        String password = propertiesService.resolveProperty(PASSWORD);

        LOGGER.info(String.format("Entering login: %s", login));
        loginPage.getLoginInput().inputText(login);

        LOGGER.info("Entering password");
        loginPage.getPasswordInput().inputText(password);
    }

    /**
     * Clicks the 'Login' button to submit the credentials.
     */
    @When("user click the 'Login' button")
    public void clickLoginButton() {
        LOGGER.info("Clicking the 'Login' button");
        loginPage.getLoginButton().click();
    }

}
