package main.page;

import main.Demon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends AnyPage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void goToRegistry() {
        Demon.waitUntilPageIsReady(driver);
        WebElement registryLink = Demon.getElementByXpath(driver, By.xpath(".//a[@data-id='qa-head-registration']"));
        registryLink.click();

        Demon.waitUntilPageIsReady(driver);
        WebElement registryButton = Demon.getElementByXpath(driver, By.xpath(".//main/div/div/div/div[@class='mt-36 d-sm-flex justify-content-center']/div[@class='fl-sign-up-choice-block-item mr-sm-36']/button"));
        registryButton.click();
    }

    public void goToSignIn() {
        Demon.waitUntilPageIsReady(driver);
        WebElement signInButton = Demon.getElementByXpath(driver, By.xpath(".//a[@data-id='qa-head-sign-in']"));
        signInButton.click();
    }
}
