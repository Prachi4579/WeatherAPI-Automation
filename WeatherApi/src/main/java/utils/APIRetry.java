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
        logger.trace("Retrying test {} with status {} for the {} time(s).",
                     result.getName(), result.getStatus(), count + 1);

        if (count < retryFrequency) {
            count++;
            logger.trace("Retry count increased to {}", count);
            return true;
        }
        logger.trace("Maximum retry limit for test {}", result.getName());
        return false;
    }
}
