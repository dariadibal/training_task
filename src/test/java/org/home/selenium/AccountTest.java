package org.home.selenium;

import org.home.entities.TestUser;
import org.home.pages.AccountPage;
import org.home.pages.CreateAccountPage;
import org.home.pages.components.HeaderComponent;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class AccountTest extends BaseTest {

    @Test(description = "AP-1 Verify the ability to create an account 1")
    public void CreateAccountTest() throws IOException {
        TestUser user = createUser();
        HeaderComponent headerComponent = openApp();
        CreateAccountPage createAccountPage = headerComponent.OpenCreateAnAccountPage();
        AccountPage accountPage = createAccountPage.CreateAccount(user);

        assertTrue(accountPage.isAccountPageOpened(), "Successful creation message is not appeared!");
        assertTrue(accountPage.isContactInfoCorrect(user), "User data is incorrect!");
    }
}
