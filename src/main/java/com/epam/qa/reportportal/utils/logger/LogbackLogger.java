package com.epam.qa.reportportal.utils.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackLogger implements com.epam.qa.reportportal.utils.logger.Logger {

    private final Logger logger;

    public LogbackLogger() {
        final Throwable t = new Throwable();
        t.fillInStackTrace();
        final String clazz = t.getStackTrace()[1].getClassName();
        logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }
}
