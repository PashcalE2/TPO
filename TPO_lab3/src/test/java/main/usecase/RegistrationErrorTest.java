package main.usecase;

import main.driver.Demon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationErrorTest {
    private List<WebDriver> drivers;

    @BeforeAll
    public static void prepareDrivers() {
        Demon.setDriversProperties();
    }

    @BeforeEach
    public void setUp() {
        drivers = Demon.newDrivers();
    }

    @AfterEach
    public void tearDown() {
        for (WebDriver driver : drivers) {
            driver.quit();
        }
    }

    @Test
    public void badRegistration() {
        for (WebDriver driver : drivers) {
            driver.get("https://www.fl.ru/");
            Demon.waitUntilPageIsReady(driver);

            driver.manage().window().maximize();

            Demon.findElement(driver, By.xpath("//*[@id=\"cookie_accept\"]/div[2]/button")).click();
            Demon.findElement(driver, By.xpath("//ul[2]/li/a")).click();
            Demon.findElement(driver, By.cssSelector(".mr-sm-36 > .btn")).click();
            Demon.findElement(driver, By.id("ui-input-user-email")).sendKeys("1234456");
            Demon.findElement(driver, By.cssSelector(".mt-36")).click();
            Demon.findElement(driver, By.id("user-password")).click();
            Demon.findElement(driver, By.id("user-password")).sendKeys("1237asdf&");
            Demon.findElement(driver, By.cssSelector(".col-12:nth-child(1)")).click();

            Demon.findElement(driver, By.cssSelector(".fl-field-icon > use")).click();

            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".ui-system__message")));
            }
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".bg-danger > svg")));
            }
        }
    }
}
