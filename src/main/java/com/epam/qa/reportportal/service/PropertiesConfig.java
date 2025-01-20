package com.epam.qa.reportportal.service;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:env/prod/config.properties")
public interface PropertiesConfig extends Config {

    @Key("user.login")
    String login();

    @Key("user.password")
    String password();

    @Key("report.portal.url")
    String url();

    @Key("report.portal.project.name")
    String projectName();

    @Key("report.portal.base.uri")
    String baseUri();

    @Key("report.portal.api.token")
    String token();
}
