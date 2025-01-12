package com.epam.qa.reportportal.service;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:env/prod/config.properties")
public interface PropertiesConfig extends Config {

    @Config.Key("user.login")
    String login();

    @Config.Key("user.password")
    String password();

    @Config.Key("report.portal.url")
    String url();

    @Config.Key("report.portal.project.name")
    String projectName();

    @Config.Key("report.portal.base.uri")
    String baseUri();
}
