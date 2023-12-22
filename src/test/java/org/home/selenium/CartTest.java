package org.home.selenium;

import org.home.core.Driver;
import org.home.core.Waiter;
import org.home.entities.TestUser;
import org.home.pages.CartPage;
import org.home.pages.LoginPage;
import org.home.pages.ProductDetailsPage;
import org.home.pages.components.BreadcrumbsComponent;
import org.home.pages.components.CartComponent;
import org.home.pages.components.HeaderComponent;
import org.home.pages.components.NavigationMenuComponent;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {
    CartPage cartPage;

    @Test(description = "AP-5 Verify the ability to add to cart")
    public void AddItemToCartTest() throws IOException, InterruptedException {
        int expectedNumberOfItems = 3;
        TestUser user = getLoggedInUser();
        HeaderComponent headerComponent = openApp();
        LoginPage loginPage = headerComponent.OpenLoginPage();
        loginPage.login(user);
        NavigationMenuComponent navigation = new NavigationMenuComponent(Driver.getInstance().getDriver());
        ProductDetailsPage productDetails = navigation.OpenMenu("Women", "Tops").OpenProductDetails();
        addItemsToCart(productDetails, expectedNumberOfItems);
        CartComponent cartComponent = headerComponent.OpenCartPopup();
        int amountOfItems = cartComponent.getAmountOfItems();
        cartPage = cartComponent.OpenCart();
        assertEquals(amountOfItems, expectedNumberOfItems, "Wrong amount of items");
    }

    @AfterClass
    public void cleanup() throws IOException {
        cartPage.deleteAllItems();
        Driver.getInstance().getDriver().close();
        Waiter.getInstance().close();
    }

    private void addItemsToCart(ProductDetailsPage page, int count) throws IOException, InterruptedException {
        BreadcrumbsComponent breadcrumbsComponent = new BreadcrumbsComponent(Driver.getInstance().getDriver());
        for(int i = 0; i < count; i++) {
            page.chooseSize().chooseColor().addToCart();
            page = breadcrumbsComponent.ClickOnItem("Tops").OpenProductDetails();
        }
    }
}
