package org.home.core.factories;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalPlatformFactory implements PlatformFactory {
    @Override
    public ChromeDriver createChrome() {
        ChromeBrowser browser = new ChromeBrowser();
        return new ChromeDriver(browser.getOptions());
    }

    @Override
    public FirefoxDriver createFirefox() {
        FirefoxBrowser browser = new FirefoxBrowser();
        return new FirefoxDriver(browser.getOptions());
    }
}
