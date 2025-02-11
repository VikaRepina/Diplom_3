import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
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
import site.page.object.LoginPage;
import site.page.object.RegistrationPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private String browser;

    private static final String email = "tedetugr-data@yandex.ru";
    private static final String password = "gsysydh";

    public LoginTest(String browser) {
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
        loginPage = new LoginPage(driver);
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Step("Тест успешного входа по кнопке «Войти в аккаунт» на главной")
    @DisplayName("Test log in through login account button")
    @Description("Этот тест проверяет функциональность успешного входа по кнопке «Войти в аккаунт» на главной")
    public void testLogInThroughLoginAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughLoginAccountButton(wait, email, password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")));
    }

    @Test
    @Step("Тест успешного входа через кнопку «Личный кабинет»")
    @DisplayName("Test log in through personal account")
    @Description("Этот тест проверяет функциональность успешного входа через кнопку «Личный кабинет»")
    public void testLogInThroughPersonalAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughPersonalAccount(wait, email, password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")));
    }

    @Test
    @Step("Тест успешного входа через кнопку в форме регистрации")
    @DisplayName("Test log in through registration form")
    @Description("Этот тест проверяет функциональность успешного входа через кнопку в форме регистрации")
    public void testLogInThroughRegistrationForm() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughRegistrationForm(wait, email, password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")));
    }

    @Test
    @Step("Тест успешного входа через кнопку в форме восстановления пароля")
    @DisplayName("Test log in through password recovery form")
    @Description("Этот тест проверяет функциональность успешного входа через кнопку в форме восстановления пароля")
    public void testLogInThroughPasswordRecoveryForm() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughPasswordRecoveryForm(wait, email, password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")));
    }
}
