package com.sevenrmartsupermart.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.sevenrmartsupermart.constants.Constants;
import com.sevenrmartsupermart.utilities.ScreenShotCapture;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	Properties properties = new Properties();
	ScreenShotCapture screenshotcapture = new ScreenShotCapture();

	public Base() {
		try {
			FileInputStream ip = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** InitialiseBrowser */
	public void initialise(String browser, String url) {
		if (browser.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
		driver.manage().deleteAllCookies();
	}

	@Parameters("browser")
	@BeforeMethod(enabled = false)
	public void launchBrowser(String browser) {

		String url = properties.getProperty("url");
		initialise(browser, url);
	}
	
	@BeforeMethod(enabled = true,alwaysRun = true)
	public void launchBrowser() {
		String browser = properties.getProperty("browser");
		String url = properties.getProperty("url");
		initialise(browser, url);
	}

	@AfterMethod(alwaysRun = true)
	public void terminateSession(ITestResult itestresult) {
		if (itestresult.getStatus() == ITestResult.FAILURE) {
			screenshotcapture.takeScreenshot(driver, itestresult.getName());
		}
		driver.close();

	}
}
