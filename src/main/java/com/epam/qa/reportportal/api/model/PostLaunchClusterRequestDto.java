package com.epam.qa.reportportal.api.model;

import lombok.Data;

@Data
public class PostLaunchClusterRequestDto {
    int launchId;
    boolean removeNumbers;
}
