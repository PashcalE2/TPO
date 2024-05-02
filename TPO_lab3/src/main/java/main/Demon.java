package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class Demon {
    private static final String site_url = "https://www.fl.ru";
    private static final String chrome_driver_property = "webdriver.chrome.driver";
    private static final String chrome_driver_path = "drivers/chromedriver.exe";
    private static final String firefox_driver_property = "webdriver.gecko.driver";
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
        ArrayList<WebDriver> drivers = new ArrayList<WebDriver>(2);

        drivers.add(newChromeDriver());
        drivers.add(newFirefoxDriver());

        return drivers;
    }

    public static String getSiteUrl() {
        return site_url;
    }
}
