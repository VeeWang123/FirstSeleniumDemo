import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by tianweiwang on 2019/8/22.
 */
public class BaiduPage {

    WebDriver driver;

    @FindBy(id="kw")
    WebElement input;

    @FindBy(id="su")
    WebElement submit;

    /*
        public static By input2=By.id("kw");
        如何使用？哪个更好？
     */

    public BaiduPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void search(String content) throws InterruptedException {
        input.sendKeys(content);
        submit.click();
        Thread.sleep(2000);
    }



}
