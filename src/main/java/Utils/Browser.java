package Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
    public WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие браузера Chrome в разрешении 1920:1080")
    public WebDriver Chrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    @Step("Закрытие браузера")
    public void CloseDriver()  {
        driver.close();
    }


    @Step("Открытие браузера Firefox в разрешении 1920:1080")
    public WebDriver FireFox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }

    @Step("Открытие браузера Edge в разрешении 1920:1080")
    public WebDriver Edge() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        return driver;
    }
}
