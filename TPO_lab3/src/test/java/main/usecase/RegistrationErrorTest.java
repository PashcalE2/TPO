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
    public void doTest() {
        for (WebDriver driver : drivers) {
            badRegistration(driver);
        }
    }

    private void badRegistration(WebDriver driver) {
        driver.get("https://www.fl.ru/");
        Demon.waitUntilPageIsReady(driver);

        driver.manage().window().setSize(new Dimension(1050, 660));

        // driver.findElement(By.xpath("//ul[2]/li/a")).click();
        Demon.findElement(driver, By.xpath("//ul[2]/li/a")).click();

        // driver.findElement(By.cssSelector(".mr-sm-36 > .btn")).click();
        Demon.findElement(driver, By.cssSelector(".mr-sm-36 > .btn")).click();

        // driver.findElement(By.id("ui-input-user-email")).sendKeys("1234456");
        Demon.findElement(driver, By.id("ui-input-user-email")).sendKeys("1234456");

        // driver.findElement(By.cssSelector(".mt-36")).click();
        Demon.findElement(driver, By.cssSelector(".mt-36")).click();

        // driver.findElement(By.id("user-password")).click();
        Demon.findElement(driver, By.id("user-password")).click();

        // driver.findElement(By.id("user-password")).sendKeys("1237asdf&");
        Demon.findElement(driver, By.id("user-password")).sendKeys("1237asdf&");

        // driver.findElement(By.cssSelector(".col-12:nth-child(1)")).click();
        Demon.findElement(driver, By.cssSelector(".col-12:nth-child(1)")).click();

        // driver.findElement(By.cssSelector(".fl-field-icon > use")).click();
        Demon.findElement(driver, By.cssSelector(".fl-field-icon > use")).click();

        {
            // List<WebElement> elements = driver.findElements(By.cssSelector(".ui-system__message"));
            // assert(elements.size() > 0);

            assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".ui-system__message")));
        }
        {
            // List<WebElement> elements = driver.findElements(By.cssSelector(".invalid-feedback"));
            // assert(elements.size() > 0);

            assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".invalid-feedback")));
        }
        {
            // List<WebElement> elements = driver.findElements(By.cssSelector(".bg-danger > svg"));
            // assert(elements.size() > 0);

            assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".bg-danger > svg")));
        }

        driver.close();
    }
}
