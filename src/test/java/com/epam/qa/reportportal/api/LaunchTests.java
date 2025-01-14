package com.epam.qa.reportportal.api;

import com.epam.qa.reportportal.api.controller.LaunchController;
import com.epam.qa.reportportal.api.model.FinishLaunchRequestDto;
import com.epam.qa.reportportal.api.model.PostLaunchRequestDto;
import com.epam.qa.reportportal.api.model.PostLaunchClusterRequestDto;
import com.epam.qa.reportportal.service.PropertiesConfig;
import com.epam.qa.reportportal.utils.logger.Logger;
import com.epam.qa.reportportal.utils.logger.LoggerFactoryProvider;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;

public class LaunchTests {

    private final LaunchController launchController = new LaunchController();
    private static final PropertiesConfig config = ConfigFactory.create(PropertiesConfig.class);

    public static final String PROJECT_NAME = config.projectName();
    public static final String INVALID_PROJECT_NAME = "project_name";

    public static final String ERROR_INVALID_PROJECT =
            "You do not have enough permissions. Please check the list of your available projects.";
    public static final String ERROR_INVALID_LAUNCH_ID =
            "Launch '%s' not found. Did you use correct Launch ID?";
    public static final String ERROR_UPDATE_INVALID_PROJECT =
            "You do not have enough permissions.";

    public static final String SUCCESS_CLUSTER_CREATED =
            "Clusters generation for launch with ID='%s' started.";
    public static final String SUCCESS_LAUNCH_UPDATED =
            "Launch with ID = '%s' successfully updated.";

    @Test
    public void getTotalLaunchSize() {
        launchController.getList(PROJECT_NAME).
                statusCode(200).
                body("content.size()", equalTo(5)).
                body("page.totalElements", equalTo(5));
    }

    @Test
    public void getInvalidProjectName() {
        launchController.getList(INVALID_PROJECT_NAME).
                statusCode(403).
                body("message", equalTo(ERROR_INVALID_PROJECT));
    }

    @Test
    public void postLaunchCluster() {
        int launchId = 8426398;
        PostLaunchClusterRequestDto dto = new PostLaunchClusterRequestDto();
        dto.setLaunchId(launchId);
        dto.setRemoveNumbers(true);

        launchController.createCluster(PROJECT_NAME, dto).
                statusCode(200).
                body("message", equalTo(String.format(SUCCESS_CLUSTER_CREATED, launchId)));
    }

    @Test
    public void updateLaunch() {
        int launchId = 8426397;
        PostLaunchRequestDto dto = new PostLaunchRequestDto();
        dto.setModel("DEFAULT");
        dto.setDescription("string");

        launchController.update(PROJECT_NAME, launchId, dto).
                statusCode(200).
                body("message", equalTo(String.format(SUCCESS_LAUNCH_UPDATED, launchId)));
    }

    @Test
    public void updateInvalidIDLaunch() {
        int launchId = -4;
        PostLaunchRequestDto dto = new PostLaunchRequestDto();
        dto.setModel("DEFAULT");
        dto.setDescription("string");

        launchController.update(PROJECT_NAME, launchId, dto).
                statusCode(404).
                body("message", equalTo(String.format(ERROR_INVALID_LAUNCH_ID, launchId)));
    }

    @Test
    public void updateLaunchByInvalidProjectName() {
        PostLaunchRequestDto dto = new PostLaunchRequestDto();
        launchController.update("project_name", 8426397, dto).
                statusCode(403).
                body("message", equalTo(ERROR_UPDATE_INVALID_PROJECT));
    }

    @Test
    public void deleteLaunch() {
        PostLaunchRequestDto createLaunchDto = createLaunchDto("name", "description");

        String launchUuid = launchController.create(PROJECT_NAME, createLaunchDto)
                .extract()
                .path("id");

        FinishLaunchRequestDto finishLaunchDto = createFinishLaunchDto();
        launchController.finish(PROJECT_NAME, launchUuid, finishLaunchDto)
                .statusCode(200);

        int launchId = launchController.getOne(PROJECT_NAME, launchUuid)
                .extract()
                .path("id");

        launchController.delete(PROJECT_NAME, launchId)
                .statusCode(200);
    }

    private String getCurrentIsoTime() {
        return DateTimeFormatter.ISO_INSTANT.format(Instant.now());
    }

    private PostLaunchRequestDto createLaunchDto(String name, String description) {
        PostLaunchRequestDto dto = new PostLaunchRequestDto();
        dto.setName(name);
        dto.setDescription(description);
        dto.setStartTime(getCurrentIsoTime());
        return dto;
    }

    private FinishLaunchRequestDto createFinishLaunchDto() {
        FinishLaunchRequestDto dto = new FinishLaunchRequestDto();
        dto.setEndTime(getCurrentIsoTime());
        return dto;
    }
}
