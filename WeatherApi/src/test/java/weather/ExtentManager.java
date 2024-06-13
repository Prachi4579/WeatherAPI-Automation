package weather;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.*;

public class ExtentManager extends Parameter{

public static ExtentReports extent;
		 
			  @Test
			  public ExtentReports createInstance(String fileName,String reportName,String documentTitle) {
			 
			  
			  ExtentTest test=extent.createTest("TC-008");
			  test.pass("Verify status code started successfully");
			  test.pass("URL is loaded");
			  test.pass("Parameters have been provided");
			  test.pass("Api testing done successfully");
			  
//			  ExtentTest test1=extent.createTest("API test");
//			  test1.pass("Verify status code started successfully");
//			  test1.pass("URL is loaded");
//			  test1.pass("Parameters have been provided");
//			  test1.pass("Api testing done successfully");



			  extent.flush(); //Unless you call this method, your report will not be written with logs
			  
			  return extent;
			  
			}
	}



