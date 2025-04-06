package com.javaseleniumdemo.pages;

import com.javaseleniumdemo.utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage {
    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForUrlToBe(String url) {
        WebDriverUtils.waitForUrlToBe(this.driver, url);
    }
}
