package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {

		File src = new File("./Configurations/configuration.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getApplicationURL() {
		String url = pro.getProperty("WEB_URL");
		return url;
	}

	public String getUserName() {
		String userName = pro.getProperty("UserName");
		return userName;
	}

	public String getPassword() {
		String password = pro.getProperty("Password");
		return password;
	}

	public String getPlatformType() {
		String platformType = pro.getProperty("PLATFORM_TYPE");
		return platformType;
	}

	public String getBrowser() {
		String browser = pro.getProperty("BROWSER");
		return browser;
	}
}
