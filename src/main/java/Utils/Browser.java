package Utils;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bolshakova\\TestByFil\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    @Step("Закрытие браузера")
    public void CloseDriver()  {
        driver.close();
    }


    @Step("Открытие браузера Firefox в разрешении 1920:1080")
    public WebDriver FireFox() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\bolshakova\\TestByFil\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        return driver;
    }

    @Step("Открытие браузера Edge в разрешении 1920:1080")
    public WebDriver Edge() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\bolshakova\\TestByFil\\drivers\\geckodriver.exe");
        driver = new EdgeDriver();
        return driver;
    }
}
