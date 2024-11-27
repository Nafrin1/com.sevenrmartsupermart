package com.sevenrmartsupermart.pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermart.utilities.PageUtility;


public class HomePage {
	WebDriver driver;
	
	@FindBy(xpath = "//a[@class='d-block']")
	private WebElement profileName;
	@FindBy(xpath = "//a[@data-toggle='dropdown']")
	private WebElement profileMenu;
	@FindBy(xpath = "(//a[@class='dropdown-item'])[2]")
	private WebElement logoutButton;
	@FindBy(xpath = "(//a[@class='dropdown-item'])[1]")
	private WebElement settingsButton;
	@FindBy(xpath = "(//p[contains(text(),'Sub Category')]//following::a[1])[2]")
	private WebElement subCategory;
	@FindBy(xpath = "//a[@class='dropdown-item']")
	List<WebElement> profileOptions;
	@FindBy(xpath = "(//ul[@class='nav nav-pills nav-sidebar flex-column']//p)[1]")
	private WebElement sideMenuDashboard;
	

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

	public boolean isLogoutDisplayed()
	{
		PageUtility pageutility=new PageUtility(driver);
		return pageutility.isDisplayed(logoutButton);
	}
	
	public boolean isSettingsDisplayed()
	{
		PageUtility pageutility=new PageUtility(driver);
		return pageutility.isDisplayed(settingsButton);
	}
	

}
