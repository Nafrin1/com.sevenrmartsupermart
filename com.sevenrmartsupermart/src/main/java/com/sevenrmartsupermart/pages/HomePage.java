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

	public SubCategoryPage clickSubCategory() {
		subCategory.click();
		return new SubCategoryPage(driver);
	}

	public String getTabName() {
		return driver.getTitle();
	}

	public List<String> getHomePageOptions() {
		GeneralUtility generalutility = new GeneralUtility(driver);
		List<String> homeMenuList = new ArrayList<String>();
		homeMenuList = generalutility.getTextOfElements(homePageOptions);
		return homeMenuList;
	}

	public List<String> originalHomePageOptions() {
		List<String> options = new ArrayList<String>();
		options.add("Admin Users");
		options.add("Dashboard");
		options.add("Category");
		options.add("Sub Category");
		options.add("Manage Contact");
		options.add("Manage Gift cards &vouchers");
		options.add("Test name");
		options.add("Manage Product");
		options.add("Manage News");
		options.add("Manage Footer Text");
		options.add("Manage Category");

		return options;
	}

}
