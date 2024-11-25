package com.sevenrmartsupermart.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermart.constants.Constants;
import com.sevenrmartsupermart.utilities.GeneralUtility;
import com.sevenrmartsupermart.utilities.PageUtility;

public class LoginPage {
	WebDriver driver;
	Properties properties = new Properties();
	
	@FindBy(xpath = "//input[@type='text']")
	private WebElement userNameField;
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordField;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInButton;
	@FindBy(xpath = "//label[@for='remember']")
	private WebElement rememberMeCheckBox;
	@FindBy(xpath = "//a[contains(@href,'https')]//b")
	private WebElement groceryStoreName;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement loginAlert;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		try {
			FileInputStream ip = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterUserName(String userName) {
		userNameField.sendKeys(userName);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickSignInButton() {
		signInButton.click();
	}

	public HomePage login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickSignInButton();
		return new HomePage(driver);

	}
	
	public HomePage login()
	{
		String userName=properties.getProperty("username");
		String password=properties.getProperty("password");
		enterUserName(userName);
		enterPassword(password);
		clickSignInButton();
		return new HomePage(driver);
	}
	
	public void loginWithRememberMeCheckBox()
	{
		String userName=properties.getProperty("username");
		String password=properties.getProperty("password");
		enterUserName(userName);
		enterPassword(password);
		clickRememberMeCheckBox();
		clickSignInButton();
	}
	
	public void clickRememberMeCheckBox()
	{
		rememberMeCheckBox.click();
	}
	
	public boolean returnCheckBoxEnableStatus()
	{
		clickRememberMeCheckBox();
		return rememberMeCheckBox.isEnabled();
	}
	
	public String nameOfGroceryApp()
	{
		return groceryStoreName.getText();
	}
	
	public String getValueAttributeOfUserNameField()
	{
		return userNameField.getAttribute("value");
	}
	
	public String getValueAttributeOfPasswordField()
	{
		return passwordField.getAttribute("value");
	}
	
	public void loginWithoutPassword(String userName) {
		enterUserName(userName);
		clickSignInButton();

	}

	public void loginWithoutUsername(String password) {
		enterUserName(password);
		clickSignInButton();

	}
	
	public boolean alertTextIsDisplayed()
	{
		return loginAlert.isDisplayed();
		}
	
	public String getColorOfElement()
	{
		GeneralUtility generalutility=new GeneralUtility(driver);
		return generalutility.getCssProperty(signInButton, "background-color");
	}
	
	public String getColorOfText()
	{
		GeneralUtility generalutility=new GeneralUtility(driver);
		return generalutility.getCssProperty(signInButton, "color");
	}
	
	public String getFontSize()
	{
		GeneralUtility generalutility=new GeneralUtility(driver);
		return generalutility.getCssProperty(signInButton, "font-size");
	}
	
	public String getFontStyle()
	{		
		GeneralUtility generalutility=new GeneralUtility(driver);
		return generalutility.getCssProperty(signInButton, "font-style");
	}
	
	public void enterPasswordAndClickEnterKey()
	{
		PageUtility pageutility=new PageUtility(driver);
		String password=properties.getProperty("password");
		pageutility.clickEnterKey(passwordField,password);
	}
	
	public void loginUsingEnterKey()
	{
		String userName=properties.getProperty("username");
		enterUserName(userName);
		enterPasswordAndClickEnterKey();
	}
}
