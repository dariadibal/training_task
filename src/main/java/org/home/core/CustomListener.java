package org.home.core;

import io.qameta.allure.Allure;
import org.home.core.config.PropertiesManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

import static org.home.core.Screenshot.*;

public class CustomListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Properties props = null;
        try {
            props = PropertiesManager.getInstance().getProperties();
            takeScreenshot(result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            Allure.addAttachment("Browser", props.getProperty("browser"));
            Allure.addAttachment("Platform", props.getProperty("platform"));
            Allure.addAttachment("Date", LocalDate.now().toString());
            Allure.addAttachment("Time", LocalTime.now().toString());
        }
    }
}
