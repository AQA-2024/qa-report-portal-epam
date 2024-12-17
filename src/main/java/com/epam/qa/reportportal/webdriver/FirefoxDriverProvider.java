package com.epam.qa.reportportal.webdriver;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import javax.annotation.Nonnull;

import static com.epam.qa.reportportal.webdriver.AbstractChromeDriverProvider.DOWNLOAD_PATH;

public class FirefoxDriverProvider implements WebDriverProvider {

    private static final Integer SECURITY_SANDBOX_LEVEL = 4;
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver webDriver = new FirefoxDriver(getFirefoxOptions());
        webDriver.manage().window().maximize();
        return webDriver;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.download.dir", DOWNLOAD_PATH);
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting ", false);
        firefoxProfile.setPreference("browser. frames. enabled", false);
        firefoxProfile.setPreference("pdfjs.disabled", true);
        firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
        firefoxProfile.setPreference("plugin.scan.plid.all", false);
        firefoxProfile.setAcceptUntrustedCertificates(true);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.addPreference("security.sandbox.content.level", SECURITY_SANDBOX_LEVEL);

        return firefoxOptions;
    }
}
