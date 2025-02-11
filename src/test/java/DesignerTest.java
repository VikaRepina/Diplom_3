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
import site.page.object.DesignerPage;
import site.page.object.HomePage;
import site.page.object.LoginPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DesignerTest {
    private WebDriver driver;
    private DesignerPage designerPage;
    private LoginPage loginPage;
    private String browser;

    private static final String email = "tedetugr-data@yandex.ru";
    private static final String password = "gsysydh";

    public DesignerTest(String browser) {
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
        designerPage = new DesignerPage(driver);
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
    @Step("Тест успешного перехода по клику к разделу «Булки»")
    @DisplayName("Test crossing bun section")
    @Description("Этот тест проверяет функциональность успешного перехода по клику к разделу «Булки»")
    public void testCrossingBunSection() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        designerPage.crossingSaucesSectionButton(wait);
        designerPage.crossingBunSectionButton(wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']")));
    }

    @Test
    @Step("Тест успешного перехода по клику к разделу «Соусы»")
    @DisplayName("Test crossing sauces section")
    @Description("Этот тест проверяет функциональность успешного перехода по клику к разделу «Соусы»")
    public void testCrossingSaucesSection() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        designerPage.crossingSaucesSectionButton(wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']")));
    }

    @Test
    @Step("Тест успешного перехода по клику к разделу «Начинки»")
    @DisplayName("Test crossing filling section")
    @Description("Этот тест проверяет функциональность успешного перехода по клику к разделу «Начинки»")
    public void testCrossingFillingSection() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        designerPage.crossingFillingSectionButton(wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']")));
    }
}
