package com.epam.qa.reportportal.utils;

public enum BrowserType {

    /** Chrome. */
    CHROME("chrome"),

    /** Firefox. */
    FIREFOX("firefox");

    private String browser;

    /**
     * Constructor.
     *
     * @param browser browser name.
     */
    BrowserType(String browser) {
        this.browser = browser;
    }

    /**
     * Get browser.
     *
     * @return browser
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * Resolves browser type by its name.
     *
     * @param browser browser name.
     * @return browser type.
     */
    static BrowserType resolve(String browser) {
        for (BrowserType browserType : BrowserType.values()) {
            if (browserType.getBrowser().equals(browser)) {
                return browserType;
            }
        }
        throw new IllegalArgumentException(String.format("Unsupported browser [%s]", browser));
    }
}
