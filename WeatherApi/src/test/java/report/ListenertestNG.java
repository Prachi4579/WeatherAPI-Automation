package report;
import org.testng.ITestContext;  
import org.testng.ITestListener;  
import org.testng.ITestResult;

import io.restassured.response.Response;
import weather.Parameter;

	public class ListenertestNG implements ITestListener   
	{

		public void onTestStart(ITestContext context) {
			System.out.println("*** Test Suite " + context.getName() + " started ***"+context.getAllTestMethods());
		}

		public void onTestSuccess(ITestResult result) {
			System.out.println("*** Test Suite " + result.getName() + " successful ***"+result.getTestName());

		}

		@Override
		public void onTestFailure(ITestResult result) {
			System.out.println("*** Test Suite " + result.getName() + " failed ***");

		}

		

	}  

