package org.home.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.util.function.Function;

import static java.time.Duration.ofSeconds;

public class Waiter {

    private static Waiter instance;

    private final ThreadLocal<Wait<WebDriver>> waiter = new ThreadLocal<>();

    private Waiter() {}

    public Wait<WebDriver> getWait() throws IOException {
        if (this.waiter.get() == null) {
            WebDriver driver = Driver.getInstance().getDriver();
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(ofSeconds(60))
                    .pollingEvery(ofSeconds(3))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementNotInteractableException.class);
            this.waiter.set(wait);
        }
        return this.waiter.get();
    }

    public Boolean waitForCondition(Function<? super WebDriver, Boolean> function) throws IOException {
        return this.getWait().until(function);
    }

    public static Waiter getInstance() {
        if (instance == null) {
            instance = new Waiter();
        }
        return instance;
    }

    public void close() {
        this.waiter.remove();
    }
}
