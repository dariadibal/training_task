package org.home.pages;

import io.qameta.allure.Step;
import net.bytebuddy.asm.Advice;
import org.home.core.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.time.Duration;

import static java.time.Duration.*;

public class WishListPage extends BasePage {
    private final By mainSectionMessages = By.cssSelector("#maincontent .messages");
    private final By title = By.cssSelector(".page-title");
    private final By messageInfoEmpty = By.cssSelector(".message.empty");


    public WishListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Is add message shows")
    public boolean isAddMessageShows() throws IOException {
        return  Waiter.getInstance().waitForCondition(driver -> {
            driver.findElement(this.mainSectionMessages).isDisplayed();
            String text = this.driver.findElement(this.mainSectionMessages).getText();
            return text.contains("has been added to your Wish List.");
        });
    }

    @Step("Is wish list page loaded")
    public boolean isPageLoaded() throws IOException {
        return Waiter.getInstance().waitForCondition(driver -> driver.findElement(this.title).isDisplayed());
    }

    @Step("Delete item from wish list")
    public void deleteProductItem() throws IOException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementsByClassName('btn-remove action delete')[0].click()");
        Waiter.getInstance().waitForCondition(d -> d.findElement(this.messageInfoEmpty).isDisplayed());
    }
}
