package com.javaseleniumdemo.pages;

import com.javaseleniumdemo.utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    protected String baseUrl = "https://practicetestautomation.com/";

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForUrlToBe(String url) {
        WebDriverUtils.waitForUrlToBe(this.driver, url);
    }
}
