import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
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
import site.page.object.RegistrationPage;
import site.page.object.User;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;


public class RegistrationUserTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private static String browser;

    private static final String email = "tedetugre-data@yandex.ru";
    private static final String password = "gsysreydh";
    private static final String name = "useeeeeeerrr";
    private static final String passwordE = "user";
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
        registrationPage = new RegistrationPage(driver);
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
    @Step("Тест успешной регистрации пользователя")
    @DisplayName("Test successful registration")
    @Description("Этот тест проверяет функциональность успешной регистрации")
    public void testSuccessfulRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        registrationPage.successfulRegistrationUser(email, name, password);

        assertTrue("Не успешная регистрация", registrationPage.afterSuccessfulRegistration());
    }

    @Test
    @Step("Тест регистрации неправильным паролем")
    @DisplayName("Test incorrect password registration")
    @Description("Этот тест проверяет регистрацию неправильным паролем")
    public void testIncorrectPasswordRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        registrationPage.successfulRegistrationUser(email, name, passwordE);

        assertTrue("Не возникает ошибка при регистрация пользователя с неверным логином", registrationPage.messageAboutError());
    }

}
