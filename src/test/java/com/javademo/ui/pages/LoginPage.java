package com.javademo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.javademo.ui.utils.WebDriverUtils;

public class LoginPage extends BasePage {
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.id("submit");
    private final By logoutButton = By.linkText("Log out");
    private final By loggedInMessage = By.cssSelector("h1");
    private final By errorMessage = By.id("error");
    private final String loginPageUrl = baseUrl + "practice-test-login/";

    public LoginPage(WebDriver driver) {
        super(driver); 
    }

    public void goTo() {
        driver.get(loginPageUrl);
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public void fillUsernameField(String text) {
        WebElement usernameField = WebDriverUtils.waitForVisibilityAndFind(driver, this.usernameField);
        usernameField.sendKeys(text);
    }

    public void fillPasswordField(String text) {
        WebElement passwordField = WebDriverUtils.waitForVisibilityAndFind(driver, this.passwordField);
        passwordField.sendKeys(text);
    }

    public void clickSubmitButton() {
        WebElement submitButton = WebDriverUtils.waitForVisibilityAndFind(driver, this.submitButton);
        submitButton.click();
    }

    public boolean isLogoutButtonVisible() {
        WebElement logOutButton = WebDriverUtils.waitForVisibilityAndFind(driver, this.logoutButton);
        return logOutButton.isDisplayed();
    }

    public String getLoginErrorMessage() {
        WebElement errorMessage = WebDriverUtils.waitForVisibilityAndFind(driver, this.errorMessage);
        return errorMessage.getText();
    }

    public String getLoggedInMessage() {
        WebElement loggedInMessage = WebDriverUtils.waitForVisibilityAndFind(driver, this.loggedInMessage);
        return loggedInMessage.getText();
    }
}
