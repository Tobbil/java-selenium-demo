package com.javademo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.javademo.ui.utils.WebDriverUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExceptionsPage extends BasePage {

    private final By addBtn = By.id("add_btn");
    private final By rowsContainer = By.id("rows");
    private final String exceptionsPageUrl = baseUrl + "practice-test-exceptions/";
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public ExceptionsPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        driver.get(exceptionsPageUrl);
    }

    public void clickAddButton() {
        WebElement button = WebDriverUtils.waitForVisibilityAndFind(driver, addBtn);
        button.click();
    }

    public int getRowQty() {
        WebElement freshContainer = WebDriverUtils.waitForVisibilityAndFind(driver, rowsContainer);
        List<WebElement> rows = freshContainer.findElements(By.cssSelector("div[id^='row']"));
        return rows.size();
    }

    public int waitUntilRowQtyIncreasesFrom(int previousCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(driver -> {
            WebElement freshContainer = WebDriverUtils.waitForVisibilityAndFind(driver, rowsContainer);
            List<WebElement> rows = freshContainer.findElements(By.cssSelector("div[id^='row']"));
            int currentCount = rows.size();
            return currentCount > previousCount ? currentCount : null;
        });
    }

    public void fillRowTextField(int rowNumber, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String xpath = String.format("//div[@id='row%d']/input", rowNumber);
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        input.clear();
        input.sendKeys(text);
    }

    public void clickSaveButton(int rowNumber) {
        String xpath = String.format("//div[@id='row%d']/button[@id='save_btn']", rowNumber);
        WebElement saveBtnElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        saveBtnElement.click();
    }

    public boolean isRowTextFieldFilledWithText(int rowNumber, String text) {
        String xpath = String.format("//div[@id='row%d']/input", rowNumber);
        WebElement textField = WebDriverUtils.waitForVisibilityAndFind(driver, By.xpath(xpath));

        return textField.getText().equals(text);
    }
}
