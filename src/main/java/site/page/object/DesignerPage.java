package site.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DesignerPage extends BasePage {
    public DesignerPage(WebDriver driver) {
        super(driver);
    }

    private final By designerButtonAuthorize = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()= 'Конструктор']");
    private final By bunSectionButtonAuthorize = By.xpath("//span[@class='text text_type_main-default' and text()= 'Булки']");
    private final By saucesSectionButtonAuthorize = By.xpath("//span[@class='text text_type_main-default' and text()= 'Соусы']");
    private final By fillingSectionButtonAuthorize = By.xpath("//span[@class='text text_type_main-default' and text()= 'Начинки']");
    private final By afterSuccessfulCrossingSection = By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']");


    public void crossingBunSectionButton(WebDriverWait wait) {
        WebElement designerButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(designerButtonAuthorize));
        designerButtonAuthorizeLocator.click();
        driver.findElement(bunSectionButtonAuthorize).click();
    }

    public void crossingSaucesSectionButton(WebDriverWait wait) {
        WebElement designerButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(designerButtonAuthorize));
        designerButtonAuthorizeLocator.click();
        driver.findElement(saucesSectionButtonAuthorize).click();
    }

    public void crossingFillingSectionButton(WebDriverWait wait) {
        WebElement designerButtonAuthorizeLocator = wait.until(ExpectedConditions.elementToBeClickable(designerButtonAuthorize));
        designerButtonAuthorizeLocator.click();
        driver.findElement(fillingSectionButtonAuthorize).click();
    }
}
