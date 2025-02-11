package site.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By logInAccountButtonAuthorize  = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()= 'Личный Кабинет']");
    private final By designerButtonAuthorize = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()= 'Конструктор']");
    private final By logoButtonAuthorize = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private final By exitButtonAuthorize = By.xpath("//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']");
    private final By afterSuccessfulCrossingLogInAccount = By.xpath("//ul[@class='Profile_profileList__3vTor']");
    private final By afterSuccessfulCrossingDesignerAndLogo = By.xpath("//div[@style='display: flex;']");
    private final By afterSuccessfulExitOutAccount = By.xpath("//div[@class='Auth_login__3hAey']");


    public void crossingLogInAccountButton(WebDriverWait wait) {
        WebElement logInAccountButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(logInAccountButtonAuthorize));
        logInAccountButtonAuthorizeLocator.click();
    }

    public void crossingDesignerButton(WebDriverWait wait) {
        driver.findElement(logInAccountButtonAuthorize).click();
        WebElement designerButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(designerButtonAuthorize));
        designerButtonAuthorizeLocator.click();
    }

    public void crossingLogoButton(WebDriverWait wait) {
        driver.findElement(logInAccountButtonAuthorize).click();
        WebElement logoButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(logoButtonAuthorize));
        logoButtonAuthorizeLocator.click();
    }

    public void crossingExitButton(WebDriverWait wait) {
        driver.findElement(logInAccountButtonAuthorize).click();
        WebElement exitButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(exitButtonAuthorize));
        exitButtonAuthorizeLocator.click();
    }
}
