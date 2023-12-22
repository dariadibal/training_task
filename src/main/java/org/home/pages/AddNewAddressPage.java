package org.home.pages;

import io.qameta.allure.Step;
import org.home.core.Waiter;
import org.home.entities.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class AddNewAddressPage extends BasePage {
    private final String url = "https://magento.softwaretestingboard.com/customer/address/index/";
    private final By streetInput = By.id("street_1");
    private final By cityInput = By.id("city");
    private final By zipInput = By.id("zip");
    private final By telephoneInput = By.id("telephone");
    private final By regionSelect = By.id("region_id");
    private final By countrySelect = By.id("country");
    private final By saveButton = By.cssSelector(".save");
    private final By addNewAddressButton = By.cssSelector(".add");

    public AddNewAddressPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open add new address page")
    public AddNewAddressPage open() {
        this.driver.get(url);
        return this;
    }

    @Step("Add address")
    public AddressBookPage AddAddress(Address address) throws IOException {
        if (this.driver.findElement(this.addNewAddressButton).isDisplayed()) {
            this.driver.findElement(this.addNewAddressButton).click();
        }
        Waiter.getInstance().waitForCondition(driver -> driver.findElement(this.streetInput).isDisplayed());
        this.driver.findElement(this.streetInput).sendKeys(address.getStreet());
        this.driver.findElement(this.cityInput).sendKeys(address.getCity());
        this.driver.findElement(this.zipInput).sendKeys(address.getZip());
        this.driver.findElement(this.telephoneInput).sendKeys(address.getTelephone());
        select(this.regionSelect, address.getState());
        select(this.countrySelect, address.getCounty());
        this.driver.findElement(this.saveButton).click();
        return new AddressBookPage(this.driver);
    }

    private void select(By locator, String value) {
        WebElement regionSelect = driver.findElement(locator);
        Select select = new Select(regionSelect);
        select.selectByValue(value);
    }
}
