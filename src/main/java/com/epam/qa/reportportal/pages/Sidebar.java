package com.epam.qa.reportportal.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.epam.qa.reportportal.component.Button;
import com.epam.qa.reportportal.component.Icon;
import com.epam.qa.reportportal.component.Selector;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

@Component
public class Sidebar {
    public static final String SIDEBAR_BUTTON = "a[href*='%s']";
    public static final String USER_AVATAR_ICON = ".userBlock__avatar--x5As7";
    public static final String USER_AVATAR_MENU = ".userBlock__menu--FHvby";
    public static final String PROJECT_SELECTOR = ".//*[contains(@class, 'projectSelector__project-selector--C4soz')]";

    private static final Map<String, String> METRIC_SUBGROUP_BLOCK = Map.of(
            "Launches", "launches",
            "Dashboard", "dashboard",
            "Filters", "filters",
            "Debug", "userdebug/all",
            "Project Members", "members",
            "Project Settings", "settings");

    private static final Map<String, String> USER_MENU_ITEM = Map.of(
            "PROFILE", "Profile",
            "API", "Api",
            "LOGOUT", "Logout");

    public Button getSidebarButton(String button) {
        return new Button($$(String.format(SIDEBAR_BUTTON, METRIC_SUBGROUP_BLOCK.get(button)))
                .first());
    }

    public Icon getUserAvatarIcon() {
        return new Icon($(USER_AVATAR_ICON));
    }

    public SelenideElement getUserAvatarMenu() {
        return $(USER_AVATAR_MENU);
    }

    public SelenideElement getUserMenuItem(String item) {
        return getUserAvatarMenu().$$("*").findBy(Condition.text(USER_MENU_ITEM.get(item)));
    }

    public Selector getProjectSelector() {
        return new Selector($x(PROJECT_SELECTOR));
    }

}
