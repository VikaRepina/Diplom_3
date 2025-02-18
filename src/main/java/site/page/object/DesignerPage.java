package site.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DesignerPage extends BasePage {
    private final WebDriverWait wait;
    public DesignerPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, 10);
    }

    private final By designerButtonAuthorize = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()= 'Конструктор']");
    private final By bunSectionButtonAuthorize = By.xpath("//span[@class='text text_type_main-default' and text()= 'Булки']");
    private final By saucesSectionButtonAuthorize = By.xpath("//span[@class='text text_type_main-default' and text()= 'Соусы']");
    private final By fillingSectionButtonAuthorize = By.xpath("//span[@class='text text_type_main-default' and text()= 'Начинки']");
    private final By afterSuccessfulCrossingBunSection = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and text()= 'Булки']");
    private final By afterSuccessfulCrossingSaucesSection = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and text()= 'Соусы']");
    private final By afterSuccessfulCrossingFillingSection = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and text()= 'Начинки']");

    @Step("Переход по клику к разделу «Булки»")
    public void crossingBunSectionButton() {
        WebElement designerButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(designerButtonAuthorize));
        designerButtonAuthorizeLocator.click();
        driver.findElement(bunSectionButtonAuthorize).click();

    }

    @Step("Подтверждение успешного перехода к разделу «Булки»")
    public boolean afterSuccessfulCrossingBun() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulCrossingBunSection));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Переход по клику к разделу «Соусы»")
    public void crossingSaucesSectionButton() {
        WebElement designerButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(designerButtonAuthorize));
        designerButtonAuthorizeLocator.click();
        driver.findElement(saucesSectionButtonAuthorize).click();
    }

    @Step("Подтверждение успешного перехода к разделу «Соусы»")
    public boolean afterSuccessfulCrossingSauces() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulCrossingBunSection));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Переход по клику к разделу «Начинки»")
    public void crossingFillingSectionButton() {
        WebElement designerButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(designerButtonAuthorize));
        designerButtonAuthorizeLocator.click();
        driver.findElement(fillingSectionButtonAuthorize).click();
    }

    @Step("Подтверждение успешного перехода к разделу «Начинки»")
    public boolean afterSuccessfulCrossingFilling() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(afterSuccessfulCrossingBunSection));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
