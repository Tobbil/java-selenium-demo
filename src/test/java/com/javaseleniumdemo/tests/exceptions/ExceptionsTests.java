package com.javaseleniumdemo.tests.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.javaseleniumdemo.pages.ExceptionsPage;
import com.javaseleniumdemo.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExceptionsTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionsTests.class);

    @Test(groups = {"regression", "smoke"})
    public void testAddButtonCreatesNewRow() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(this.driver);
        logger.info("Step 1: Go to page");
        exceptionsPage.goTo();
        logger.info("Step 2: Get number of rows");
        int rowsBefore = exceptionsPage.getRowQty();
        logger.info("Step 3: Click Add button");
        exceptionsPage.clickAddButton();
        logger.info("Step 4: Get number of rows");
        int rowsAfter = exceptionsPage.waitUntilRowQtyIncreasesFrom(rowsBefore);
        logger.info("Step 5: Check if number of rows increased by 1");
        Assert.assertEquals(rowsAfter, rowsBefore + 1, "Number of rows did not change");
    }

    @Test(groups = {"regression", "smoke"})
    public void testNewTextInputIsSaved() {
        String testInputText = "test input";
        ExceptionsPage exceptionsPage = new ExceptionsPage(this.driver);
        logger.info("Step 1: Go to page");
        exceptionsPage.goTo();
        int initialRowQty = exceptionsPage.getRowQty();
        logger.info("Step 2: Click Add button");
        exceptionsPage.clickAddButton();
        logger.info("Step 3: Wait for the second row to load");
        exceptionsPage.waitUntilRowQtyIncreasesFrom(initialRowQty);
        logger.info("Step 4: Fill text field for second row with test input");
        exceptionsPage.fillRowTextField(2, testInputText);
        logger.info("Step 5: Click Save button");
        exceptionsPage.clickSaveButton(2);
        logger.info("Step 6: Check if text was saved to the text field");
        Assert.assertTrue(exceptionsPage.isRowTextFieldFilledWithText(2, testInputText));
    }
}