package com.sevenrmartsupermart.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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

	public static String getRandomUserName() {
		Faker faker = new Faker();
		return faker.name().username();
	}

	public static String getRandomPassword() {
		Faker faker = new Faker();
		return faker.internet().password(5, 8);
	}

	public String returnTabName() {
		return driver.getTitle();
	}

}
