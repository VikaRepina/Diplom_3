package site.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private final By logInAccountButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private final By registrationButton = By.xpath("//a[@class='Auth_link__1fOlj' and @href='/register']");
    private final By nameInput = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[1]");
    private final By emailInput = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[2]");
    private final By passwordInput = By.xpath("//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private final By registrationButtonSecond = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By afterSuccessfulRegistration = By.xpath("//div[@class='Auth_login__3hAey']");
    private final By messageAboutError = By.xpath("//p[@class='input__error text_type_main-default']");

    public void successfulRegistrationUser(WebDriverWait wait, String email, String name, String password) {
        WebElement logInAccountButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(logInAccountButton));
        logInAccountButtonLocator.click();
        WebElement registrationButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        registrationButtonLocator.click();

        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(registrationButtonSecond).click();


    }

    public void incorrectPasswordError(WebDriverWait wait, String email, String name, String passwordE) {
        WebElement logInAccountButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(logInAccountButton));
        logInAccountButtonLocator.click();
        WebElement registrationButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        registrationButtonLocator.click();

        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(passwordE);
        driver.findElement(registrationButtonSecond).click();
    }
}
