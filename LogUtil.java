import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class LogUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void logInfo(String className, String message, Map<String, Object> additionalInfo) {
        Logger logger = LogManager.getLogger(className);
        try {
            String jsonLog = mapper.writeValueAsString(additionalInfo);
            ThreadContext.put("additionalInfo", jsonLog);
            logger.info(message);
        } catch (Exception e) {
            logger.error("Error in logging", e);
        } finally {
            ThreadContext.clearAll();
        }
    }

    public static void logError(String className, String message, Throwable throwable) {
        Logger logger = LogManager.getLogger(className);
        logger.error(message, throwable);
    }
}