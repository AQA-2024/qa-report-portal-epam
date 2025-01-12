package com.epam.qa.reportportal.api.controller;

import com.epam.qa.reportportal.api.model.FinishLaunchRequestDto;
import com.epam.qa.reportportal.api.model.PostLaunchRequestDto;
import com.epam.qa.reportportal.api.model.PostLaunchClusterRequestDto;
import com.epam.qa.reportportal.service.PropertiesConfig;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;


public class LaunchController {

    private static final PropertiesConfig config = ConfigFactory.create(PropertiesConfig.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String PROJECT_NAME_PATH = "project_name";

    private static final String BASE_URI = config.baseUri();
    private static final String GET_LAUNCH_LIST_PATH = "/{project_name}/launch";
    private static final String GET_LAUNCH_PATH = "/{project_name}/launch/{id}";
    private static final String POST_LAUNCH_CLUSTER_PATH = "/{project_name}/launch/cluster";
    private static final String POST_LAUNCH_PATH = "/{project_name}/launch";
    private static final String UPDATE_LAUNCH_PATH = "/{project_name}/launch/{id}/update";
    private static final String FINISH_LAUNCH_PATH = "/{project_name}/launch/{id}/finish";
    private static final String DELETE_LAUNCH_PATH = "/{project_name}/launch";
    private static final String TOKEN = "Bearer ApiTests_yNij4ZivSoWBVWRvMDCImag6e7QmbqT7X2nWiylpakFF4TNJ9FQXfNbhFM72mq2S";

    private RequestSpecification getRequestSpecification(String projectName) {
        return given()
                .baseUri(BASE_URI)
                .header(AUTHORIZATION_HEADER, TOKEN)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .pathParam(PROJECT_NAME_PATH, projectName)
                .log().all();
    }

    public ValidatableResponse getList(String projectName) {
        return getRequestSpecification(projectName).
        when().
                get(BASE_URI + GET_LAUNCH_LIST_PATH).
        then().
                log().all();
    }

    public ValidatableResponse getOne(String projectName, String id) {
        return getRequestSpecification(projectName).
                pathParam("id", id).
        when().
                get(BASE_URI + GET_LAUNCH_PATH).
                then().
                log().all();
    }

    public ValidatableResponse createCluster(String projectName, PostLaunchClusterRequestDto dto) {
        return getRequestSpecification(projectName).
                body(dto).
                log().all().
        when().
                post(BASE_URI + POST_LAUNCH_CLUSTER_PATH).
        then().
                log().all();
    }
    
    public ValidatableResponse create(String projectName, PostLaunchRequestDto dto) {
        return getRequestSpecification(projectName).
                body(dto).
                log().all().
        when().
                post(BASE_URI + POST_LAUNCH_PATH).
                then().
                log().all();
    }

    public ValidatableResponse update(String projectName, int id, PostLaunchRequestDto dto) {
        return getRequestSpecification(projectName).
                pathParam("id", id).
                body(dto).
        when().
                put(BASE_URI + UPDATE_LAUNCH_PATH).
        then().
                log().all();
    }

    public ValidatableResponse finish(String projectName, String id, FinishLaunchRequestDto dto) {
        return getRequestSpecification(projectName).
                pathParam("id", id).
                body(dto).
        when().
                put(BASE_URI + FINISH_LAUNCH_PATH).
        then().
                log().all();
    }

    public ValidatableResponse delete(String projectName, int id) {
        return getRequestSpecification(projectName).
                queryParam("ids", id).
        when().
                delete(BASE_URI + DELETE_LAUNCH_PATH).
        then().
                log().all();
    }
}
