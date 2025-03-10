import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.time.Duration;

public class OrderTests {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private OrderPage orderPage;

    private static final String HOME_PAGE_URL = "https://www.demoblaze.com/#";

    @BeforeEach
    public void setup() {
        // Настройка ChromeDriver (укажите путь к вашему chromedriver)
        System.setProperty("webdriver.chrome.driver", "путь/к/chromedriver"); // Замените на реальный путь
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Успешное оформление заказа")
    public void testSuccessfulOrderPlacement() {
        driver.get(HOME_PAGE_URL);

        // 1. Добавить товар в корзину
        homePage.selectFirstProduct(); // Выбираем первый продукт на главной странице
        productPage.addToCart();
        productPage.acceptAlert(); // Подтверждаем добавление товара

        // 2. Перейти в корзину
        homePage.goToCart();

        // 3. Нажать на кнопку "Place Order"
        cartPage.placeOrder();

        // 4. Заполнить форму заказа
        orderPage.fillOrderForm("Test Name", "Test Country", "Test City", "1234567890123456", "Test Year", "Test Month");

        // 5. Нажать на кнопку "Purchase"
        orderPage.purchase();

        // 6. Проверить, что заказ успешно оформлен
        Assertions.assertTrue(orderPage.isSuccessMessagePresent(), "Сообщение об успешном оформлении заказа не отображается.");

        // Дополнительно: проверить текст сообщения об успехе (необязательно)
        String successMessage = orderPage.getSuccessMessageText();
        Assertions.assertTrue(successMessage.contains("Thank you for your purchase!"), "Текст сообщения об успехе не соответствует ожидаемому.");
    }
}