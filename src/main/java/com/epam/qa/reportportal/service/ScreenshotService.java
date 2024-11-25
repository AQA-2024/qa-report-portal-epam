package com.epam.qa.reportportal.service;

import com.epam.qa.reportportal.utils.TestParameters;
import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ScreenshotService {

    private static final Logger LOGGER = LoggerFactoryProvider.getLogger();

    /**
     * Cleans screenshot directory.
     */
    public void cleanScreenshots() {
        File screenshotFolder = new File(TestParameters.SCREENSHOT_PATH);
        if (screenshotFolder.exists()) {
            try {
                FileUtils.cleanDirectory(screenshotFolder);
                LOGGER.info(String.format("Screenshots folder [%s] has been cleaned", screenshotFolder.getAbsolutePath()));
            } catch (IOException e) {
                LOGGER.info(String.format("Error on cleaning screenshots folder %s", e));
            }
        }
    }
}
