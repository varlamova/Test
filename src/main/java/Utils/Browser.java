package Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.Dimension;

public class Browser {
    public WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие браузера Chrome")
    public WebDriver Chrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    @Step("Открытие браузера Firefox")
    public WebDriver FireFox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }

    @Step("Открытие браузера Edge")
    public WebDriver Edge() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        return driver;
    }

    @Step("Открытие браузера Opera")
    public WebDriver Opera() {
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver();
        return driver;
    }

    @Step("Открытие браузера IE")
    public WebDriver IE() {
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
        return driver;
    }

    @Step("Применение размера окна браузера")
    public void BrowserSize()  {
        driver.manage().window().setSize(new Dimension(1920, 1080));//Размеры окна браузера
    }

    @Step("Открытие url страницы")
    public void GetUrl()  {
        driver.get("https://dostavka.auchan.ru/");//Открыть в браузере url "https://dostavka.auchan.ru/"
    }

    @Step("Удаление всех кук")
    public void DeleteAllCookies()  {
        driver.manage().deleteAllCookies();//Удалить все куки
    }

    @Step("Обновление страницы")
    public void RefreshPage()  {
        driver.navigate().refresh();//Обновить страницу
    }

    @Step("Закрытие браузера")
    public void CloseDriver()  {
        driver.close();
    }
}
