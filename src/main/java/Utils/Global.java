package Utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class Global {
    public WebDriver driver;
    public static String url = "https://dostavka.auchan.ru/";

    public Global (WebDriver driver) {
        this.driver = driver;
    }

    //////////////////////Скриншот после провалленого Step/////////////////////
    @Attachment("Screenshot on failure")
    public byte[] screenshot() {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
