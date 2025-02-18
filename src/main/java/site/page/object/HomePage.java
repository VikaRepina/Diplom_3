package site.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private final WebDriverWait wait;
    public HomePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 10);
    }

    private final By logInAccountButtonAuthorize  = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()= 'Личный Кабинет']");
    private final By designerButtonAuthorize = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()= 'Конструктор']");
    private final By logoButtonAuthorize = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private final By exitButtonAuthorize = By.xpath("//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']");
    private final By afterSuccessfulCrossingLogInAccount = By.xpath("//ul[@class='Profile_profileList__3vTor']");
    private final By afterSuccessfulCrossingDesignerAndLogo = By.xpath("//div[@style='display: flex;']");
    private final By afterSuccessfulExitOutAccount = By.xpath("//div[@class='Auth_login__3hAey']");


    @Step("Переход по клику на «Личный кабинет»")
    public void crossingLogInAccountButton() {
        WebElement logInAccountButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(logInAccountButtonAuthorize));
        logInAccountButtonAuthorizeLocator.click();
    }

    @Step("Подтверждение успешного перехода в «Личный кабинет»")
    public boolean afterSuccessfulCrossingLogInAccount() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulCrossingLogInAccount));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Перехода по клику на «Конструктор»")
    public void crossingDesignerButton() {
        driver.findElement(logInAccountButtonAuthorize).click();
        WebElement designerButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(designerButtonAuthorize));
        designerButtonAuthorizeLocator.click();

    }

    @Step("Подтверждение успешного перехода в «Конструктор»")
    public boolean afterSuccessfulCrossingDesigner() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulCrossingDesignerAndLogo));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    @Step("Переход по клику на логотип Stellar Burgers")
    public void crossingLogoButton() {
        driver.findElement(logInAccountButtonAuthorize).click();
        WebElement logoButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(logoButtonAuthorize));
        logoButtonAuthorizeLocator.click();
    }

    @Step("Подтверждение успешного перехода логотип Stellar Burgers")
        public boolean afterSuccessfulCrossingLogo() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulCrossingDesignerAndLogo));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Выход из аккаунта")
    public void crossingExitButton() {
        driver.findElement(logInAccountButtonAuthorize).click();
        WebElement exitButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(exitButtonAuthorize));
        exitButtonAuthorizeLocator.click();
    }

    @Step("Подтверждение успешного выхода из аккаунта")
    public boolean afterSuccessfulExitOutAccount() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulExitOutAccount));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
