package utils;

import log.LoggerController;
import org.apache.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.IOException;

/**
 * Created by tianweiwang on 2019/9/2.
 */
public class MyAssert {
    public  static Logger log = LoggerController.getLogger(MyAssert.class);

    public static boolean assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            return true;
        } catch (AssertionError e) {
            CommonUtils.takeFullScreenshot("");  //也可copy Assert代码，只修改fail()方法
            log.error(message);
            e.printStackTrace();
            return false;
        }

    }

    public static boolean assertEquals(String actual, String expected) throws IOException {
        try {
            Assert.assertEquals(actual, expected);
            return true;
        } catch (AssertionError e) {
            CommonUtils.takeFullScreenshot("");  //也可copy Assert代码，只修改fail()方法
            e.printStackTrace();
            return false;
        }

    }

    public static boolean assertNotNull(Object obj, String message) {
        try {
            Assert.assertNotNull(obj, message);
            return true;
        } catch (AssertionError e) {
            CommonUtils.takeFullScreenshot("");  //也可copy Assert代码，只修改fail()方法
            e.printStackTrace();
            return false;
        }

    }

    public static boolean assertNotNull(Object obj) {
        try {
            Assert.assertNotNull(obj);
            return true;
        } catch (AssertionError e) {
            CommonUtils.takeFullScreenshot("");  //也可copy Assert代码，只修改fail()方法
            e.printStackTrace();
            return false;
        }

    }

    public static boolean assertTrue(boolean expression, String message) {
        try {
            Assert.assertTrue(expression, message);
            return true;
        } catch (AssertionError e) {
            CommonUtils.takeFullScreenshot("");  //也可copy Assert代码，只修改fail()方法
            e.printStackTrace();
            return false;
        }
    }

    public static boolean assertTrue(boolean expression) {
        try {
            Assert.assertTrue(expression);
            return true;
        } catch (AssertionError e) {
            CommonUtils.takeFullScreenshot("");  //也可copy Assert代码，只修改fail()方法
            e.printStackTrace();
            return false;
        }
    }


}
