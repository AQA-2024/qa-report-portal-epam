package com.epam.qa.reportportal.utils.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Logger implements com.epam.qa.reportportal.utils.logger.Logger {

    private final Logger logger;

    public Log4j2Logger() {
        final Throwable t = new Throwable();
        t.fillInStackTrace();
        final String account0 = t.getStackTrace()[1].getClassName();
        logger = LogManager.getLogger(account0);
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
