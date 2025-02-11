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
import site.page.object.HomePage;
import site.page.object.LoginPage;


import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HomeTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private String browser;

    private static final String email = "tedetugr-data@yandex.ru";
    private static final String password = "gsysydh";

    public HomeTest(String browser) {
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
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        loginPage.logInThroughLoginAccountButton(wait, email, password);
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Step("Тест успешного перехода по клику на «Личный кабинет»")
    @DisplayName("Test crossing log in account")
    @Description("Этот тест проверяет функциональность успешного перехода по клику на «Личный кабинет»")
    public void testCrossingLogInAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        homePage.crossingLogInAccountButton(wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='Profile_profileList__3vTor']")));
    }

    @Test
    @Step("Тест успешного перехода по клику на «Конструктор»")
    @DisplayName("Test crossing designer")
    @Description("Этот тест проверяет функциональность успешного перехода по клику на «Конструктор»")
    public void testCrossingDesigner() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        homePage.crossingDesignerButton(wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='display: flex;']")));
    }

    @Test
    @Step("Тест успешного перехода по клику на логотип Stellar Burgers")
    @DisplayName("Test crossing logo")
    @Description("Этот тест проверяет функциональность успешного перехода по клику на логотип Stellar Burgers")
    public void testCrossingLogo() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        homePage.crossingLogoButton(wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='display: flex;']")));
    }

    @Test
    @Step("Тест успешного выхода из аккаунта")
    @DisplayName("Test exit from account")
    @Description("Этот тест проверяет функциональность выхода из аккаунта")
    public void testExitFromAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        homePage.crossingExitButton(wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Auth_login__3hAey']")));
    }
}
