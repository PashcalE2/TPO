package main.usecase;

import main.driver.Demon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FilterTest {
    private List<WebDriver> drivers;
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
    public void filterTest() {
        for (WebDriver driver : drivers) {
            driver.get("https://www.fl.ru/");
            driver.manage().window().maximize();

            Demon.findElement(driver, By.xpath("//*[@id=\"cookie_accept\"]/div[2]/button")).click();
            Demon.findElement(driver, By.linkText("Работа")).click();
            Demon.findElement(driver, By.cssSelector("#vs1__combobox .vs__search")).click();
            Demon.findElement(driver, By.id("vs1__option-2")).click();
            Demon.findElement(driver, By.cssSelector("#vs1__combobox .vs__search")).click();
            Demon.findElement(driver, By.cssSelector("#vs1__combobox .vs__search")).sendKeys("321");
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".vs__no-options")));
            }
        }

    }
}
