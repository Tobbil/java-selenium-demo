package com.javaseleniumdemo.tests.login;

import com.javaseleniumdemo.dataproviders.TestDataProviders;
import com.javaseleniumdemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest {

    @Test(groups = {"positive", "regression", "smoke"})
    public void testCorrectLogin() {
        String expectedLoggedInMessage = "Logged In Successfully";
        String expectedUrlAfterLogin = baseUrl + "logged-in-successfully/";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsernameField("student");
        loginPage.fillPasswordField("Password123");
        loginPage.clickSubmitButton();

        loginPage.waitForUrlToBe(baseUrl + "practice-test-login/");
        Assert.assertEquals(loginPage.getCurrentUrl(), expectedUrlAfterLogin);
        Assert.assertEquals(loginPage.getLoggedInMessage(), expectedLoggedInMessage);
        Assert.assertTrue(loginPage.isLogoutButtonVisible());
    }

    @Test(dataProvider = "negativeLoginData", dataProviderClass = TestDataProviders.class, groups = {"negative", "regression"})
    public void testIncorrectLogin(String username, String password, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickSubmitButton();

        Assert.assertEquals(loginPage.getLoginErrorMessage(), expectedErrorMessage);
        Assert.assertEquals(loginPage.getCurrentUrl(), loginPageUrl);
    }
}
