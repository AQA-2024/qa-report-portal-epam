package com.epam.qa.reportportal.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.BooleanSupplier;

public class PageUtils {

    public static String getPageUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    /**
     * Wait while some boolean supplier to be true.
     *
     * @param bs boolean supplier to wait for.
     * @param pollingDurationInSeconds polling duration in seconds
     * @param timeoutInSeconds timeout in seconds
     * @param message message
     */
    public static void waitFor(BooleanSupplier bs, int pollingDurationInSeconds, int timeoutInSeconds, String message) {
        new FluentWait<>(1)
                .pollingEvery(Duration.ofSeconds(pollingDurationInSeconds))
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .withMessage(message)
                .until(f -> bs.getAsBoolean());
    }

    /**
     * Wait while some boolean supplier to be true.
     *
     * @param bs boolean supplier to wait for.
     * @param pollingDurationInSeconds polling duration in seconds
     * @param timeoutInSeconds timeout in seconds
     * @param message message
     */
    public static void waitForIgnoringExceptions(BooleanSupplier bs, int pollingDurationInSeconds, int timeoutInSeconds,
                                                 String message) {
        new FluentWait<>(1).pollingEvery(Duration.ofSeconds(pollingDurationInSeconds))
                .withTimeout(Duration.ofSeconds(timeoutInSeconds)).withMessage(message).ignoring(Exception.class)
                .until(f -> bs.getAsBoolean());
    }
}
