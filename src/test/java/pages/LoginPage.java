package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseClass;

public class LoginPage extends BaseClass {

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void setUserName(String uname) {
		WebElement userName = webDriver.findElement(By.xpath("//input[@name='uid']"));
		userName.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		WebElement password = webDriver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys(pwd);
	}

	public void clickOnLogin() {
		WebElement btnLogin = webDriver.findElement(By.xpath("//input[@name='btnLogin']"));
		btnLogin.click();
	}
}
