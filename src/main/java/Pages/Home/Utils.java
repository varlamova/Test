package Pages.Home;

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

    public Utils(WebDriver driver) {
        this.driver = driver;
        global = new Global(driver);
    }

    ///////////////////////////////Все объекты Главной/////////////////////////////////
    private By buttonAddress = By.xpath("//main//button[ text() = \"Проверить адрес доставки\"]");
    private By inputAddress= By.xpath("//div[@class = \"popup\"]//input[@name='deliveryAddress']");
    private By liVariantAddress= By.xpath("//div[@class = \"popup\"]//ul[@role = \"listbox\"]/li[1]");
    private By buttonSaveAddress= By.xpath("//div[@class = \"popup\"]//button[@type='submit']");
    private By textSaveAddress= By.xpath("//main/section//div[2]//p");
    private By imgBanner = By.xpath("//img[@class='picture__img']");

    ///////////////////////////////Баннер/////////////////////////////////
    @Step("Проверка отображения баннера")
    public void visiblebanner() {
        if(!driver.findElement(imgBanner).isDisplayed()){
            fail("Баннер не отображается");
        }
    }

    ///////////////////////////////Категории на Главной странице/////////////////////////////////
    @Step("Кликнуть на N категорию с Главной")
    public void clickToCategoriFromMain(int numberCategori1) {
        String outputCategori1 = String.format("//div[@id='main']//div/section//a[%d]", numberCategori1);
        By linkCategori1 = By.xpath(outputCategori1);
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(linkCategori1));
        actions.perform();
        driver.findElement(linkCategori1).click();
    }

    ///////////////////////////////Адрес доставки/////////////////////////////////

    @Step("Нажать на кнопку 'Адрес доставки'")
    public void clickAddressDelivery() {
        driver.findElement(buttonAddress).click();
    }

    @Step("Написать адрес в попапе 'Адрес доставки'")
    public void setTextAddressDelivery() throws InterruptedException {
        driver.findElement(inputAddress).click();
        Thread.sleep(1000);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys("г Москва, ул Красносельская Верхн., д 15 стр 2");
    }

    @Step("Ожидание появления вариантов адресов")
    public void waitVariantAddressDelivery() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(liVariantAddress));
    }

    @Step("Выбрать первый вариант адреса")
    public void clickVariantAddressDelivery() {
            driver.findElement(liVariantAddress).click();
    }

    @Step("Сохранить адрес")
    public void clickSaveAddressDelivery() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSaveAddress));
        driver.findElement(buttonSaveAddress).click();
    }

    @Step("Проверка отображения баннера")
    public void varifyAddress() {
        if(!driver.findElement(textSaveAddress).isDisplayed()){
            fail("Адрес нельзя вписать");
        }
    }
}
