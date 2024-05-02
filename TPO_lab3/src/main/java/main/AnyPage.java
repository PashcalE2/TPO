package main;

import org.openqa.selenium.WebDriver;

public abstract class AnyPage {
    protected WebDriver driver;

    public AnyPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
