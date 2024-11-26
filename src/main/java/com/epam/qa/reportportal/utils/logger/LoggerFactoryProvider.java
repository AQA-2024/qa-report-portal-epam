package com.epam.qa.reportportal.utils.logger;

import com.epam.qa.reportportal.utils.TestParameters;

public class LoggerFactoryProvider {

    private static final String LOG4J2 = "Log4j2";
    private static final String LOGBACK = "Logback";

    public static Logger getLogger() {
        switch (TestParameters.LOGGER) {
            case LOG4J2:
                return new Log4j2Logger();
            case LOGBACK:
            default:
                return new LogbackLogger();
        }
    }
}
