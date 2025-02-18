package site.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;

public class RegistrationPage extends BasePage {
    private final WebDriverWait wait;
    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 10);
    }

    private final By logInAccountButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private final By registrationButton = By.xpath("//a[@class='Auth_link__1fOlj' and @href='/register']");
    private final By nameInput = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[1]");
    private final By emailInput = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[2]");
    private final By passwordInput = By.xpath("//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private final By registrationButtonSecond = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By afterSuccessfulRegistration = By.xpath("//div[@class='Auth_login__3hAey']");
    private final By messageAboutError = By.xpath("//p[@class='input__error text_type_main-default']");

    @Step("Регистрация пользователя")
    public void successfulRegistrationUser(String email, String name, String password) {
        WebElement logInAccountButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(logInAccountButton));
        logInAccountButtonLocator.click();
        WebElement registrationButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        registrationButtonLocator.click();

        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(registrationButtonSecond).click();

    }

    @Step("Подтверждение регистрация пользователя")
    public boolean afterSuccessfulRegistration() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulRegistration));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Ошибка при регистрация пользователя с неверным логином")
    public boolean messageAboutError() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(messageAboutError));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
