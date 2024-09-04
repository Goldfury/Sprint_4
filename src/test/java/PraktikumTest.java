import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;

@RunWith(Parameterized.class)
public class PraktikumTest {

    private WebDriver driver;
    private final String text;
    private final String FAQItem;
    private final String FAQAnswerText;
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    //тут просто проверяется код на заказ
    @Test
    public void createOrderTest() {
        // создали драйвер для браузера Chrome
        driver = new ChromeDriver();
        // перешли на страницу тестового приложения
        //тест работает но не дошло до страницы подтверждения заказа
        driver.get(url);
        SamokatOrderPage objSamokatOrderPage = new SamokatOrderPage(driver);
        objSamokatOrderPage.fillSamokatOrderForm();
        //Проверка которая должна сломаться так как в Хроме не выходит что Заказ оформлен
        // задаче написано:Обрати внимание: в приложении есть баг, который не даёт оформить заказ. Он воспроизводится только в Chrome.
        //Ты можешь заметить этот баг, когда будешь писать тесты или запускать их. Ещё может случиться так: тест наткнётся на баг и упадёт. Пусть тебя это не смущает: если тест помог найти неисправность, это хорошо.
        String confirmText = objSamokatOrderPage.getConfirmOrderText();
        Assert.assertTrue("Текст 'Заказ оформлен' не найден", confirmText.contains("Заказ оформлен"));
    }

    //В данном тесте проверяется только большая кнопка на странице со сравнением с текстом, так как в header уже идет проверка в формировании заказа
    @Test
    public void checkBigOrderbuttonTest(){
        driver = new ChromeDriver();
        driver.get(url);
        SamokatHomePageTest objSamokatHomePageTest = new SamokatHomePageTest(driver);
        objSamokatHomePageTest.checkBigOrderButton();
        String titleOfTheForm = objSamokatHomePageTest.gettitleOfTheFormText();
        String expected = "Для кого самокат";
        Assert.assertEquals("Окно не открылось", expected, titleOfTheForm);
    }

        // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "accordion__heading-0", "accordion__panel-0" },
                { "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "accordion__heading-1", "accordion__panel-1"},
                { "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента," +
                        " когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "accordion__heading-2", "accordion__panel-2" },
                { "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "accordion__heading-3", "accordion__panel-3" },
                { "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "accordion__heading-4", "accordion__panel-4" },
                { "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "accordion__heading-5", "accordion__panel-5" },
                { "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "accordion__heading-6", "accordion__panel-6" },
                { "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "accordion__heading-7", "accordion__panel-7" },
        };
    }

    public PraktikumTest(String text, String FAQItem, String FAQAnswerText) {
        this.text = text;
    this.FAQItem = FAQItem;
    this.FAQAnswerText =FAQAnswerText;
    }

    @Test
    //Проверка Выпадающего список в разделе «Вопросы о важном». в самом в методе вставлена 8 методов которые проверяют каждую кнопку
    public void checkTextInDropdownTest(){
        driver = new ChromeDriver();
        driver.get(url);
        SamokatHomePageTest objSamokatHomePageTest = new SamokatHomePageTest(driver);
        String resultText =objSamokatHomePageTest.comparePanels(FAQItem, FAQAnswerText);
        Assert.assertEquals("Текст 1 не сходится", text, resultText);
        System.out.println(resultText);


    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}