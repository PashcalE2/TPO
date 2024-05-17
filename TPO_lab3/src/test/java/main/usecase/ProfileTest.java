package main.usecase;

import main.driver.Demon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
public class ProfileTest {
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
    public void profileDataChangeWrongPassword() {
        drivers.forEach(driver -> {
            driver.get("https://www.fl.ru/");
            Demon.waitUntilPageIsReady(driver);

            driver.manage().window().setSize(new Dimension(1050, 660));
            driver.findElement(By.cssSelector("#navbarRightDropdown > svg")).click();
            driver.findElement(By.cssSelector(".dropdown-menu-right > .dropdown-item:nth-child(1)")).click();
            driver.findElement(By.linkText("Настройки")).click();
            driver.findElement(By.name("pname")).click();
            driver.findElement(By.name("pname")).sendKeys("TestTest");
            driver.findElement(By.name("oldpwd")).click();
            driver.findElement(By.name("oldpwd")).sendKeys("12345");
            driver.findElement(By.name("btn")).click();
            {
                List<WebElement> elements = driver.findElements(By.cssSelector(".errorBox"));
                assert(elements.size() > 0);
            }
            driver.close();
        });

    }
}
