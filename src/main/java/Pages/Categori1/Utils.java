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

    @Step("Проверка отображения в категории первого товара")
    public void visibilityOfElementLocated() {
        //Ожидание первого элемента в категории
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(articleProduct1Categori));
        //Проверка отображения в категории первого товара
        if(!driver.findElement(articleProduct1Categori).isDisplayed()){
            fail("В категории нет продуктов");
        }
    }
}
