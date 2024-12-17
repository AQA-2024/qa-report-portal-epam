package com.epam.qa.reportportal.utils;

import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import org.springframework.core.io.ClassPathResource;

import java.net.URL;

public class ResourceUtils {

    private static final Logger LOGGER = LoggerFactoryProvider.getLogger();

    /**
     * Retrieves the base URL of the resources directory, specifically for the "stories" folder.
     * <p>
     * If the resources are packed inside a JAR file, it adjusts the URL accordingly
     * to point to the JAR file location. If the resources are located in the file system,
     * it returns the appropriate file URL.
     * </p>
     *
     * @return The base URL of the resources' directory.
     * @throws RuntimeException if the resources could not be located.
     */
    public static URL getResourcesURL() {
        LOGGER.info("Attempting to retrieve the base URL of the 'stories' folder");
        try {
            String url = new ClassPathResource("stories").getURL().toString();
            LOGGER.debug(String.format("Initial URL retrieved: %s", url));
            url = url.substring(0, url.length() - "/stories".length());
            LOGGER.debug(String.format("URL after removing '/stories': %s", url));
            if (url.startsWith("jar:")) {
                LOGGER.debug("Detected URL inside a JAR file, adjusting the URL");
                url = url.substring("jar:".length());
                LOGGER.debug(String.format("URL after removing 'jar:' prefix: %s", url));
                if (url.endsWith(".jar!")) {
                    url = url.substring(0, url.length() - 1);
                    LOGGER.debug(String.format("URL after removing trailing '!': %s", url));
                }
            }
            URL finalURL = new URL(url);
            LOGGER.info(String.format("Successfully retrieved the base URL: %s", finalURL));
            return finalURL;
        } catch (Exception e) {
            LOGGER.error(String.format("Failed to retrieve the resources URL: %s", e.getMessage()));
            throw new RuntimeException("Resources not found", e);
        }
    }

}
