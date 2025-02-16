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


public class DesignerTest {
    private WebDriver driver;
    private DesignerPage designerPage;
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
        designerPage = new DesignerPage(driver);
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
    @Step("Тест успешного перехода по клику к разделу «Булки»")
    @DisplayName("Test crossing bun section")
    @Description("Этот тест проверяет функциональность успешного перехода по клику к разделу «Булки»")
    public void testCrossingBunSection() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        designerPage.crossingSaucesSectionButton();
        designerPage.crossingBunSectionButton();


        assertTrue("Переход к разделу не успешен", designerPage.afterSuccessfulCrossingBun());

    }

    @Test
    @Step("Тест успешного перехода по клику к разделу «Соусы»")
    @DisplayName("Test crossing sauces section")
    @Description("Этот тест проверяет функциональность успешного перехода по клику к разделу «Соусы»")
    public void testCrossingSaucesSection() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        designerPage.crossingSaucesSectionButton();

        assertTrue("Переход к разделу не успешен", designerPage.afterSuccessfulCrossingSauces());
    }

    @Test
    @Step("Тест успешного перехода по клику к разделу «Начинки»")
    @DisplayName("Test crossing filling section")
    @Description("Этот тест проверяет функциональность успешного перехода по клику к разделу «Начинки»")
    public void testCrossingFillingSection() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        designerPage.crossingFillingSectionButton();

        assertTrue("Переход к разделу не успешен", designerPage.afterSuccessfulCrossingFilling());
    }
}
