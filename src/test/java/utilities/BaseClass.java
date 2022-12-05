package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;

public class BaseClass {

	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest loggerNew;
	public static WebDriver webDriver;
	ReadConfig readconfig = new ReadConfig();

	public String userName = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public String baseURL = readconfig.getApplicationURL();
	public String platformType = readconfig.getPlatformType();
	public String browser = readconfig.getBrowser();

	public void setUp() {
		reportSetup();
		try {
			if (platformType.equalsIgnoreCase("web")) {
				if (browser.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--headless");
					webDriver = new ChromeDriver(options);
					loggerNew.info("Chrome Browser opened successfully!!!");
				}
				webDriver.manage().window().maximize();
				webDriver.get(baseURL);
				loggerNew.info("URL is opened!!");

			}
		} catch (Exception e) {
			loggerNew.info("Exception occured while returning the driver");
		}
	}

	public void reportSetup() {
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