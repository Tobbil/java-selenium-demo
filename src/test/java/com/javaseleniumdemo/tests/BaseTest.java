package com.javaseleniumdemo.tests;

import com.javaseleniumdemo.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl = "https://practicetestautomation.com/";

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {
        driver = WebDriverFactory.createWebDriver(browser);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
