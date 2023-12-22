package org.home.pages.components;

import io.qameta.allure.Step;
import org.home.pages.BasePage;
import org.home.pages.ProductItemsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class NavigationMenuComponent extends BasePage {
    public NavigationMenuComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Open navigation menu item")
    public ProductItemsPage OpenMenu(String menuItem, String subMenuItem) throws IOException {
        String locator = String.format(".//*[text()='%s']", menuItem);
        String sublocator = String.format(".//*[text()='%s']", subMenuItem);
        Actions action = new Actions(driver);
        action.moveToElement(this.driver.findElement(By.xpath(locator)))
                .moveToElement(this.driver.findElement(By.xpath(sublocator))).click().build().perform();
        return new ProductItemsPage(this.driver);
    }
}
