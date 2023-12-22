package org.home.core.factories;

import org.home.core.config.PropertiesManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class CloudPlatformFactory implements PlatformFactory {
    private Properties props = null;

    public CloudPlatformFactory() throws IOException {
        props = PropertiesManager.getInstance().getProperties();
    }

    @Override
    public RemoteWebDriver createChrome() throws MalformedURLException {
        ChromeBrowser browserOptions = new ChromeBrowser();
        ChromeOptions options = browserOptions.getOptions();
        options.setPlatformName("Linux");
        options.setBrowserVersion("latest");
        options.setCapability("sauce:options", sauceOptions);
        return new RemoteWebDriver(new URL(props.getProperty("sauce_labs_hub")), options);
    }

    @Override
    public RemoteWebDriver createFirefox() throws MalformedURLException {
        FirefoxBrowser browserOptions = new FirefoxBrowser();
        FirefoxOptions options = browserOptions.getOptions();
        options.setPlatformName("Windows 11");
        options.setBrowserVersion("latest");
        options.setCapability("sauce:options", sauceOptions);
        return new RemoteWebDriver(new URL(props.getProperty("sauce_labs_hub")), options);
    }
}
