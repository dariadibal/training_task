package org.home.core.factories;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.Map;

import static java.util.Map.entry;

public interface PlatformFactory {

    Map<String, Object> sauceOptions = Map.ofEntries(
            entry("username", System.getenv("SAUCE_USERNAME")),
            entry("accessKey", System.getenv("SAUCE_KEY")),
            entry("build", "selenium-build-6G7AW")
    );

    WebDriver createChrome() throws MalformedURLException;
    WebDriver createFirefox() throws MalformedURLException;
}
