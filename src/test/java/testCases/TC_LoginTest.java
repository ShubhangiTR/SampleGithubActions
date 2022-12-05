package testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.BaseClass;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TC_LoginTest extends BaseClass {

	@BeforeClass
	public void initTest() throws Exception {
		setUp();
	}

	@Test
	public void loginTest() throws InterruptedException {
		LoginPage lp = new LoginPage();
		lp.setUserName(userName);
		loggerNew.info("Entered Username!!");

		lp.setPassword(password);
		loggerNew.info("Entered Password!!");

		lp.clickOnLogin();
		loggerNew.info("Clicked on Login Button!!");

		if (webDriver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			loggerNew.pass("Title is verified successfully!!");
			loggerNew.info("Login Test Passed!!");
			String logText = "Test Case: loginTest Passed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			loggerNew.log(Status.PASS, m);
		} else {
			loggerNew.pass("Title is not matched.");
			loggerNew.info("Login Test Failed!!");
			String logText = "Test Case: loginTest Failed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			loggerNew.log(Status.FAIL, m);			
		}
	}

	@AfterTest
	public void afterTestMethod() {
		extent.flush();
	}
	
	@AfterClass(alwaysRun = true)
	public void quitDriver() {
		tearDown();
	}

}
