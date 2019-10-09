package element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianweiwang on 2019/8/28.
 */
public class WebAction {

    public static ArrayList getTexts(By by){
        ArrayList texts=new ArrayList();
        List<WebElement> elements=ElementUtils.findElements(by);
        for(WebElement el:elements){
            String text = el.getText();
            texts.add(text);
        }
        return texts;
    }


    public static void doubleclick(WebDriver driver,By by){
        Actions actions = new Actions(driver);
        actions.doubleClick(ElementUtils.findElement(by)).perform();
    }
}
