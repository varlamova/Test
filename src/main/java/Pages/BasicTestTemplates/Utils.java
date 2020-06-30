package Pages.BasicTestTemplates;
import Utils.Global;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;


public class Utils {
    private WebDriver driver;
    private Actions actions;
    public Global global;
    public static String searctName = "Молоко";

    public Utils(WebDriver driver) {
        this.driver = driver;
        global = new Global(driver);
    }

    //////////////////////////////////////Все объекты на примере Ашана///////////////////////////////////////
    //Объекты Хедера
    private By buttonBasket = By.xpath("//header//a[@href = '/cart/']");
    private By buttonCatalog = By.xpath("//button[@type='button' and text() = \"Каталог\"]");
    private By spanNameLK = By.xpath("//header//div[4]//span");
    private By inputSearch = By.xpath("//input[@id='search']");
    //Объекты Главной
    private By imgBanner = By.xpath("//img[@class='picture__img']");
    //Объекты Категории
    private By articleProduct1Categori = By.xpath("//div[@id='main']//section[1]//div[1]/article");

    //////////////////////////////////////////////Все тесты//////////////////////////////////////////////////

    //////////////////////////////////////////////Корзина//////////////////////////////////////////////////
    //Сделать клик по объекту
    @Step("Нажатие на иконку 'Корзина'")//Название шага в allure
    public void clickBasket(){
        driver.findElement(buttonBasket).click();//кликнуть на объект
    }

    //Навести курсор мыши на объект
    @Step("Навести мышку на кнопку \"Каталог\"")
    public void clickToCatalog() {
        actions = new Actions(driver);//нужно для использования события "moveToElement"
        actions.moveToElement(driver.findElement(buttonCatalog));//навести курсор на объект
        actions.perform();//нужно для использования события "moveToElement"
    }

    //Проверка url страницы
    // ('equals' - проверка на совпадение точного адреса;
    //  'contains' - проверка на совпадение хотя бы по корректному началу url;
    @Step("Проверка url корзины")
    public void urlBasket() throws InterruptedException{
        String urlbasket = driver.getCurrentUrl();//записать url страницы в переменную "urlbasket"
        if (!urlbasket.equals(Global.url + "cart/")) {
            fail("Это не страница корзины");//вывод ошибки в allure
        }
        Thread.sleep(1000);//пауза на 1 секунду (используется только с "throws InterruptedException")
    }

    //Проверка текста у объектов
    @Step("Проверка текста у 'ЛК'")
    public void verifyLK(){
        String LKText = driver.findElement(spanNameLK).getText();//получить текст объекта
        if (!LKText.equals("Войти")){
            fail("Пользователь авторизован");//вывод ошибки в allure
        }
    }

    //Вставить текст в поле объекта
    @Step("Вставить в поле поиска слово 'Молоко'")
    public void setTextSearct() throws InterruptedException {
        driver.findElement(inputSearch).click();//кликнуть на элемент
        Thread.sleep(1000);//пауза на 1 секунду (используется только с "throws InterruptedException")
        driver.findElement(inputSearch).clear();//очистка всего поля
        driver.findElement(inputSearch).sendKeys(searctName);//вставить текст в поле
    }

    //////////////////////////////////////////////Главная//////////////////////////////////////////////////
    //Проверка присутствия элемента
    @Step("Проверка присутствия баннера")
    public void enabledBanner() {
        if(!driver.findElement(imgBanner).isEnabled())//Проверка на присутствие элемента на странице
        { fail("Баннера нет на странице"); }
    }

    //Проверка отображения элемента
    @Step("Проверка отображения баннера")
    public void visibleBanner() {
        if(!driver.findElement(imgBanner).isDisplayed())//Проверка на отображение элемента на странице
        { fail("Баннер не отображается"); }
    }

    //Кликнуть на N категорию
    @Step("Кликнуть на N категорию с Главной")
    public void clickToCategoriFromMain(int numberCategori1) {
        By linkCategori1 = By.xpath(String.format("//div[@id='main']//div/section//a[%d]", numberCategori1));//Заводим xPath элемента, у которого будет меняться значение "%d" в переменной numberCategori1
        actions = new Actions(driver);//нужно для использования события "moveToElement"
        actions.moveToElement(driver.findElement(linkCategori1));//навести курсор на объект
        actions.perform();//нужно для использования события "moveToElement"
        driver.findElement(linkCategori1).click();//Кликнуть на элемент
    }

    //////////////////////////////////////////////Категории//////////////////////////////////////////////////
    //Ожидание отображения элемента
    @Step("Ожидание отображения элемента")
    public void visibilityOfElementLocated() {
        //Ожидание первого элемента в категории
        WebDriverWait wait = new WebDriverWait(driver, 4);//Указывается время ожидания элемента (время прибавляется к "driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)")
        wait.until(ExpectedConditions.visibilityOfElementLocated(articleProduct1Categori));//Ожидание отображение указанного элемента
    }
}
