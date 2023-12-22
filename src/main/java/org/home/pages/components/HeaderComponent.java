package org.home.pages.components;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.home.entities.TestUser;
import org.home.pages.BasePage;
import org.home.pages.CreateAccountPage;
import org.home.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HeaderComponent extends BasePage {
    private final By createAnAccountLink = By.xpath(".//header//a[text()='Create an Account']");
    private final By signInLink = By.cssSelector("header .authorization-link a");
    private final By userGreetingContainer = By.cssSelector("header .logged-in");
    private final By cartIcon = By.cssSelector(".showcart");

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Open create account page")
    public CreateAccountPage OpenCreateAnAccountPage() {
        this.driver.findElement(createAnAccountLink).click();
        return new CreateAccountPage(this.driver);
    }

    @Step("Open login page")
    public LoginPage OpenLoginPage() {
        this.driver.findElement(signInLink).click();
        return new LoginPage(this.driver);
    }

    @Step("Check login message in header")
    public boolean isUserLoggedIn(TestUser user) throws IOException {
        return Waiter.getInstance().waitForCondition(driver -> {
            String greeting = this.driver.findElement(this.userGreetingContainer).getText();
            return  greeting.contains(user.getFirstName()) && greeting.contains(user.getLastName());
        });
    }

    @Step("Open Cart popup")
    public CartComponent OpenCartPopup() throws IOException {
        Waiter.getInstance().waitForCondition(d -> d.findElement(this.cartIcon).isDisplayed());
        this.driver.findElement(this.cartIcon).click();
        return new CartComponent(this.driver);
    }
}
