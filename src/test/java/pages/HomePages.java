package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private final By firstProductLink = By.cssSelector(".card-title a"); // Выбираем первый продукт
    private final By cartLink = By.id("cartur");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFirstProduct() {
        driver.findElement(firstProductLink).click();
    }

    public void goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Явное ожидание
        wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        driver.findElement(cartLink).click();
    }
}