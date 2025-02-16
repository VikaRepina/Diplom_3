package site.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private final WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 10);
    }

    private final By emailInput = By.xpath("//input[@class='text input__textfield text_type_main-default' and @name='name']");
    private final By passwordInput = By.xpath("//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private final By logButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    private final By logInAccountButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private final By personalAccountButton = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()= 'Личный Кабинет']");
    private final By registrationButton = By.xpath("//a[@class='Auth_link__1fOlj' and @href='/register']");
    private final By loginRegistrationFormButton = By.xpath("//a[@class='Auth_link__1fOlj']");
    private final By passwordRecoveryForm = By.xpath("//a[@class='Auth_link__1fOlj' and @href='/forgot-password']");
    private final By loginPasswordRecoveryForm = By.xpath("//a[@class='Auth_link__1fOlj' and @href='/login']");
    private final By afterSuccessfulLogin = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    @Step("Вход через кнопку «Войти в аккаунт» на главной")
    public void logInThroughLoginAccountButton(String email, String password) {
        WebElement logInAccountButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(logInAccountButton));
        logInAccountButtonLocator.click();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(logButton).click();
    }

    @Step("Подтверждение успешного входа")
    public boolean afterSuccessfulLogin() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulLogin));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Вход через кнопку «Личный кабинет»")
    public void logInThroughPersonalAccount(String email, String password) {
        WebElement personalAccountButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        personalAccountButtonLocator.click();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(logButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulLogin));
    }

    @Step("Вход через кнопку в форме регистрации")
    public void logInThroughRegistrationForm(String email, String password) {
        WebElement logInAccountButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(logInAccountButton));
        logInAccountButtonLocator.click();
        WebElement registrationButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        registrationButtonLocator.click();
        WebElement loginRegistrationFormButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(loginRegistrationFormButton));
        loginRegistrationFormButtonLocator.click();

        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(logButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulLogin));
    }

    @Step("Вход через кнопку в форме восстановления пароля")
    public void logInThroughPasswordRecoveryForm(String email, String password) {
        WebElement personalAccountButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        personalAccountButtonLocator.click();
        WebElement passwordRecoveryFormLocator = wait.until(ExpectedConditions.elementToBeClickable(passwordRecoveryForm));
        passwordRecoveryFormLocator.click();
        WebElement loginPasswordRecoveryFormLocator = wait.until(ExpectedConditions.elementToBeClickable(loginPasswordRecoveryForm));
        loginPasswordRecoveryFormLocator.click();

        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(logButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulLogin));
    }
}
