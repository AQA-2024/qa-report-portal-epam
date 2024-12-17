package com.epam.qa.reportportal.webdriver;

import com.codeborne.selenide.WebDriverProvider;
import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nonnull;
import java.util.Map;

public abstract class AbstractChromeDriverProvider implements WebDriverProvider {

    private static final Logger LOGGER = LoggerFactoryProvider.getLogger();

    public static final String DOWNLOAD_PATH = "";


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        WebDriverManager.chromedriver().clearDriverCache().cachePath("./build/cache");
        WebDriver webDriver = null;
        int retryCount = 0;
        while (null == webDriver) {
            try {
                LOGGER.info(String.format("Attempting to create Chrome WebDriver, attempt %s", retryCount + 1));
                webDriver = new ChromeDriver(getChromeOptions());
                webDriver.manage().window().maximize();
                LOGGER.info(String.format("Chrome WebDriver created successfully on attempt %s", retryCount + 1));
            } catch (Exception e) {
                 if (retryCount > 2) {
                     LOGGER.error(String.format("Failed to create Chrome WebDriver after %s attempts %s", retryCount + 1, e));
                     throw e;
                }
                LOGGER.warn(String.format("Unable to create Chrome web driver. %s", e));
                retryCount++;
            }
        }
        webDriver.manage().window().maximize();
        LOGGER.info("Chrome WebDriver successfully created and maximized.");
        return webDriver;
    }

    /**
     * Provides chrome options.
     *
     * @return chrome options
     */
    protected abstract ChromeOptions getChromeOptions();
}
