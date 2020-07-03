import Pages.BasicTestTemplates.BasicTestTemplates;
import Utils.Browser;
import Utils.Global;
import Utils.Popup;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import java.util.concurrent.TimeUnit;

@Ignore
    @DisplayName("Основные события для тест-сценариев")//Название тест-сценария для allure
        public class BasicTestTemplatesClassTest {
        public static WebDriver driver;
        public Popup popup;
        public BasicTestTemplates basicTestTemplates;
        private Browser browserClose;
        public Global global;

        //действия, которые должны выполняться в начале всех тест-сценариев
        @BeforeClass
        public static void setup() {
            driver = new Browser(driver).Chrome();
            driver.manage().window().setSize(new Dimension(1920, 1080));//Размеры окна браузера
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Время ожидания действия сценария
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);//Время ожидания загрузки страницы
            driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);//Время ожидания действия скриптов на странице
        }

        //действия, которые должны выполняться в начале каждого тест-сценария
        @Before
        public void beforeClassMethod() {
            driver.get("https://dostavka.auchan.ru/");//Открыть в браузере url "https://dostavka.auchan.ru/"
            driver.manage().deleteAllCookies();//Удалить все куки
            driver.navigate().refresh();//Обновить страницу
            basicTestTemplates = new BasicTestTemplates(driver);//Используемые классы в данном тест-сценарии
            popup = new Popup(driver);//Используемые классы в данном тест-сценарии
            global = new Global(driver);//Используемые классы в данном тест-сценарии
            browserClose = new Browser(driver);//Использование браузера
            popup.clickClosePopup();
            popup.clickCloseIAgree();
        }

        /////////////////////////////////////Скриншоты при ошибках////////////////////////////////////////
        //Стандартная функция для того, чтобы делать скриншоты в allure, после каждого сломанного тест-кейса
        @Rule
        public TestWatcher screenshotOnFailure = new TestWatcher() {
            @Override
            protected void failed(Throwable e, Description description) {
                makeScreenshotOnFailure();
            }

            @Attachment("Screenshot on failure")
            public byte[] makeScreenshotOnFailure() {
                try {
                    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                } catch (WebDriverException e) {
                    e.printStackTrace();
                }
                return new byte[0];
            }
        };

        //////////////////////?/////////////////Тест-Сценарии///////////////////////////////////////////////////
        @Test
        @Link(name = "Ссылка на страницу Корзины", url = "https://dostavka.auchan.ru/cart/")//Добавление в allure ссылки на нужную страницу
        @Severity(SeverityLevel.MINOR)//Критичность тест-сценария для отображении в allure (BLOCKER, CRITICAL,MINOR, NORMAL, TRIVIAL)
        @DisplayName("Применение основных событий на странице корзины")//Название тест-сценария в allure
        @Owner(value = "Большакова Полина Денисовна")//Инициалы того, кто написал тест-сценарий
        public void BasicTestTemplatesBasket() throws InterruptedException {
            basicTestTemplates.Basket();//Тест-сценарий, написанный в "basicTestTemplates"
        }

        @Test
        @Severity(SeverityLevel.NORMAL)//Критичность тест-сценария для отображении в allure (BLOCKER, CRITICAL,MINOR, NORMAL, TRIVIAL)
        @DisplayName("Применение основных событий на главной странице")//Название тест-сценария в allure
        @Owner(value = "Большакова Полина Денисовна")//Инициалы того, кто написал тест-сценарий
        public void BasicTestTemplatesMain() throws InterruptedException {
            basicTestTemplates.Main();//Тест-сценарий, написанный в "basicTestTemplates"
        }

        @Test
        @Severity(SeverityLevel.NORMAL)//Критичность тест-сценария для отображении в allure (BLOCKER, CRITICAL,MINOR, NORMAL, TRIVIAL)
        @DisplayName("Проверка основных запросов API")//Название тест-сценария в allure
        @Owner(value = "Большакова Полина Денисовна")//Инициалы того, кто написал тест-сценарий
        public void BasicTestTemplateAPI() {
            basicTestTemplates.API();//Тест-сценарий, написанный в "basicTestTemplates"
        }

        //действия, которые должны выполняться в конце всех тест-сценариев
        @AfterClass
        public static void tearDownClass() {
            driver.quit();//Завершить работу драйвера, т.е. закрытие полностью браузера
        }
}
