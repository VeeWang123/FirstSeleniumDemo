package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Created by tianweiwang on 2019/8/27.
 */
public class SeleniumDriver {
    public static WebDriver driver;

    public static WebDriver open(String browser){
        String path=System.getProperty("user.dir");
        switch (browser){
            case "firefox":
                FirefoxOptions capabilities = new FirefoxOptions();
                capabilities.setCapability("marionette", true);
                System.setProperty("webdriver.gecko.driver","./lib/geckodriver"); //path?
                driver = new FirefoxDriver(capabilities);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver","./lib/chromedriver");
                driver = new ChromeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                driver.manage().window().maximize();

                break;
            default:
                System.setProperty("webdriver.chrome.driver","./lib/chromedriver");
                driver = new ChromeDriver();
                System.out.println("open Chrome by default");
        }
        return driver;
    }

    public static void closeBrowser(){
        if(driver!=null){
            driver.quit();
        }
    }

}
