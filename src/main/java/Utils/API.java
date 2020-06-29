package Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import static Pages.Header.Utils.searctName;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.fail;

public class API {
    public WebDriver driver;
    public Global global;
    public static String getInformationForCart = "https://dostavka.auchan.ru/v1/cart/data";
    public static String getInformationForSearch = "https://dostavka.auchan.ru/v1/search";
    public static String getInformationForProductDetail = "https://dostavka.auchan.ru/v1/catalog/product-detail";

    public API(WebDriver driver) {
        this.driver = driver;
        global = new Global(driver);
    }

    /////////////////////////////////Корзина///////////////////////////////////////////////////
    //Просто получение статуса запроса: 200, 404, 502 и так далее
    @Step("Проверка ответа API 'Получение информации по корзине'")
    public void testGetStatusCart() throws AssertionError {
        try { given().get(getInformationForCart).then().statusCode(200); }
        catch(AssertionError e) { fail("Запрос не равен '200'"); }
    }

    //Проверка динамических атрибутов в запросе опираясь на куки сайта
    @Step("Проверка ответа API в корзине 1 товар")
    public void testGetTotalCountCart() throws AssertionError {
        try { given().cookie("laravel_session", driver.manage().getCookieNamed("laravel_session").getValue()).when().get(getInformationForCart).then().body("minCart.totalCount", equalTo(1)); }
        catch(AssertionError e) {
            fail("В корзине не 1 товар"); }
    }

    ///////////////////?/////////////Поиск////////////////////////////////////////////////////////
    //Проверка запроса на изменение url страницы и его ответ
    @Step("Проверка API 'Поиск по слову яблоки'")
    public void testPutProductt() throws AssertionError {
        try { given().queryParam("query", searctName).get(getInformationForSearch).then().statusCode(200); }
        catch(AssertionError e) { fail("Запрос не равен '200'"); }
    }

    ///////////////////////////////Категории////////////////////////////////////////////////////////
    //Проверка запроса на наличие параметров и их значений
    @Step("Проверить наличие параметров (code, id, categoryCodes, title, vendorCode, price, active) из запроса API")
    public void testGetCodeProduct() {
        //Преобразовать url в виде кода товара
        String codeProduct = driver.getCurrentUrl().split("product/")[1].replace( "/","");
        System.out.println(codeProduct);
        //Проверить наличие параметров из запроса API
        try { given().param("code", codeProduct).when().get(getInformationForProductDetail).then().assertThat()
                .body("id", notNullValue())
                .body("code", equalTo(codeProduct))
                .body("categoryCodes", notNullValue())
                .body("title", notNullValue())
                .body("vendorCode", notNullValue())
                .body("price", notNullValue())
                .body("active", equalTo(true)).statusCode(200); }
        catch(AssertionError e) {
            fail("Запрос не равен '200'"); }
    }

}
