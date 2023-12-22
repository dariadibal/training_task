package org.home.pages;

import io.qameta.allure.Step;
import org.home.entities.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("pass");
    private final By loginButton = By.id("send2");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login")
    public MainPage login(TestUser user) {
        this.driver.findElement(this.emailInput).sendKeys(user.getEmail());
        this.driver.findElement(this.passwordInput).sendKeys(user.getPassword());
        this.driver.findElement(this.loginButton).click();
        return new MainPage(this.driver);
    }
}
