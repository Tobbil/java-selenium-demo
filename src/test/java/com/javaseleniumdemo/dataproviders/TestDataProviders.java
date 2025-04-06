package com.javaseleniumdemo.dataproviders;

import org.testng.annotations.DataProvider;

public class TestDataProviders {

    @DataProvider(name = "negativeLoginData")
    public Object[][] negativeLoginData() {
        return new Object[][] {
                {"wrong_user", "Password123", "Your username is invalid!"},
                {"student", "Password124", "Your password is invalid!"},
                {"", "Password123", "Your username is invalid!"},
                {"student", "", "Your password is invalid!"}
        };
    }
}
