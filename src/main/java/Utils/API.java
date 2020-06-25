package Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.fail;

public class API {
    public WebDriver driver;
    public Global global;

    public API(WebDriver driver) {
        this.driver = driver;
        global = new Global(driver);


    }

    @Step("Проверка ответа API 'Получение информации по корзине'")
    public void testGetSingleUserProgrammatic() {
        try {
            given().get("https://dostavka.auchan.ru/v1/cart/data").then().statusCode(200);
        }
        catch(WebDriverException e)
        {
            fail("/cart/data не равен '200'");
            System.out.println("Huy" + e);
        }
    }
}
