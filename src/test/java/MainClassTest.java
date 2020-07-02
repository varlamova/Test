import Pages.Header.Header;
import Pages.Home.Home;
import Pages.Categori1.Categori1;
import Utils.API;
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

@DisplayName("Главная")
public class MainClassTest {
        public static WebDriver driver;
        public Header header;
        public Popup popup;
        public Categori1 categori1;
        public Home main;
        public Global global;
        private Browser browser;
        public API api;

        @BeforeClass
        public static void setup() {
            driver = new Browser(driver).Chrome();
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        }

        @Before
        public void beforeClassMethod() {
            driver.get("https://dostavka.auchan.ru/");
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
            header = new Header(driver);
            popup = new Popup(driver);
            main = new Home(driver);
            categori1 = new Categori1(driver);
            global = new Global(driver);
            browser = new Browser(driver);
            api = new API(driver);
            popup.clickClosePopup();
            popup.clickCloseIAgree();
        }

        //////////////////Скриншоты при ошибках//////////////////////
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

        //////////////////////////Главная страница////////////////////////////////
        @Test
        @DisplayName("Баннер на главной")
        @Owner(value = "Большакова Полина Денисовна")
        public void MainBanner() {
            main.banner();
        }

        @Test
        @DisplayName("Адрес доставки на главной")
        @Owner(value = "Большакова Полина Денисовна")
        public void MainAddressDelivery() throws InterruptedException {
            main.inputAddressDelivery();
        }

        @Test
        @Flaky
        @Severity(SeverityLevel.MINOR)
        @Owner(value = "Большакова Полина Денисовна")
        @DisplayName("Категории на главной")
        public void MainCategori1() throws InterruptedException {
            main.clicklinkCategori1();
        }

        @Test
        @Flaky
        @Severity(SeverityLevel.MINOR)
        @Owner(value = "Большакова Полина Денисовна")
        @DisplayName("Проверить товар на наличие и положить в корзну")
        public void MainProductCart() throws InterruptedException {
            categori1.clickProduct();
        }

        @AfterClass
        public static void tearDownClass() {
            driver.quit();
        }

}
