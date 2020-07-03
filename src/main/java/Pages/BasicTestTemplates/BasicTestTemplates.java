package Pages.BasicTestTemplates;
import Utils.API;
import io.qameta.allure.Step;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.openqa.selenium.WebDriver;

public class BasicTestTemplates {
    private WebDriver driver;
    private Pages.BasicTestTemplates.Utils utils;
    Pages.Header.Utils utilsHeader;
    public API api;

    public  BasicTestTemplates(WebDriver driver){
        this.driver = driver;
        utils = new Utils(driver);
        api = new API(driver);
        utilsHeader = new Pages.Header.Utils(driver);

    }

    /////////////////////////////////////////Тест-кейсы////////////////////////////////////////////
    //собираем все написанные мини-тесты из "BasicTestTemplates.Utils" в логичный тест-сценарий по Корзине
    @Step("Использование основных свойств на странице корзина")
    public void Basket() throws InterruptedException {
        utils.clickToCatalog();
        utils.clickBasket();
        utils.urlBasket();
        utils.verifyLK();
        utils.setTextSearct();
    }

    //собираем все написанные мини-тесты из "BasicTestTemplates.Utils" в логичный тест-сценарий по Главной
    @Step("Использование основных свойств на главной странице")
    public void Main() throws InterruptedException {
        utils.enabledBanner();
        utils.visibleBanner();
        for (int numberCategori1 = 1; numberCategori1 < 18; numberCategori1++){
            //Нажать на N категорию
            utils.clickToCategoriFromMain(numberCategori1);//Определить передаваемую переменную
            //Ожидание отображения элемента
            utils.visibilityOfElementLocated();
            //Нажатие на Лого
            utilsHeader.clickLogo();
        }
    }

    //собираем все написанные мини-тесты из "API"
    @Step("Проверка запросов API")
    public void API() {
        api.testGetStatusCart();
        api.testGetTotalCountCart();
        api.testSearchProduct();
        api.testGetCodeProduct();
    }
}
