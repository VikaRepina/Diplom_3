import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.page.object.RegistrationPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegistrationUserTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private String browser;

    private static final String email = "tedetwagj-data@yandex.ru";
    private static final String name = "UserTeeeest";
    private static final String password = "gshpfjs";
    private static final String passwordE = "user";

    public RegistrationUserTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "chrome" },
                { "yandex" }
        });
    }


    @Before
    @Step("Настройка драйвера")
    public void setup() {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("yandex")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:/Users/Виктория/AppData/Local/Yandex/YandexBrowser/Application/browser.exe"); // Укажите правильный путь к Yandex Browser
            driver = new ChromeDriver(options);
        }
        driver.get("https://stellarburgers.nomoreparties.site/");
        registrationPage = new RegistrationPage(driver);
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Step("Тест успешной регистрации пользователя")
    @DisplayName("Test successful registration")
    @Description("Этот тест проверяет функциональность успешной регистрации")
    public void testSuccessfulRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        registrationPage.successfulRegistrationUser(wait,email, name, password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Auth_login__3hAey']")));
    }

    @Test
    @Step("Тест регистрации неправильным паролем")
    @DisplayName("Test incorrect password registration")
    @Description("Этот тест проверяет регистрацию неправильным паролем")
    public void testIncorrectPasswordRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        registrationPage.incorrectPasswordError(wait, email, name, passwordE);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='input__error text_type_main-default']")));
    }

}
