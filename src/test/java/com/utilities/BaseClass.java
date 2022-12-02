package com.utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class BaseClass {

	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest loggerNew;
	public static WebDriver webDriver;

	ReadConfig readconfig = new ReadConfig();

	public String userName = readconfig.getUserName();
	public String password = readconfig.getPasswrord();
	public String baseURL = readconfig.getApplicationURL();
	public String platformType = readconfig.getPlatformType();
	public String browser = readconfig.getBrowser();

	// public static Logger logger =
	// LogManager.getLogger(BaseClass.class.getName());

	public void setUp() {
		reportSetup();
		try {
			if (platformType.equalsIgnoreCase("web")) {
				if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
					webDriver = new ChromeDriver();
					loggerNew.info("Chrome Browser opened successfully!!!");
				} else if (browser.equalsIgnoreCase("ie")) {
					System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
					webDriver = new InternetExplorerDriver();
					loggerNew.info("IE Browser opened successfully!!!");
				} else
					loggerNew.info("Invalid Browser: " + browser);

				webDriver.get(baseURL);
				loggerNew.info("URL is opened!!");
				webDriver.manage().window().maximize();
			}
		} catch (Exception e) {
			loggerNew.info("Exception occured while returning the driver");
		}
	}

	private void reportSetup() {
		// TODO Auto-generated method stub
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "TestReport"
				+ File.separator + "AutomationReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Login Automation Report");
		htmlReporter.config().setReportName("Login Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		loggerNew = extent.createTest("Login Test");
	}

	public void tearDown() {
		if (platformType.equalsIgnoreCase("web") && !webDriver.equals(null)) {
			webDriver.quit();
			loggerNew.info("Successfully quit driver!!!");
		}
	}
}