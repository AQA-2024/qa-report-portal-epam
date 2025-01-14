package com.epam.qa.reportportal.utils;

import com.google.common.base.Preconditions;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public final class EnvironmentProviderHook {

    private static final String ENV_PROP = "qaa.environment";
    private static final String RUP_ENV_PROP = "rup.environment";

    private EnvironmentProviderHook() {
        // no ctor
    }

    /**
     * Sets up environment (if it is not yet set) to local env.
     */
    public static void ensureEnvironmentSet() {
        final String environment = defaultIfEmpty(System.getProperty(ENV_PROP), System.getenv(ENV_PROP));
        Preconditions.checkArgument(isNotEmpty(environment),
            "qaa.environment or rup.environment java properties must be set");
        System.setProperty(RUP_ENV_PROP, environment.toLowerCase());
    }
}
