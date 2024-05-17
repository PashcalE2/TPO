package main.usecase;

import main.driver.Demon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.util.List;

public class LoginTest {
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
    public void login() {
        for (WebDriver driver : drivers) {
            driver.get("https://www.fl.ru/account/login/");
            Demon.waitUntilPageIsReady(driver);

            driver.manage().window().setSize(new Dimension(1050, 660));
            driver.findElement(By.name("username")).sendKeys("shipulinpavel@mail.ru");
            driver.findElement(By.id("password-field")).sendKeys("gT176)(JGa");
            driver.switchTo().frame(0);
            driver.findElement(By.cssSelector(".recaptcha-checkbox-border")).click();
            driver.switchTo().defaultContent();
            driver.findElement(By.id("submit-button")).click();
            driver.findElement(By.id("code")).click();
            driver.findElement(By.id("code")).sendKeys("123");
            driver.findElement(By.name("add")).click();
            {
                List<WebElement> elements = driver.findElements(By.cssSelector(".invalid-feedback"));
                assert(elements.size() > 0);
            }
            driver.close();
        }
    }
}
