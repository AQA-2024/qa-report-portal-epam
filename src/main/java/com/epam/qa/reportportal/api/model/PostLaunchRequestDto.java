package com.epam.qa.reportportal.api.model;

import lombok.Data;

@Data
public class PostLaunchRequestDto {
    String id;
    String startTime;
    String name;
    String model;
    String description;
}
