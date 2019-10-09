package log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by tianweiwang on 2019/8/30.
 */
public class LoggerController {
    public static Logger logger;

    public static Logger getLogger(Class<?> T){
        String path=System.getProperty("user.dir")+"/src/test/java/configs/log4j.properties";
        PropertyConfigurator.configure(path);
        if(logger==null){
            logger = Logger.getLogger(T);
        }
        return logger;
    }

}
