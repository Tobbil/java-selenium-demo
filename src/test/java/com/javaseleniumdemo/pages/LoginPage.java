package com.javaseleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.javaseleniumdemo.utils.WebDriverUtils;

public class LoginPage extends BasePage {
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By submitButton = By.id("submit");
    private final By logoutButton = By.linkText("Log out");
    private final By loggedInMessage = By.cssSelector("h1");
    private final By errorMessage = By.id("error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillUsernameField(String text) {
        WebElement usernameField = WebDriverUtils.waitForVisibilityAndFind(this.driver, this.usernameField);
        usernameField.sendKeys(text);
    }

    public void fillPasswordField(String text) {
        WebElement passwordField = WebDriverUtils.waitForVisibilityAndFind(this.driver, this.passwordField);
        passwordField.sendKeys(text);
    }

    public void clickSubmitButton() {
        WebElement submitButton = WebDriverUtils.waitForVisibilityAndFind(this.driver, this.submitButton);
        submitButton.click();
    }

    public boolean isLogoutButtonVisible() {
        WebElement logOutButton = WebDriverUtils.waitForVisibilityAndFind(this.driver, this.logoutButton);
        return logOutButton.isDisplayed();
    }

    public String getLoginErrorMessage() {
        WebElement errorMessage = WebDriverUtils.waitForVisibilityAndFind(this.driver, this.errorMessage);
        return errorMessage.getText();
    }

    public String getLoggedInMessage() {
        WebElement loggedInMessage = WebDriverUtils.waitForVisibilityAndFind(this.driver, this.loggedInMessage);
        return loggedInMessage.getText();
    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }
}
