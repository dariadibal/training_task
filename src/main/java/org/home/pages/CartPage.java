package org.home.pages;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CartPage extends BasePage {
    private final By cartItem = By.cssSelector(".cart.item");
    private final By deleteButton = By.cssSelector(".cart .action-delete");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Delete all items from Cart")
    public void deleteAllItems() throws IOException {
        Waiter.getInstance().waitForCondition(d -> d.findElement(this.cartItem).isDisplayed());
        int amountOfButtons = this.driver.findElements(this.deleteButton).size();
        for(int i = 0; i < amountOfButtons; i++) {
            Waiter.getInstance().waitForCondition(d -> d.findElement(this.cartItem).isDisplayed());
            this.driver.findElement(this.deleteButton).click();
        }
    }
}
