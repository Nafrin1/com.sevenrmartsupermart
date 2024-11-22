package com.sevenrmartsupermart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermart.utilities.GeneralUtility;
import com.sevenrmartsupermart.utilities.PageUtility;

public class HomePage {
	WebDriver driver;
	
	@FindBy(xpath = "//a[@class='d-block']")
	private WebElement profileName;
	@FindBy(xpath = "//a[@data-toggle='dropdown']")
	private WebElement profileMenu;
	@FindBy(xpath = "(//a[@class='dropdown-item'])[2]")
	private WebElement logoutButton;
	@FindBy(xpath = "(//p[contains(text(),'Sub Category')]//following::a[1])[2]")
	private WebElement subCategory;
	@FindBy(xpath = "//div[@class='row']/div")
	List<WebElement> homePageOptions;
	
	
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getProfileName() {
		return profileName.getText();
	}

	public void clickProfileMenuOnTopRight() {
		profileMenu.click();
	}

	public void logout() {
		clickProfileMenuOnTopRight();
		logoutButton.click();
	}

	public SubCategoryPage callSubCategory() {
		subCategory.click();
		return new SubCategoryPage(driver);
	}

	public String getTabName() {
		return driver.getTitle();
	}

	public List<String> getDashboardOptions() {
		GeneralUtility generalutility = new GeneralUtility(driver);
		List<String> homeMenuList = new ArrayList();
		homeMenuList = generalutility.getTextOfElements(homePageOptions);
		return homeMenuList;
	}

	public List<String> originalDashboardOptions() {
		List<String> options = new ArrayList();
		String option1 = "7356\r\n" + "Admin Users\r\n" + "More info";
		String option1Correct = option1.replace("\r\n", " ");
		String option2 = "0\r\n" + "Dashboard\r\n" + "More info";
		String option2Correct = option2.replace("\r\n", " ");
		String option3 = "26\r\n" + "Category\r\n" + "More info";
		String option3Correct = option3.replace("\r\n", " ");
		String option4 = "547\r\n" + "Sub Category\r\n" + "More info";
		String option4Correct = option4.replace("\r\n", " ");
		String option5 = "1\r\n" + "Manage Contact\r\n" + "More info";
		String option5Correct = option5.replace("\r\n", " ");
		String option6 = " 0\r\n" + "Manage Gift cards &vouchers\r\n" + "More info";
		String option6Correct = option6.replace("\r\n", " ");
		String option7 = "0\r\n" + "Test name\r\n" + "More info";
		String option7Correct = option7.replace("\r\n", " ");
		String option8 = "0\r\n" + "Manage Product\r\n" + "More info";
		String option8Correct = option8.replace("\r\n", " ");
		String option9 = "0\r\n" + "Manage News\r\n" + "More info";
		String option9Correct = option9.replace("\r\n", " ");
		String option10 = " 0\r\n" + "Manage Footer Text\r\n" + "More info";
		String option10Correct = option10.replace("\r\n", " ");
		String option11 = "0\r\n" + "Manage Category\r\n" + "More info";
		String option11Correct = option11.replace("\r\n", " ");

		options.add(option1Correct);
		options.add(option2Correct);
		options.add(option3Correct);
		options.add(option4Correct);
		options.add(option5Correct);
		options.add(option6Correct);
		options.add(option7Correct);
		options.add(option8Correct);
		options.add(option9Correct);
		options.add(option10Correct);
		options.add(option11Correct);

		return options;
	}

}
