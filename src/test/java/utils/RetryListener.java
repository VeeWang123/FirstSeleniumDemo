package utils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
 * Created by tianweiwang on 2019/9/2.
 */
public class RetryListener implements IAnnotationTransformer {


    /**
     * 添加用例重跑监听器，用例失败自动重跑功能
     */
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        if (retry == null) {
            annotation.setRetryAnalyzer(TestNGRetry.class);
        }
    }

}
