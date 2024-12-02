package com.sevenrmartsupermart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermart.utilities.GeneralUtility;
import com.sevenrmartsupermart.utilities.PageUtility;

public class AdminUserPage {
	WebDriver driver;

	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//thead//th")
	private List<WebElement> tableColumns;
	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	private WebElement newButton;
	@FindBy(xpath = "(//input[@class='form-control'])[2]")
	private WebElement userNameField;
	@FindBy(xpath = "(//input[@class='form-control'])[3]")
	private WebElement passwordField;
	@FindBy(xpath = "(//select[@class='form-control'])[2]")
	private WebElement userTypeField;
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successAlert;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]")
	private WebElement firstRowOfAdminUserList;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement failureAlert;
	@FindBy(xpath = "(//table[@class='table table-bordered table-hover table-sm']//tbody//td//a[@role='button']//span)[6]")
	private WebElement statusButton;
	@FindBy(xpath = "(//select[@class='form-control'])[1]")
	private WebElement userTypeFieldFromSearch;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private List<WebElement> userNameList;

	public AdminUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<String> getTableColumn() {
		GeneralUtility generalutility = new GeneralUtility(driver);
		List<String> columns = new ArrayList<String>();
		columns = generalutility.getTextOfElements(tableColumns);
		return columns;
	}

	public void clickNewAdminUserButton() {
		newButton.click();
	}

	public void enterUserName(String userName) {
		userNameField.sendKeys(userName);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void selectUserType(String userType) {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(userTypeField, userType);
	}

	public void clickSaveButton() {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.scrollAndClick(saveButton);
	}

	public void createNewAdminUser(String userName, String password, String userType) {

		clickNewAdminUserButton();
		enterUserName(userName);
		enterPassword(password);
		selectUserType(userType);
		clickSaveButton();
	}

	public String getSuccessAlertMessage() {
		PageUtility pageutility = new PageUtility(driver);
		return pageutility.waitAndGetText(successAlert);
	}

	public String returnFirstRowOfTable() {
		return firstRowOfAdminUserList.getText();
	}

	public String getFailureAlert() {
		return failureAlert.getText();
	}

	public void statusChangeButton() {
		statusButton.click();
	}

	public void clickStatusButton(String userName) {
		GeneralUtility generalutility = new GeneralUtility(driver);
		PageUtility pageutility = new PageUtility(driver);
		List<String> adminUser = new ArrayList<String>();
		adminUser = generalutility.getTextOfElements(userNameList);

		int index = 0;
		for (index = 0; index < adminUser.size(); index++) {
			if (userName.equals(adminUser.get(index))) {
				index++;
				break;
			}

		}
		WebElement update = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index
						+ "]//td//a[@class='btn btn-sm btn btn-success btncss']"));
		pageutility.moveToElement(update);
		update.click();
	}

	public String getStatusChange(String userName) {
		GeneralUtility generalutility = new GeneralUtility(driver);
		PageUtility pageutility = new PageUtility(driver);
		List<String> adminUser = new ArrayList<String>();
		adminUser = generalutility.getTextOfElements(userNameList);

		int index = 0;
		for (index = 0; index < adminUser.size(); index++) {
			if (userName.equals(adminUser.get(index))) {
				index++;
				break;
			}

		}
		WebElement status = driver
				.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index
						+ "]//td//a[@role='button']//span"));
		pageutility.moveToElement(status);
		return status.getText();
	}

}
