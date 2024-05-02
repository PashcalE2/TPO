package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    @BeforeAll
    static void prepareDrivers() {
        Demon.setDriversProperties();
    }

    @Test
    void testDriver() {
        assertAll(
                () -> checkTitle(Demon.newChromeDriver()),
                () -> checkTitle(Demon.newFirefoxDriver())
        );
    }

    private void checkTitle(WebDriver driver) {
        driver.get(Demon.getSiteUrl());
        String title = driver.getTitle();
        assertEquals("Крупнейшая биржа фриланса с лучшими профессионалами рунета. Фриланс и удаленная работа - FL.ru", title);
        driver.quit();
    }
}