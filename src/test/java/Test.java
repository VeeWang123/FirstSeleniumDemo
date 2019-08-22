import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.*;


/**
 * Created by tianweiwang on 2019/8/19.
 */
public class Test {
    public WebDriver driver;

    @BeforeMethod
    public void openBrowser(){
//        FirefoxOptions capabilities = new FirefoxOptions();
//        capabilities.setCapability("marionette", true);
//        System.setProperty("webdriver.gecko.driver","./lib/geckodriver");
//        driver = new FirefoxDriver(capabilities);
        System.setProperty("webdriver.chrome.driver","./lib/chromedriver");
        driver = new ChromeDriver();
//        driver = new SafariDriver();

    }
    @DataProvider (name="search")
    public Object[][] dataProvider2(){
        return new Object[][]{
                {"百度"},
                {"腾讯"},
                {"阿里"}
        };
    }
    @org.testng.annotations.Test(dataProvider = "search")
    public void testURL(String search) throws InterruptedException {
//        driver.get("http://192.168.2.111:4444/grid/console?config=true&configDebug=true");
        driver.get("https://www.baidu.com/");
        sleep(2000);
        WebElement help = driver.findElement(By.id("kw"));
        help.sendKeys(search);
        sleep(2000);
        driver.findElement(By.id("su")).click();
        sleep(2000);
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title,search+"_百度搜索","not equal！");
    }

    @org.testng.annotations.Test //(dataProvider = "search")
    public void testPageFactory() throws InterruptedException {
        BaiduPage baiduPage = new BaiduPage(driver);
//        PageFactory.initElements(driver,BaiduPage.class);
        driver.get("https://www.baidu.com/");
        baiduPage.search("hahaha");


    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();

    }



}
