package main.usecase;

import main.Demon;
import main.page.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class RegistryTest {
    @BeforeAll
    public static void prepareDrivers() {
        Demon.setDriversProperties();
    }

    @Test
    public void wrongInput() {
        ArrayList<WebDriver> drivers = new ArrayList<>(Demon.newDrivers());

        drivers.parallelStream().forEach((driver) -> {
            driver.get(Demon.getSiteUrl());
            MainPage mainPage = new MainPage(driver);
            mainPage.goToRegistry();
        });
    }
}
