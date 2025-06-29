package com.javademo.ui.tests.login;

import com.javademo.ui.dataproviders.TestDataProviders;
import com.javademo.ui.pages.LoginPage;
import com.javademo.ui.tests.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTests.class);

    @Test(groups = {"positive", "regression", "smoke"})
    public void testCorrectLogin() {
        String expectedLoggedInMessage = "Logged In Successfully";
        String expectedUrlAfterLogin = baseUrl + "logged-in-successfully/";
        LoginPage loginPage = new LoginPage(driver);
        logger.info("Step 1: Go to page");
        loginPage.goTo();
        logger.info("Step 2: Fill username field with correct login");
        loginPage.fillUsernameField("student");
        logger.info("Step 3: Fill password field with correct password");
        loginPage.fillPasswordField("Password123");
        logger.info("Step 4: Click submit button");
        loginPage.clickSubmitButton();
        loginPage.waitForUrlToBe(expectedUrlAfterLogin);
        logger.info("Step 5: Check if URL has changed");
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrlAfterLogin);
        logger.info("Step 6: Check if logged in message is visible");
        Assert.assertEquals(loginPage.getLoggedInMessage(), expectedLoggedInMessage);
        logger.info("Step 7: Check if logout button is visible");
        Assert.assertTrue(loginPage.isLogoutButtonVisible());
    }

    @Test(dataProvider = "negativeLoginData", dataProviderClass = TestDataProviders.class, groups = {"negative", "regression"})
    public void testIncorrectLogin(String username, String password, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        logger.info("Step 1: Go to page");
        loginPage.goTo();
        logger.info("Step 2: Fill username field with test data");
        loginPage.fillUsernameField(username);
        logger.info("Step 3: Fill password field with test data");
        loginPage.fillPasswordField(password);
        logger.info("Step 4: Click submit button");
        loginPage.clickSubmitButton();
        logger.info("Step 5: Check if error message is visible");
        Assert.assertEquals(loginPage.getLoginErrorMessage(), expectedErrorMessage);
        logger.info("Step 6: Check if URL has not changed");
        Assert.assertEquals(driver.getCurrentUrl(), loginPage.getLoginPageUrl());
    }
}
