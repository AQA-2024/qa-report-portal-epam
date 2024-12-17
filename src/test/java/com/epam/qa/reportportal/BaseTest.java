package com.epam.qa.reportportal;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.qa.reportportal.steps.LaunchSteps;
import com.epam.qa.reportportal.steps.LoginSteps;
import com.epam.qa.reportportal.steps.SidebarSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    private static final String LAUNCHES_TAB = "Launches";
    private static final String BROWSER = "chrome";
    private static final String BROWSER_SIZE = "1920x1080";
    private static final String PROJECT_NAME = "yanina_shokun_personal";

    protected LoginSteps loginSteps = new LoginSteps();
    protected LaunchSteps launchSteps = new LaunchSteps();
    protected SidebarSteps sidebarSteps = new SidebarSteps();

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        initializeWebDriver();
        loginToReportPortal();
        navigateToProject();
        navigateToLaunchesTab();
    }

    @AfterEach
    public void tearDown() {
        logout();
        closeWebDriver();
    }

    private void initializeWebDriver() {
        Configuration.browser = BROWSER;
        Configuration.browserSize = BROWSER_SIZE;
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    private void loginToReportPortal() {
        loginSteps.openReportPortal();
        loginSteps.enterCredentials();
        loginSteps.clickLoginButton();
    }

    private void navigateToLaunchesTab() {
        sidebarSteps.switchToTab(LAUNCHES_TAB);
        launchSteps.verifyLaunchPageOpened();
    }

    private void navigateToProject() {
        sidebarSteps.openProjectSelector();
        sidebarSteps.selectProject(PROJECT_NAME);
    }
    private void logout() {
        sidebarSteps.clickUserIcon();
        sidebarSteps.clickItemInUserMenu("LOGOUT");
    }

}
