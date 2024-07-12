package report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import resources.ExtentReportNG;

public class ListenertestNG extends ExtentReportNG implements ITestListener   
{  	private static final Logger logger = LoggerFactory.getLogger(ListenertestNG.class);

	public static ExtentTest test1;
	public void onTestStart(String string) {
		System.out.println("\n*** Test Suite " + string + " started ***");
		test1 = extent.createTest(string);
	}
	public void onTestSuccess(ITestResult result) {
		test1.log(Status.PASS,"*** Test Suite " + result.getMethod().getMethodName() + " successful ***");
		logger.info(result.getMethod().getMethodName());
	}
	@Override
	public void onTestFailure(ITestResult result) {
		test1.log(Status.FAIL,"*** Test Suite " + result.getMethod().getMethodName() + " failure ***");
		test1.fail(result.getThrowable());
		logger.debug( result.getMethod().getMethodName());
	}
	@Override
	public void onFinish() {
		extent.flush();
	}
	public static ITestResult getTestResult() {
		return getTestResult();
	}
	@Override
	public void onTestSkipped(ITestResult result) {
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
	@Override
	public void onStart(ITestContext context) {
	}
	@Override
	public void onTestStart(ITestResult result) {
	}
	@Override
	public void onFinish(ITestContext context) {
	}
}  

