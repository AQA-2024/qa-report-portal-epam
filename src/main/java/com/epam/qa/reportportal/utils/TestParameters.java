package com.epam.qa.reportportal.utils;

public final class TestParameters {

    /**
     * The constant STORY_TO_RUN.
     */
    public static final String STORY_TO_RUN = System.getProperty("qaa.story", "**/*");

    /**
     * The constant META_FILTERS.
     */
    public static final String META_FILTERS =
            System.getProperty("qaa.metaFilters", "-make-screenshot -ignore -ignoreUsualRun -skip");

    /**
     * Path to the directory which should contain screenshots.
     */
    public static final String SCREENSHOT_PATH = System.getProperty("qaa.screenshot.path", "build/reports/tests");

    /**
     * The constant SYSTEM_ENVIRONMENT.
     */
    public static final String SYSTEM_ENVIRONMENT = System.getProperty("qaa.environment", "local");

    /**
     * The constant BROWSER.
     */
    public static final BrowserType BROWSER = BrowserType.resolve(System.getProperty("qaa.browser", "chrome"));

    /**
     * The constant LOGGER.
     */
    public static final String LOGGER = System.getProperty("qaa.logger", "Logback");


}
