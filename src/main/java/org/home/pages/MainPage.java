package org.home.pages;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.home.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class MainPage extends BasePage {
    private final By mainSection = By.id("maincontent");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Loaded page")
    public boolean isPageLoaded() throws IOException {
        return Waiter.getInstance().waitForCondition(driver -> driver.findElement(this.mainSection).isDisplayed());
    }
}
