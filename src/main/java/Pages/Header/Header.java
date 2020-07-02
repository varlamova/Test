package Pages.Header;
import Utils.API;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Header {
    private WebDriver driver;
    private Actions actions;
    private Utils utils;
    public API api;
    public Pages.Categori1.Utils utilsCategori;

    public Header(WebDriver driver){
        this.driver = driver;
        utils = new Utils(driver);
        api = new API(driver);
        utilsCategori = new Pages.Categori1.Utils(driver);

    }

    ///////////////////////////////Корзина/////////////////////////////////
    @Step("Проверка корзины")
    public void Basket() throws InterruptedException {
        utilsCategori.clickToCategoriFromMain();
        utilsCategori.clickToProduct();
        utils.clickBasket();
        utils.urlBasket();
        api.testGetTotalCountCart();
        api.testSearchProduct();
    }

    ///////////////////////////////Адрес доставки/////////////////////////////////
    @Step("Проверка адреса доставки")
    public void DeliveryAddress(){
        utils.clickDeliveryAddress();
        utils.verifyDeliveryAddress();
    }

    ///////////////////////////////ЛК/////////////////////////////////
    @Step("Проверка ЛК")
    public void LK(){
        utils.verifyLK();
        utils.clickLK();
        utils.urlLK();
    }

    ///////////////////////////////Лого/////////////////////////////////
    @Step("Проверка Лого")
    public void Logo(){
        utils.clickLogo();
        utils.urlLogo();
    }

    ///////////////////////////////Поиск/////////////////////////////////
    @Step("Проверка поиска")
    public void Searct() throws InterruptedException {
        utils.setTextSearct();
        utils.clickSearch();
        utils.urlSearch();
        api.testGetStatusCart();
    }

    ///////////////////////////////Каталог/////////////////////////////////
    @Step("Проверка каталога (в 5 первых категорий по 2 первых подкатегории)")
    public void moveCatalog() throws InterruptedException{
        for (int numberCategori1 =1; numberCategori1 <6; numberCategori1++){
            for (int numberCategori2 =1; numberCategori2 <3; numberCategori2++){
                // Навести мышку на кнопку "Каталог"
                utils.clickToCatalog();
                //Навести мышку на Категорию в каталоге
                utils.mouseoverbuttonCategori1(numberCategori1);
                //Кликнуть на  подкатегорию категории
                utils.mouseoverbuttonCategori2(numberCategori2);
                //Проверка отображения в подкатегории первого товара
                utils.visibilityOfElementLocated2();
            }

        }
    }
}
