package com.epam.qa.reportportal.pages;

import com.epam.qa.reportportal.component.Button;
import com.epam.qa.reportportal.component.Input;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class LoginPage {

    private static final String LOGIN_INPUT = "[name='login']";
    private static final String PASSWORD_INPUT = "[name='password']";
    private static final String LOGIN_BUTTON = "//button[@type='submit']";

    /**
     * Returns the input field for the login.
     *
     * @return an instance of {@link Input} representing the login input field.
     */
    public Input getLoginInput() {
        return new Input($(LOGIN_INPUT));
    }

    /**
     * Returns the input field for the password.
     *
     * @return an instance of {@link Input} representing the password input field.
     */
    public Input getPasswordInput() {
        return new Input($(PASSWORD_INPUT));
    }

    /**
     * Returns the login button.
     *
     * @return an instance of {@link Button} representing the login button.
     */
    public Button getLoginButton() {
        return  new Button($(By.xpath(LOGIN_BUTTON)));
    }

}
