package resources;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNG {
	
	public static ExtentReports extent;
    
	public static boolean cicdExecution = true;
    public static ExtentReports getReportObject() {
		extent = new ExtentReports();

		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("hh_mm_a");
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedDate = date.format(localDateTime);
		String formattedTime = time.format(localDateTime);
		String dateFolderPath = System.getProperty("user.dir") + "/report/";
		new File(dateFolderPath).mkdirs();
		String reportPath = "";
		if(cicdExecution) {
			reportPath = dateFolderPath  + "Report.html";
		}else {
			 dateFolderPath = dateFolderPath  + formattedDate;
			 String timeFolderPath = dateFolderPath + "/" + formattedTime+"Report";
			 reportPath = timeFolderPath + "/Report.html";
		}
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Weather Automation Report");
		spark.config().setReportName("Weather API Test Report");

		extent.attachReporter(spark);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		
		return extent;
		}
    
    public void onFinish() {
		// TODO Auto-generated method stub
		extent.setSystemInfo("Tester", "Prachi Sharma");
		extent.setSystemInfo("OS", "Windows11");
		
	}


}
