package main.usecase;

import main.driver.Demon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ReplyTest {
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
    public void replyTest() {
        for (WebDriver driver : drivers) {
            driver.get("https://www.fl.ru/");
            driver.manage().window().maximize();

            Demon.findElement(driver, By.xpath("//*[@id=\"cookie_accept\"]/div[2]/button")).click();
            Demon.findElement(driver, By.linkText("Работа")).click();
            Demon.findElement(driver, By.cssSelector("#vs1__combobox .vs__search")).click();
            Demon.findElement(driver, By.id("vs1__option-2")).click();
            Demon.findElement(driver, By.cssSelector("#vs2__combobox .vs__search")).click();
            Demon.findElement(driver, By.id("vs2__option-2")).click();
            Demon.findElement(driver, By.id("main-template-sup")).click();
            Demon.findElement(driver, By.cssSelector(".\\_primary")).click();
            Demon.findElement(driver, By.id("prj_name_5320451")).click();
            Demon.findElement(driver, By.linkText("Ответить на проект")).click();
            Demon.findElement(driver, By.id("el-ps_text")).click();
            Demon.findElement(driver, By.id("el-ps_text")).sendKeys("1234");
            Demon.findElement(driver, By.cssSelector(".col-sm-6:nth-child(2) > .b-layout_padright_5 > .b-layout__txt_padbot_10")).click();
            Demon.findElement(driver, By.id("el-time_from")).click();
            Demon.findElement(driver, By.id("el-time_from")).sendKeys("15");
            Demon.findElement(driver, By.cssSelector(".b-fon_margbot_20 > .col-sm-6:nth-child(1)")).click();
            Demon.findElement(driver, By.linkText("Добавить")).click();
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.id("el-ps_text-error-text")));
            }
            Demon.findElement(driver, By.id("el-ps_text")).click();
            Demon.findElement(driver, By.id("el-ps_text")).sendKeys("12345");
            Demon.findElement(driver, By.linkText("Добавить")).click();
            {
                assertDoesNotThrow(() -> Demon.findElement(driver, By.xpath("//*[@id=\"el-submit\"]")));
            }
        }
    }
}
