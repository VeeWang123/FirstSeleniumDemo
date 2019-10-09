package utils;

import log.LoggerController;
import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by tianweiwang on 2019/9/2.
 */
public class TestNGRetry implements IRetryAnalyzer{
    /**
     * 用例失败自动重跑逻辑
     */
        public  static Logger log = LoggerController.getLogger(TestNGRetry.class);
        private int retryCount = 1;
        private int maxRetryCount=1;


        public boolean retry(ITestResult result) {
            if (retryCount <= maxRetryCount) {
                String message = "running retry for  '" + result.getName() + "' on class " +
                        this.getClass().getName() + " Retrying " + retryCount + " times！！！";
                log.info(message);
                CommonUtils.takeFullScreenshot("retry");
                retryCount++;
                System.out.println("retryCount: "+ retryCount);
                return true;
            }
            return false;
     }



}
