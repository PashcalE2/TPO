package main.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Demon {
    private static final String site_url = "https://www.fl.ru";
    private static final String chrome_driver_property = "webdriver.chrome.driver";
    private static final String chrome_driver_path = "drivers/chromedriver.exe";
    private static final String firefox_driver_property = "webdriver.firefox.bin";
    private static final String firefox_driver_path = "drivers/geckodriver.exe";

    public static void setDriversProperties() {
        System.setProperty(chrome_driver_property, chrome_driver_path);
        System.setProperty(firefox_driver_property, firefox_driver_path);
    }

    public static ChromeDriver newChromeDriver() {
        return new ChromeDriver();
    }

    public static FirefoxDriver newFirefoxDriver() {
        return new FirefoxDriver();
    }

    public static List<WebDriver> newDrivers() {
        ArrayList<WebDriver> drivers = new ArrayList<>(2);

        drivers.add(newChromeDriver());
        drivers.add(newFirefoxDriver());

        return drivers;
    }

    public static String getSiteUrl() {
        return site_url;
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitUntilPageIsReady(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
