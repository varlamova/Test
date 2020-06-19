package Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Popup {
    private WebDriver driver;

    public Popup(WebDriver driver){
        this.driver = driver;
    }

    private By buttonCloseAllPopup = By.xpath("//button[contains(@class, \"popup__close\")]");
    private By buttonCloseIAgree = By.xpath("//button[text() = \"Я согласен\"]");

    @Step("Закрытие попапа")
    public void clickClosePopup(){
        driver.findElement(buttonCloseAllPopup).click();
    }

    @Step("Закрытие попапа 'Я согласен'")
    public void clickCloseIAgree(){
        driver.findElement(buttonCloseIAgree).click();
    }

}
