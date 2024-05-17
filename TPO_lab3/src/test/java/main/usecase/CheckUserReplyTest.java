package main.usecase;

import main.driver.Demon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
public class CheckUserReplyTest {
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
    public void checkForBuyReplies() {
        drivers.forEach(driver -> {
            driver.get("https://www.fl.ru/");
            Demon.waitUntilPageIsReady(driver);

            driver.manage().window().setSize(new Dimension(1050, 660));
            driver.findElement(By.linkText("Работа")).click();
            {
                WebElement element = driver.findElement(By.id("ui-input-budgetFrom"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).clickAndHold().perform();
            }
            {
                WebElement element = driver.findElement(By.id("ui-input-budgetFrom"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            }
            {
                WebElement element = driver.findElement(By.id("ui-input-budgetFrom"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).release().perform();
            }
            driver.findElement(By.cssSelector(".d-flex:nth-child(8)")).click();
            driver.findElement(By.id("ui-input-budgetTo")).sendKeys("5000");
            driver.findElement(By.cssSelector(".text-6:nth-child(7)")).click();
            driver.findElement(By.cssSelector("#vs1__combobox > .vs__actions")).click();
            driver.findElement(By.id("vs1__option-2")).click();
            driver.findElement(By.cssSelector(".ui-button:nth-child(17)")).click();
            driver.findElement(By.id("prj_name_5320222")).click();
            driver.findElement(By.linkText("Ответить на проект")).click();
            {
                List<WebElement> elements = driver.findElements(By.linkText("Купить отклики"));
                assert(elements.size() > 0);
            }
            driver.close();
        });
    }
}
