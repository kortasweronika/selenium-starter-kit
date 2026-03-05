package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][]{
                {"Selenium", "Selenium"},
                {"TestNG", "TestNG"},
                {"WebDriver", "WebDriver"}
        };
    }

    @Test(groups = {"smoke"}, dataProvider = "searchQueries")
    public void testGoogleSearch(String query, String expectedInTitle) {
        driver.get("https://www.google.com");

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.titleContains(expectedInTitle));
        Assert.assertTrue(driver.getTitle().contains(expectedInTitle),
                "Title should contain: " + expectedInTitle);
    }

    @Test(groups = {"smoke"})
    public void testGooglePageLoads() {
        driver.get("https://www.google.com");

        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box should be visible");
    }

    @Test(groups = {"regression"})
    public void testGoogleImagesLink() {
        driver.get("https://www.google.com");

        WebElement imagesLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Images")));
        imagesLink.click();

        wait.until(ExpectedConditions.urlContains("imghp"));
        Assert.assertTrue(driver.getCurrentUrl().contains("imghp"),
                "Should navigate to Google Images");
    }
}
