package resources;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.PropertiesReader;

public class ExtentReportNG {

	private static final Logger logger = LoggerFactory.getLogger(ExtentReportNG.class);
	public static ExtentReports extent;
	public static boolean cicdExecution;
	static Properties properties ;
	static {
		properties = PropertiesReader.getEndPoint();
		cicdExecution = false;
		if (properties != null) {
			cicdExecution = Boolean.parseBoolean(properties.getProperty("cicdExecutionFlag"));
		} 
		logger.info("cicdExecution : " + cicdExecution);
	}
	public static ExtentReports getReportObject() {
		extent = new ExtentReports();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("hh_mm_a");
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedDate = date.format(localDateTime);
		String formattedTime = time.format(localDateTime);
		String dateFolderPath = System.getProperty("user.dir") + "/report/";
		new File(dateFolderPath).mkdirs();
		String reportPath ="";
		if(cicdExecution) {
			reportPath = dateFolderPath  +  PropertiesReader.reportMetaData().getProperty("reportname");
			logger.info(reportPath);
		}else {
			dateFolderPath = dateFolderPath  + formattedDate;
			String timeFolderPath = dateFolderPath + "/" + formattedTime+"Report";
			reportPath = timeFolderPath + "/" + PropertiesReader.reportMetaData().getProperty("reportname");
			logger.info(timeFolderPath);
		}
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle(PropertiesReader.reportMetaData().getProperty("documenttitle"));
		spark.config().setReportName(PropertiesReader.reportMetaData().getProperty("setreportname"));
		extent.attachReporter(spark);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		logger.info("extent.. ",extent);
		return extent;
	}
	public void onFinish() {
		
	}
	}
