package org.home.core.factories;

import org.home.core.config.PropertiesManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class GridPlatformFactory implements PlatformFactory {
    private Properties props = null;

    public GridPlatformFactory() throws IOException {
        props = PropertiesManager.getInstance().getProperties();
    }

    @Override
    public WebDriver createChrome() throws MalformedURLException {
        ChromeBrowser browserOptions = new ChromeBrowser();
        return new RemoteWebDriver(new URL(props.getProperty("selenium_grid_hub")),
                browserOptions.getOptions());
    }

    @Override
    public WebDriver createFirefox() throws MalformedURLException {
        FirefoxBrowser browserOptions = new FirefoxBrowser();
        return new RemoteWebDriver(new URL(props.getProperty("selenium_grid_hub")),
                browserOptions.getOptions());
    }
}
