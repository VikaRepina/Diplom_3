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
import site.page.object.*;


import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class HomeTest {
    private WebDriver driver;
    private HomePage homePage;
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
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughLoginAccountButton(email, password);
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
    @Step("Тест успешного перехода по клику на «Личный кабинет»")
    @DisplayName("Test crossing log in account")
    @Description("Этот тест проверяет функциональность успешного перехода по клику на «Личный кабинет»")
    public void testCrossingLogInAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        homePage.crossingLogInAccountButton();


        assertTrue("Переход на страницу аккаунта не успешен", homePage.afterSuccessfulCrossingLogInAccount());

    }

    @Test
    @Step("Тест успешного перехода по клику на «Конструктор»")
    @DisplayName("Test crossing designer")
    @Description("Этот тест проверяет функциональность успешного перехода по клику на «Конструктор»")
    public void testCrossingDesigner() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        homePage.crossingDesignerButton();

        assertTrue("Переход на страницу конструктора не успешен", homePage.afterSuccessfulCrossingDesigner());

    }

    @Test
    @Step("Тест успешного перехода по клику на логотип Stellar Burgers")
    @DisplayName("Test crossing logo")
    @Description("Этот тест проверяет функциональность успешного перехода по клику на логотип Stellar Burgers")
    public void testCrossingLogo() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        homePage.crossingLogoButton();

        assertTrue("Переход по логотипу Stellar Burgers не успешен", homePage.afterSuccessfulCrossingLogo());

    }

    @Test
    @Step("Тест успешного выхода из аккаунта")
    @DisplayName("Test exit from account")
    @Description("Этот тест проверяет функциональность выхода из аккаунта")
    public void testExitFromAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        homePage.crossingExitButton();

        assertTrue("Выход из аккаунта не успешен", homePage.afterSuccessfulExitOutAccount());

    }
}
