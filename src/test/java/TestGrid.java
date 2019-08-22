import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.Thread.sleep;

/**
 * Created by tianweiwang on 2019/8/21.
 */
public class TestGrid {
    @DataProvider (name="br")
    public Object[][] dataProvider(){
        return new Object[][]{
                {"chrome"},
                {"safari"},
                {"firefox"}
        };
    }

    @DataProvider (name="search")
    public Object[][] dataProvider2(){
        return new Object[][]{
                {"baidu"},
                {"tengxun"},
                {"ali"}
        };
    }

    @org.testng.annotations.Test(dataProvider = "br")
    public void testGrid(String br) throws InterruptedException, MalformedURLException {
        DesiredCapabilities dc;//= new DesiredCapabilities().firefox();
        switch (br){
            case "firefox":
                dc = new DesiredCapabilities().firefox();
            case "chrome":
                dc = new DesiredCapabilities().chrome();
            case "safari":
                dc = new DesiredCapabilities().safari();
            default:
                dc = new DesiredCapabilities().chrome();
        }
        System.out.println(br+" :");
        dc.setBrowserName(br);
        dc.setPlatform(Platform.MAC);
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.2.111:6666/wd/hub"),dc);
        driver.get("https://www.baidu.com/");
        sleep(2000);
        WebElement help = driver.findElement(By.id("kw"));
        help.sendKeys("selenium firefox");
        sleep(2000);
        driver.findElement(By.id("su")).click();
        sleep(2000);
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title,"selenium firefox_百度搜索","不等");
        driver.quit();
    }
}
