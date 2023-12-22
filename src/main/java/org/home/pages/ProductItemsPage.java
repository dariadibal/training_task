package org.home.pages;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Random;

public class ProductItemsPage extends BasePage {
    private final By productItemLink = By.cssSelector(".product-item-link");

    public ProductItemsPage(WebDriver driver) throws IOException {
        super(driver);
        isPageLoaded();
    }

    public boolean isPageLoaded() throws IOException {
        return Waiter.getInstance().waitForCondition(d -> d.findElement(this.productItemLink).isDisplayed());
    }

    @Step("Open item details")
    public ProductDetailsPage OpenProductDetails() throws IOException {
        Waiter.getInstance().waitForCondition(d -> d.findElement(this.productItemLink).isDisplayed());
        int amountOfItems = this.driver.findElements(this.productItemLink).size();
        int itemIndex = new Random().nextInt(amountOfItems - 1);
        this.driver.findElements(this.productItemLink).get(itemIndex).click();
        return new ProductDetailsPage(this.driver);
    }
}
