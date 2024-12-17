package com.epam.qa.reportportal.steps;

import com.epam.qa.reportportal.service.ScreenshotService;
import com.epam.qa.reportportal.utils.BrowserType;
import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import com.epam.qa.reportportal.webdriver.ChromeDriverProvider;
import com.epam.qa.reportportal.webdriver.FirefoxDriverProvider;
import org.jbehave.core.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MainSteps {

    private static final Logger LOGGER = LoggerFactoryProvider.getLogger();

    @Autowired
    private ScreenshotService screenshotService;

    private BrowserType browser;

    private Map<BrowserType, String> webDriverProviders = Map.of(
            BrowserType.FIREFOX, FirefoxDriverProvider.class.getCanonicalName(),
            BrowserType.CHROME, ChromeDriverProvider.class.getCanonicalName()
    );

    public void setBrowser(BrowserType browser) {
        this.browser = browser;
    }


    /**
     * Cleans the screenshots directory before executing stories.
     */
    @BeforeStories
    public void beforeStories() {
        LOGGER.info("Cleaning screenshots directory...");
        screenshotService.cleanScreenshots();
        LOGGER.info("Screenshots directory cleaned successfully.");
    }
}
