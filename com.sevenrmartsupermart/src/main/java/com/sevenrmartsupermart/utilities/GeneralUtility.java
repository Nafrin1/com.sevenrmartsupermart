package com.sevenrmartsupermart.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.github.javafaker.Faker;

public class GeneralUtility {
	WebDriver driver;

	public GeneralUtility(WebDriver driver) {
		this.driver = driver;
	}

	public String getAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);

	}

	public String getCssProperty(WebElement element, String property) {
		return element.getCssValue(property);
	}

	public List<String> getTextOfElements(List<WebElement> elements) {
		List<String> data = new ArrayList<String>();

		for (WebElement element : elements) {
			data.add(element.getText());

		}
		return data;
	}

	public static String getRandomName() {
		Faker faker = new Faker();
		return faker.name().firstName();
	}

	public String returnTabName() {
		return driver.getTitle();
	}

}
