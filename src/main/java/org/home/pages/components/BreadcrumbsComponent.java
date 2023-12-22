package org.home.pages.components;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.home.pages.BasePage;
import org.home.pages.ProductItemsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BreadcrumbsComponent extends BasePage {

    public BreadcrumbsComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Breadcrumbs item")
    public ProductItemsPage ClickOnItem(String item) throws IOException {
        String itemTemplate = ".//*[@class='breadcrumbs']//a[text()='%s']";
        By locator = By.xpath(String.format(itemTemplate, item));
        Waiter.getInstance().waitForCondition(d -> d.findElement(locator).isDisplayed());
        this.driver.findElement(locator).click();
        return new ProductItemsPage(this.driver);
    }
}
