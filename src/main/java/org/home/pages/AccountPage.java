package org.home.pages;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.home.entities.TestUser;
import org.openqa.selenium.By;;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AccountPage extends BasePage {
    private final By title = By.cssSelector(".page-title");
    private final By contactInformationBox = By.cssSelector(".box-information");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Account page is opened")
    public boolean isAccountPageOpened() throws IOException {
        return Waiter.getInstance().waitForCondition(driver -> driver.findElement(this.title).isDisplayed());
    }

    @Step("Check account info correctness")
    public boolean isContactInfoCorrect(TestUser user) throws IOException {
        Waiter.getInstance().waitForCondition(d -> d.findElement(this.contactInformationBox).isDisplayed());
        String boxText = this.driver.findElement(this.contactInformationBox).getText();
        return boxText.contains(user.getFirstName()) && boxText.contains(user.getLastName())
                && boxText.contains(user.getEmail());
    }
}
