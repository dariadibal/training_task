package org.home.selenium;

import org.home.entities.TestUser;
import org.home.pages.LoginPage;
import org.home.pages.components.HeaderComponent;
import org.home.pages.MainPage;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(description = "AP-2 Verify the ability to login in account")
    public void SuccessfulLoginTest() throws IOException, InterruptedException {
        TestUser user = getLoggedInUser();
        HeaderComponent headerComponent = openApp();
        LoginPage loginPage = headerComponent.OpenLoginPage();
        MainPage mainPage = loginPage.login(user);

        assertTrue(mainPage.isPageLoaded(), "Main page is not loaded!");
        assertTrue(headerComponent.isUserLoggedIn(user), "Logged in incorrect user!");
    }
}
