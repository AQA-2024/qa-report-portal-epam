package com.epam.qa.reportportal.webdriver;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class ChromeDriverProvider extends AbstractChromeDriverProvider {

    @Override
    protected ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of("profile.default_content_settings.popups", 0,
                "download.default_directory", DOWNLOAD_PATH));
        options.addArguments("--test-type");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-print-preview");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setAcceptInsecureCerts(true);
        options.setExperimentalOption("w3c", true);
        return options;
    }
}
