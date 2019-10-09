package element;

import driver.SeleniumDriver;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by tianweiwang on 2019/8/27.
 */
public class ElementUtils extends SeleniumDriver{

    public static WebElement findElement(final By by){     //By:元素locator
        WebElement element = null;
        try {
            element = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            System.out.println("Time out. Cannot find: "+by);
            e.printStackTrace();
        }
        return element;
    }

    public static List<WebElement> findElements(final By by){
        List<WebElement> elements= null;
        //省略try语句
        elements = new WebDriverWait(driver,5).until(new ExpectedCondition<List<WebElement>>() {
            @Override
            public List<WebElement> apply(@NullableDecl WebDriver webDriver) {
                return driver.findElements(by);
            }
        });
        return elements;
    }
}
