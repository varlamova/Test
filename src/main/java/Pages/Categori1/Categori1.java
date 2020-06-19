package Pages.Categori1;

import Pages.Header.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Categori1 {
    private WebDriver driver;
    private Actions actions;
    private Pages.Header.Utils utils;

    public Categori1(WebDriver driver){
        this.driver = driver;
        utils = new Utils(driver);
    }
}
