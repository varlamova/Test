package Pages.Header;
import Utils.API;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Header {
    private WebDriver driver;
    private Actions actions;
    private Utils utils;
    public API api;

    public Header(WebDriver driver){
        this.driver = driver;
        utils = new Utils(driver);
        api = new API(driver);
    }

    ///////////////////////////////Все объекты Хедера/////////////////////////////////
    private By buttonBasket = By.xpath("//header//div[5]/a");
    private By buttonDeliveryAddress = By.xpath("//div[@id='root']//section[1]//button");
    private By linkLK = By.xpath("//header//div[4]/a");
    private By spanNameLK = By.xpath("//header//div[4]//span");
    private By linkLogo = By.xpath("//header//div[2]/a");
    private By buttonSearch = By.xpath("//button[@type='submit' and text() = \"Найти\"]");
    private By inputSearch = By.xpath("//input[@id='search']");

    ///////////////////////////////Корзина/////////////////////////////////
    @Step("Проверка корзины")
    public void Basket() {
        utils.clickBasket();
        utils.urlBasket();
        api.testGetSingleUserProgrammatic();
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
