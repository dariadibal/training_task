package org.home.selenium;

import org.home.core.Driver;
import org.home.core.Waiter;
import org.home.entities.Address;
import org.home.entities.TestUser;
import org.home.pages.AddNewAddressPage;
import org.home.pages.AddressBookPage;
import org.home.pages.components.HeaderComponent;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class AddressTest extends BaseTest {
    AddressBookPage addressBookPage;

    @Test(description = "AP-3 Verify the ability to add address")
    public void AddAddressTest() throws IOException {
        TestUser user = getLoggedInUser();
        HeaderComponent headerComponent = openApp();
        headerComponent.OpenLoginPage().login(user);
        AddNewAddressPage addNewAddressPage = new AddNewAddressPage(Driver.getInstance().getDriver());
        Address address = createAddress();
        addressBookPage = addNewAddressPage.open().AddAddress(address);
        assertTrue(addressBookPage.isPageLoaded(), "Address book page is not loaded!");
        assertTrue(addressBookPage.isAddressAdded(address), "Address is not saved!");
    }

    @AfterClass
    public void cleanup() throws IOException {
        addressBookPage.deleteAddresses();
        Driver.getInstance().close();
        Waiter.getInstance().close();
    }
}
