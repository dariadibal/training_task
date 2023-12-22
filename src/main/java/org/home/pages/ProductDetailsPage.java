package org.home.pages;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ProductDetailsPage extends BasePage {
    private final By toWishListLink = By.cssSelector(".product-addto-links .towishlist");
    private final By sizeOptions = By.cssSelector(".swatch-attribute.size .swatch-option");
    private final By colorOptions = By.cssSelector(".swatch-attribute.color .swatch-option");
    private final By addToCartButton = By.id("product-addtocart-button");
    private final By mainSectionMessages = By.cssSelector("#maincontent .messages");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add item to wish list")
    public WishListPage AddToWishList() {
        this.driver.findElement(this.toWishListLink).click();
        return new WishListPage(this.driver);
    }

    @Step("Choose item size")
    public ProductDetailsPage chooseSize() throws IOException, InterruptedException {
        Thread.sleep(3000);
        chooseFromList(this.sizeOptions);
        return this;
    }

    @Step("Choose item color")
    public ProductDetailsPage chooseColor() throws IOException {
        Waiter.getInstance().waitForCondition(d -> d.findElement(this.colorOptions).isDisplayed());
        chooseFromList(this.colorOptions);
        return this;
    }

    @Step("Add item to cart")
    public ProductDetailsPage addToCart() throws IOException {
        this.driver.findElement(this.addToCartButton).click();
        Waiter.getInstance().waitForCondition(driver -> {
            driver.findElement(this.mainSectionMessages).isDisplayed();
            String text = this.driver.findElement(this.mainSectionMessages).getText();
            return text.contains("You added") &&  text.contains("to your shopping cart");
        });
        return this;
    }

    private void chooseFromList(By locator) throws IOException {
        Waiter.getInstance().waitForCondition(d -> d.findElement(locator).isDisplayed());
        List<WebElement> options = this.driver.findElements(locator);
        int itemIndex = new Random().nextInt(options.size());
        options.get(itemIndex).click();
    }
}
