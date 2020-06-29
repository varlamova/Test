package Pages.Categori1;
import Utils.Global;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;

public class Utils<actions> {
    private WebDriver driver;
    private Actions actions;
    public Global global;

    public Utils(WebDriver driver) {
        this.driver = driver;
        global = new Global(driver);
    }

    ///////////////////////////////Все объекты Категории/////////////////////////////////
    private By articleProduct1Categori = By.xpath("//div[@id='main']//section[1]//div[1]/article");
    private By buttonclickToCategori = By.xpath("//div[@id='main']//div/section//a[1]");
    private By buttonAddProductToCart = By.xpath("//main/section[1]/div/div/div[1]//button");
    private By buttonProductIsOutOfStoke = By.xpath("//*/text()[normalize-space(.)='Хорошо']/parent::*");


    @Step("Проверка отображения в категории первого товара")
    public void visibilityOfElementLocated() {
        //Ожидание первого элемента в категории
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(articleProduct1Categori));
        //Проверка отображения в категории первого товара
        if (!driver.findElement(articleProduct1Categori).isDisplayed()) {
            fail("В категории нет продуктов");
        }
    }

    //////////////////////////////Положить товар в корзину//////////////////////////////////
    @Step("Кликнуть на 1 категорию с Главной")
    public void clickToCategoriFromMain() {
        driver.findElement(buttonclickToCategori).click();
    }

    @Step("Положить первый товар в корзину в категории")
    public void clickToProduct() throws InterruptedException {
        driver.findElement(buttonAddProductToCart).click();
    }

    @Step("Ожидание попапа с уведомлением о том, что товара нет в наличии")
    public void waitPopupIsOutOfStoke() {
        WebDriverWait wait = new WebDriverWait(driver, 1, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonProductIsOutOfStoke)).click();
    }

    @Step("Перейти на детальную страницу товара")
    public void clickDetailCartProduct(int Product_Number) {
        String clickDelailCart = String.format("//div[@id='main']//div[%d]/article/a[2]", Product_Number);
        By linkDelailCart = By.xpath(clickDelailCart);
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(linkDelailCart));
        actions.perform();
        driver.findElement(linkDelailCart).click();
    }
}
