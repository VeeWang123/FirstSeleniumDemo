import element.ElementUtils;
import element.WebAction;
import log.LoggerController;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import utils.CommonUtils;
import utils.MyAssert;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by tianweiwang on 2019/8/29.
 */
public class TestFrameworkElementUtils {
    public WebDriver driver;
    public  static Logger log = LoggerController.getLogger(TestFrameworkElementUtils.class);


    @org.testng.annotations.Test
    public void testFrameworkElementUtils() throws InterruptedException {
        driver=ElementUtils.open("safari");
        driver.get("https://www.baidu.com/");
        WebElement kw=ElementUtils.findElement(By.id("kw"));
        kw.sendKeys("selenium");
        sleep(2000);
        ElementUtils.findElement(By.id("su")).click();
        sleep(2000);
        System.out.println(driver.getTitle());
    }

    @org.testng.annotations.Test
    public void testActions() throws InterruptedException {
        driver=ElementUtils.open("safari");
        driver.get("https://www.baidu.com/");
        List<String> texts= WebAction.getTexts(By.xpath("//a[contains(@name,\"ime\")]"));
        ElementUtils.findElement(By.id("kw")).sendKeys("selenium");
        sleep(2000);
        System.out.println(texts.toString());
        WebAction.doubleclick(driver,By.id("su"));
    }

    @org.testng.annotations.Test
    public void testLog(){
        log.info("this is info");
        log.warn("this is warn");
        log.error("this is error");
        log.fatal("this is fatal");
        log.debug("this is debugggg");
    }

    @org.testng.annotations.Test
    public void testUtilsDate(){
        String type="yyyyMMdd";
        String date=CommonUtils.getDate(type);
        System.out.println(date);
    }

    @Test
    public void testScreenshot(){
        driver=ElementUtils.open("safari");
        driver.get("https://www.baidu.com/");
        CommonUtils.takeFullScreenshot("");
    }

    @Test
    public void testListener() throws InterruptedException {
        driver=ElementUtils.open("chrome");
        driver.get("https://www.baidu.com/");
        log.info("testListener begins ...");
        String el=ElementUtils.findElement(By.id("xxxxx")).getText();  //故意跑断
        System.out.println("continue running");
    }

    @AfterMethod
    public void close(){
        System.out.println(ElementUtils.driver);
        ElementUtils.closeBrowser();
    }




}


