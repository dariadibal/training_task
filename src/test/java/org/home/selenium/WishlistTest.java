package org.home.selenium;

import org.home.core.Driver;
import org.home.core.Waiter;
import org.home.entities.TestUser;
import org.home.pages.LoginPage;
import org.home.pages.WishListPage;
import org.home.pages.components.HeaderComponent;
import org.home.pages.components.NavigationMenuComponent;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class WishlistTest extends BaseTest {
    WishListPage wishListPage;

    @Test(description = "AP-4 Verify the ability to add to Wishlist")
    public void AddToWishListTest() throws IOException {
        TestUser user = getLoggedInUser();
        HeaderComponent headerComponent = openApp();
        LoginPage loginPage = headerComponent.OpenLoginPage();
        loginPage.login(user);
        NavigationMenuComponent navigation = new NavigationMenuComponent(Driver.getInstance().getDriver());
        wishListPage = navigation.OpenMenu("Women", "Tops")
                .OpenProductDetails()
                .AddToWishList();

        assertTrue(wishListPage.isPageLoaded(), "Page is not loaded!");
        assertTrue(wishListPage.isAddMessageShows(), "Message is not present!");
    }

    @AfterClass
    public void cleanup() throws IOException {
        wishListPage.deleteProductItem();
        Driver.getInstance().getDriver().close();
        Waiter.getInstance().close();
    }
}
