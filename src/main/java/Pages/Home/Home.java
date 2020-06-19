package Pages.Home;
import Pages.Header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class Home {
    private WebDriver driver;
    public Header header;
    private Utils utils;
    private Pages.Categori1.Utils utilsCategori;
    private Pages.Header.Utils utilsHeader;

    public Home(WebDriver driver) {
        this.driver = driver;
        header = new Header(driver);
        utils = new Utils(driver);
        utilsCategori = new Pages.Categori1.Utils(driver);
        utilsHeader = new Pages.Header.Utils(driver);
    }

    ///////////////////////////////Категории первого уровня/////////////////////////////////
    @Step("Проверка категорий и отображение товаров в них (17 категорий с главной)")
    public void clicklinkCategori1() throws InterruptedException{
        for (int numberCategori1 = 1; numberCategori1 < 18; numberCategori1++){
            //Нажать на N категорию
            utils.clickToCategoriFromMain(numberCategori1);
            //Проверка отображения в подкатегории первого товара
            utilsCategori.visibilityOfElementLocated();
            //Нажатие на Лого
            utilsHeader.clickLogo();
        }
    }

    ////////////////////////////////////////Баннер///////////////////////////////////////
    @Step("Проверка отображения баннера")
    public void banner() {
        utils.visiblebanner();
    }

    ////////////////////////////////////////Адрес Доставки///////////////////////////////////////
    @Step("Проверка ввода адреса доставки")
    public void inputAddressDelivery() throws InterruptedException {
        utils.clickAddressDelivery();
        utils.setTextAddressDelivery();
        utils.waitVariantAddressDelivery();
        utils.clickVariantAddressDelivery();
        utils.clickSaveAddressDelivery();
        utils.varifyAddress();
        }
    }
