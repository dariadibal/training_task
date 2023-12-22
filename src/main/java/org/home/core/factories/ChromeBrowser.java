package org.home.core.factories;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser implements Browser{
    @Override
    public ChromeOptions getOptions() {
        return new ChromeOptions();
    }
}
