package com.javaseleniumdemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private static void configureHeadlessOptions(Object options) {
        String headless = System.getProperty("headless", "false");
        if (headless.equalsIgnoreCase("true")) {
            if (options instanceof ChromeOptions) {
                ((ChromeOptions) options).addArguments("--headless", "--disable-gpu");
            } else if (options instanceof FirefoxOptions) {
                ((FirefoxOptions) options).addArguments("--headless", "--disable-gpu");
            }
        }
    }

    public static WebDriver createWebDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                configureHeadlessOptions(options);
                yield new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                configureHeadlessOptions(options);
                yield new FirefoxDriver(options);
            }
            default -> {
                System.out.println("Configuration for " + browser + " is missing, running tests with Chrome");
                ChromeOptions options = new ChromeOptions();
                configureHeadlessOptions(options);
                yield new ChromeDriver(options);
            }
        };
    }
}
