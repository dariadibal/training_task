package org.home.pages;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.home.entities.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CreateAccountPage extends BasePage {
    private final By firstNameInput = By.id("firstname");
    private final By lastNameInput = By.id("lastname");
    private final By emailInput = By.id("email_address");
    private final By passwordInput = By.id("password");
    private final By passwordConfirmationInput = By.id("password-confirmation");
    private final By submitButton= By.cssSelector(".submit");

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Create account")
    public AccountPage CreateAccount(TestUser user) throws IOException {
        Waiter.getInstance().waitForCondition(driver -> driver.findElement(this.firstNameInput).isDisplayed());
        this.driver.findElement(this.firstNameInput).sendKeys(user.getFirstName());
        this.driver.findElement(this.lastNameInput).sendKeys(user.getLastName());
        this.driver.findElement(this.emailInput).sendKeys(user.getEmail());
        this.driver.findElement(this.passwordInput).sendKeys(user.getPassword());
        this.driver.findElement(this.passwordConfirmationInput).sendKeys(user.getPassword());
        this.driver.findElement(this.submitButton).click();
        return new AccountPage(this.driver);
    }
}
