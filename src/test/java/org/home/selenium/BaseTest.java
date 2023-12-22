package org.home.selenium;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.home.core.Driver;
import org.home.core.Waiter;
import org.home.core.config.PropertiesManager;
import org.home.entities.Address;
import org.home.entities.TestUser;
import org.home.pages.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        postTestResultToSauceLabs(result);
    }

    @AfterClass
    public void cleanup() throws IOException {
        Driver.getInstance().close();
        Waiter.getInstance().close();
    }

    @Step("Open App")
    protected HeaderComponent openApp() throws IOException {
        Properties props = PropertiesManager.getInstance().getProperties();
        String url = props.getProperty("url");

        WebDriver driver = Driver.getInstance().getDriver();
        driver.get(url);
        return new HeaderComponent(driver);
    }

    @Step("Create User")
    protected TestUser createUser() {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String email = String.format("%s@test.com", RandomStringUtils.randomAlphabetic(10)) ;
        String password = String.format("A$%s",RandomStringUtils.randomAlphanumeric(9, 15));
        return new TestUser(firstName, lastName, email, password);
    }

    @Step("Get Logged User")
    protected TestUser getLoggedInUser() {
        return new TestUser("Daria", "Dibal", "1dariatest@gmail.com", "qaz_WSX1");
    }

    @Step("Create Address")
    protected Address createAddress() {
        String street = RandomStringUtils.randomAlphabetic(10);
        String city = RandomStringUtils.randomAlphabetic(5);
        String state = "15";
        String county = "US";
        String zip = RandomStringUtils.randomNumeric(5);
        String telephone = RandomStringUtils.randomNumeric(7);
        return new Address(street, city, state, county, zip, telephone);
    }

    private void postTestResultToSauceLabs(ITestResult result) throws IOException {
        if (PropertiesManager.getInstance().getProperties().getProperty("platform").equals("cloud")) {
            String status = result.isSuccess() ? "passed" : "failed";
            WebDriver driver = Driver.getInstance().getDriver();
            ((RemoteWebDriver) driver).executeScript("sauce:job-result=" + status);
        }
    }
}
