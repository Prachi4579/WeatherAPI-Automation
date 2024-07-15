package utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class APIRetry implements IRetryAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(APIRetry.class);

    int count = 0;
    int retryFrequency = 5;
    @Override
    public boolean retry(ITestResult result) {
         if (count < retryFrequency) {
            count++;
            logger.trace("Retry count {}", count);
            return true;
        }
        logger.trace("Maximum retry limit {}", result.getName());
        return false;
    }
}
