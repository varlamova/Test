package Pages.Categori1;
import Utils.API;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;

public class Categori1 {
    private WebDriver driver;
    private Actions actions;
    private Pages.Header.Utils utilsHeader;
    private Pages.Categori1.Utils utils;
    private API api;
    public Integer Product_Number = 1;

    public Categori1(WebDriver driver){
        this.driver = driver;
        utils = new Pages.Categori1.Utils(driver);
        api = new API(driver);
    }

    ///////////////////////////////////Категории/////////////////////////////////////////
    @Step("Положить товар в корзину в категории")
    public void clickProduct() throws InterruptedException {
        utils.clickToCategoriFromMain();
        Boolean global_break = false;
        for (Product_Number = 1; Product_Number < 11; Product_Number++) {
            if (global_break) { break; }
            utils.clickToProduct();
            try { utils.waitPopupIsOutOfStoke(); }
            catch (WebDriverException e){
                utils.clickDetailCartProduct(Product_Number);
                api.testGetCodeProduct();
                global_break = true;
            }
        }
    }

}
