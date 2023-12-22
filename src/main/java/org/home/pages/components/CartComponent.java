package org.home.pages.components;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.home.pages.BasePage;
import org.home.pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CartComponent extends BasePage {
    private final By amountOfItems = By.cssSelector("#minicart-content-wrapper .count");
    private final By cartLink = By.cssSelector(".viewcart");

    public CartComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Get amount of items")
    public int getAmountOfItems() throws IOException {
        Waiter.getInstance().waitForCondition(d -> d.findElement(this.amountOfItems).isDisplayed());
        return Integer.parseInt(this.driver.findElement(this.amountOfItems).getText());
    }

    @Step("Open cart")
    public CartPage OpenCart() throws IOException {
        Waiter.getInstance().waitForCondition(d -> d.findElement(this.cartLink).isDisplayed());
        this.driver.findElement(this.cartLink).click();
        return new CartPage(this.driver);
    }
}
