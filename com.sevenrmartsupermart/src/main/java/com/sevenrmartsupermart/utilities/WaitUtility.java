package com.sevenrmartsupermart.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;
	WebDriverWait wait;
	
	public WaitUtility(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void waitForElementToBeClickable(WebElement element, long duration)
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
