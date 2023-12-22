package org.home.core;

import org.home.core.config.PropertiesManager;
import org.home.core.factories.GridPlatformFactory;
import org.home.core.factories.LocalPlatformFactory;
import org.home.core.factories.PlatformFactory;
import org.home.core.factories.CloudPlatformFactory;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

public final class Driver {
    private static Driver instance;

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private Driver() {}

    public WebDriver getDriver() throws IOException {
        if (this.driver.get() == null) {
            Properties props = PropertiesManager.getInstance().getProperties();
            PlatformFactory factory = getPlatform(props);
            WebDriver driver = getDriverWithOptions(factory, props);
            this.driver.set(driver);
        }
        return this.driver.get();
    }

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    public void close() throws IOException {
        WebDriver driver = this.driver.get();
        Properties properties = PropertiesManager.getInstance().getProperties();
        if (properties.getProperty("platform").equals("local")) {
            driver.quit();
            this.driver.remove();
        }
    }

    private PlatformFactory getPlatform(Properties props) throws IOException {
        PlatformFactory platform = null;
        switch (props.getProperty("platform")) {
            case "cloud":
                platform = new CloudPlatformFactory();
                break;
            case "grid":
                platform = new GridPlatformFactory();
                break;
            default:
                platform = new LocalPlatformFactory();
        }
        return platform;
    }

    private WebDriver getDriverWithOptions(PlatformFactory platform, Properties props) throws MalformedURLException {
        WebDriver driver = null;
        switch (props.getProperty("browser")) {
            case "firefox":
                driver = platform.createFirefox();
                break;
            default:
                driver = platform.createChrome();
        }
        return driver;
    }
}