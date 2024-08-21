import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SamokatHomePageTest {

    private final WebDriver driver;
    private final By headerMakeOrderButton = By.xpath("//button[@class= 'Button_Button__ra12g']");
    private final By bigMakeOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");

    public void clickHeaderMakeOrderButton() {
        driver.findElement(headerMakeOrderButton).click();
    }

    public SamokatHomePageTest(WebDriver driver) {
        this.driver = driver;
    }

    //проверка большой кнопки в body
    public void checkBigOrderButton(){
        WebElement element = driver.findElement(bigMakeOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(bigMakeOrderButton));
        driver.findElement(bigMakeOrderButton).click();

    }

    public String gettitleOfTheFormText(){
        return  driver.findElement(By.xpath("//div[@class= 'Order_Header__BZXOb']")).getText();
    }

    public String comparePanels(String FAQItem, String FAQAnswerText) {
        WebElement element = driver.findElement(By.id(FAQItem));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.id(FAQItem)).click();
        return driver.findElement(By.id(FAQAnswerText)).getText();
    }

}