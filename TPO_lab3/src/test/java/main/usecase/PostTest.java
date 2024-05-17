package main.usecase;

import main.driver.Demon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PostTest {
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
    public void postTest() {
        for (WebDriver driver : drivers) {
            driver.get("https://www.fl.ru/");
            driver.manage().window().maximize();

            Demon.findElement(driver, By.xpath("//*[@id=\"cookie_accept\"]/div[2]/button")).click();
            Demon.findElement(driver, By.cssSelector(".container > .align-items-center")).click();
            Demon.findElement(driver, By.xpath("//*[@id=\"manage-care-block\"]/div/div[2]/div[1]/div/div/div/div/a")).click();
            Demon.findElement(driver, By.id("ui-input-project-title-input")).click();
            Demon.findElement(driver, By.id("ui-input-project-title-input")).sendKeys("1234");
            Demon.findElement(driver, By.cssSelector(".wizard__wrapper")).click();
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".ui-system__message")));
            }
            Demon.findElement(driver, By.id("ui-input-project-title-input")).click();
            Demon.findElement(driver, By.id("ui-input-project-title-input")).sendKeys("123456");
            Demon.findElement(driver, By.id("qa-project-wizard-step-1-button-next")).click();
            {
                WebElement element = Demon.findElement(driver, By.id("qa-project-wizard-step-1-button-next"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            }
            {
                WebElement element = Demon.findElement(driver, By.tagName("body"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element, 0, 0).perform();
            }
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".text-danger > div")));
            }
            Demon.findElement(driver, By.cssSelector(".ui-textarea")).click();
            Demon.findElement(driver, By.cssSelector(".ui-textarea")).sendKeys("5432");
            Demon.findElement(driver, By.cssSelector(".mt-36 .text-5")).click();
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".ui-system__message")));
            }
            Demon.findElement(driver, By.cssSelector(".ui-textarea")).click();
            Demon.findElement(driver, By.cssSelector(".ui-textarea")).sendKeys("54321");
            Demon.findElement(driver, By.id("qa-project-wizard-step-1-button-next")).click();
            Demon.findElement(driver, By.id("ui-input-project-order-budget-input")).click();
            Demon.findElement(driver, By.id("ui-input-project-order-budget-input")).sendKeys("1200");
            Demon.findElement(driver, By.id("date-picker")).click();
            Demon.findElement(driver, By.cssSelector(".id-2024-05-31 > .vc-day-content")).click();
            Demon.findElement(driver, By.id("qa-project-wizard-step-2-button-next")).click();
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".text-3:nth-child(1)")));
            }
            Demon.findElement(driver, By.id("qa-project-wizard-step-3-button-next")).click();
            {
                WebElement element = Demon.findElement(driver, By.id("qa-project-wizard-step-3-button-next"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            }
            {
                WebElement element = Demon.findElement(driver, By.tagName("body"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element, 0, 0).perform();
            }
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.cssSelector(".mt-36 > div")));
            }
            Demon.findElement(driver, By.cssSelector(".mb-16 .text-6:nth-child(4) > .read-more-trigger")).click();
            Demon.findElement(driver, By.cssSelector(".list-unstyled:nth-child(3) .ui-radio-label")).click();
            Demon.findElement(driver, By.id("qa-project-wizard-step-3-button-next")).click();
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.id("qa-sign-in-button")));
            }
        }

    }
}
