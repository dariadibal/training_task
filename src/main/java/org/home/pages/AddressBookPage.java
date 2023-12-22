package org.home.pages;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.home.entities.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class AddressBookPage extends BasePage {
    private final By title = By.cssSelector(".page-title");
    private final By addressesSection = By.xpath("//*[@class='column main']");
    private final By mainSectionMessages = By.cssSelector("#maincontent .messages");
    private final By deleteAddressButton = By.cssSelector(".table .delete");
    private final By confirmationButtonPopup = By.cssSelector(".confirm .action-accept");

    public AddressBookPage(WebDriver driver) {
        super(driver);
    }

    @Step("Is page loaded")
    public boolean isPageLoaded() throws IOException {
        return Waiter.getInstance().waitForCondition(driver -> driver.findElement(this.title).isDisplayed());
    }

    @Step("Is address added")
    public boolean isAddressAdded(Address address) throws IOException {
        Waiter.getInstance().waitForCondition(driver -> driver.findElement(this.addressesSection).isDisplayed());
        String text = this.driver.findElement(this.addressesSection).getText();
        return text.contains(address.getStreet()) && text.contains(address.getCity())
                && text.contains(address.getZip()) && text.contains(address.getTelephone());
    }

    @Step("Delete addresses")
    public void deleteAddresses() throws IOException {
        List<WebElement> deleteBtn = driver.findElements(this.deleteAddressButton);
        for(int i = 0; i < deleteBtn.size(); i++){
            driver.findElement(deleteAddressButton).click();
            Waiter.getInstance().waitForCondition(d->d.findElement(this.confirmationButtonPopup).isDisplayed());
            this.driver.findElement(this.confirmationButtonPopup).click();
            waitForDeleteAddressConfirmationMessage();
        }
    }

    @Step("Wait for address removing confirmation message")
    private void waitForDeleteAddressConfirmationMessage() throws IOException {
        Waiter.getInstance().waitForCondition(driver -> {
            driver.findElement(this.mainSectionMessages).isDisplayed();
            String text = this.driver.findElement(this.mainSectionMessages).getText();
            return text.contains("You deleted the address.");
        });
    }
}
