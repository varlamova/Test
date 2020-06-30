package Pages.Header;
import Utils.Global;
import io.qameta.allure.*;
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
    public static String searctName = "Молоко";

    public Utils(WebDriver driver) {
        this.driver = driver;
        global = new Global(driver);

    }


    ///////////////////////////////Все объекты Хедера/////////////////////////////////
    private By buttonBasket = By.xpath("//header//div[5]/a");
    private By buttonDeliveryAddress = By.xpath("//div[@id='root']//section[1]//button");
    private By linkLK = By.xpath("//header//div[4]/a");
    private By spanNameLK = By.xpath("//header//div[4]//span");
    private By linkLogo = By.xpath("//header//div[2]/a");
    private By buttonSearch = By.xpath("//button[@type='submit' and text() = \"Найти\"]");
    private By inputSearch = By.xpath("//input[@id='search']");
    private By buttonCatalog = By.xpath("//button[@type='button' and text() = \"Каталог\"]");
    private By articleProduct1Categori2 = By.xpath("//div[@id='main']//div[1]/article");


    ///////////////////////////////Корзина/////////////////////////////////
    @Step("Нажатие на иконку 'Корзина'")
    public void clickBasket(){
        driver.findElement(buttonBasket).click();
    }

    @Step("Проверка url корзины")
    public void urlBasket() {
        String urlbasket = driver.getCurrentUrl();
        if (!urlbasket.equals(Global.url + "cart/")) {
            fail("Это не страница корзины");
        }
    }

    ///////////////////////////////Адрес доставки/////////////////////////////////
    @Step("Нажать на ссылку 'Адрес доставки'")
    public void clickDeliveryAddress(){
        driver.findElement(buttonDeliveryAddress).click();
    }

    @Step("Проверка названия адреса доставки")
    public void verifyDeliveryAddress(){
        String addressText = driver.findElement(buttonDeliveryAddress).getText();
        if (!addressText.equals("Проверить адрес доставки")){
            System.out.println(addressText.equals("Проверить адрес доставки"));
            fail("Адрес доставки не пустой");
        }
    }

    ///////////////////////////////ЛК/////////////////////////////////
    @Step("Проверка текста у 'ЛК'")
    public void verifyLK(){
        String LKText = driver.findElement(spanNameLK).getText();
        if (!LKText.equals("Войти")){
            fail("Пользователь авторизован");
        }
    }

    @Step("Нажатие на иконку ЛК")
    public void clickLK(){
        driver.findElement(linkLK).click();
    }

    @Step("Проверка url ЛК")
    public void urlLK() {
        String urlLK = driver.getCurrentUrl();
        if (!urlLK.equals(Global.url + "aut")) {
            fail("Это не страница ЛК");
        }
    }

    ///////////////////////////////Лого/////////////////////////////////
    @Step("Нажатие на Лого")
    public void clickLogo(){
        driver.findElement(linkLogo).click();
    }

    @Step("Проверка url Главной страницы")
    public void urlLogo() {
        String urlLogo = driver.getCurrentUrl();
        if (!urlLogo.equals(Global.url)) {
            fail("Это не главная страница");
        }
    }

    ///////////////////////////////Поиск/////////////////////////////////
    @Step("Вставить в поле поиска слово 'Молоко'")
    public void setTextSearct() throws InterruptedException {
        driver.findElement(inputSearch).click();
        Thread.sleep(1000);
        driver.findElement(inputSearch).clear();
        driver.findElement(inputSearch).sendKeys(searctName);

    }

    @Step("Нажать на кнопку 'Поиск'")
    public void clickSearch() {
        driver.findElement(buttonSearch).click();
    }

    @Step("Проверка url Поиска")
    public void urlSearch() {
        String urlSearch = driver.getCurrentUrl();
        if ((urlSearch.equals(Global.url + "search?query=%D0%9C%D0%BE%D0%BB%D0%BE%D0%BA%D0%BE")) || (urlSearch.equals(Global.url + "search?query=Молоко"))) {

        }
        else { fail("Это не страница поиска"); }
    }

    ///////////////////////////Все шаги в функции "Проверка каталога"/////////////////
    @Step("Навести мышку на кнопку \"Каталог\"")
    public void clickToCatalog() {
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(buttonCatalog));
        actions.perform();
    }

    @Step("Навести мышку на N Категорию")
    public void mouseoverbuttonCategori1(int numberCategori1) {
        String outputCategori1 = String.format("//header//ul/li[%d]/button", numberCategori1);
        By buttonCategori1 = By.xpath(outputCategori1);
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(buttonCategori1));
        actions.perform();
    }

    @Step("Нажать на N Подкатегорию")
    public void mouseoverbuttonCategori2(int numberCategori2) {
        String outputCategori2 = String.format("//header//ul[2]/li[%d]/a", numberCategori2);
        By buttonCategori2 = By.xpath(outputCategori2);
        driver.findElement(buttonCategori2).click();
    }

    @Step("Проверка отображения в подкатегории первого товара")
    public void visibilityOfElementLocated2() {
        //Ожидание первого элемента в категории
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(articleProduct1Categori2));
        //Проверка отображения в категории первого товара
        if(!driver.findElement(articleProduct1Categori2).isDisplayed()){
            fail("В подкатегории нет продуктов");
        }
    }


}
