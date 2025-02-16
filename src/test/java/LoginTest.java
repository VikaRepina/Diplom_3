import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
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
import site.page.object.LoginApi;
import site.page.object.LoginPage;
import site.page.object.RegistrationPage;
import site.page.object.User;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;


public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private static String browser;

    private static final String email = "tedetugre-data@yandex.ru";
    private static final String password = "gsysreydh";
    private static final String name = "useeeeeeerrr";
    private String Token;


    @Before
    @Step("Настройка драйвера")
    public void setup() {

        browser = System.getenv("BROWSER");
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        User user = new User(name, email, password);
        LoginApi loginApi = new LoginApi();
        Response response = loginApi.createUser(user);response.then().statusCode(200);
        Token = response.jsonPath().get("accessToken");

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "yandex":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:/Users/Виктория/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
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

        LoginApi loginApi = new LoginApi();
        Response responseSecond = loginApi.deleteUser(Token);
        responseSecond.then().statusCode(202);
    }

    @Test
    @Step("Тест успешного входа по кнопке «Войти в аккаунт» на главной")
    @DisplayName("Test log in through login account button")
    @Description("Этот тест проверяет функциональность успешного входа по кнопке «Войти в аккаунт» на главной")
    public void testLogInThroughLoginAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughLoginAccountButton(email, password);

        assertTrue("Не успешеный вход в аккаунта", loginPage.afterSuccessfulLogin());

    }

    @Test
    @Step("Тест успешного входа через кнопку «Личный кабинет»")
    @DisplayName("Test log in through personal account")
    @Description("Этот тест проверяет функциональность успешного входа через кнопку «Личный кабинет»")
    public void testLogInThroughPersonalAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughPersonalAccount(email, password);

        assertTrue("Не успешеный вход в аккаунта", loginPage.afterSuccessfulLogin());
    }

    @Test
    @Step("Тест успешного входа через кнопку в форме регистрации")
    @DisplayName("Test log in through registration form")
    @Description("Этот тест проверяет функциональность успешного входа через кнопку в форме регистрации")
    public void testLogInThroughRegistrationForm() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughRegistrationForm(email, password);

        assertTrue("Не успешеный вход в аккаунта", loginPage.afterSuccessfulLogin());
    }

    @Test
    @Step("Тест успешного входа через кнопку в форме восстановления пароля")
    @DisplayName("Test log in through password recovery form")
    @Description("Этот тест проверяет функциональность успешного входа через кнопку в форме восстановления пароля")
    public void testLogInThroughPasswordRecoveryForm() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughPasswordRecoveryForm(email, password);

        assertTrue("Не успешеный вход в аккаунта", loginPage.afterSuccessfulLogin());
    }
}
