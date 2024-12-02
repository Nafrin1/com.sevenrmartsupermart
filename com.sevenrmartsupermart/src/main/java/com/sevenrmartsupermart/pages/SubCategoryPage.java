package com.sevenrmartsupermart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermart.constants.Constants;
import com.sevenrmartsupermart.utilities.GeneralUtility;
import com.sevenrmartsupermart.utilities.PageUtility;
import com.sevenrmartsupermart.utilities.WaitUtility;

public class SubCategoryPage {
	WebDriver driver;
	
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButtonMain;
	@FindBy(xpath = "//select[@class='form-control selectpicker']")
	private WebElement categoryDropDown;
	@FindBy(xpath = "//input[@placeholder='Sub Category']")
	private WebElement subcategoryField;
	@FindBy(xpath = "//button[@class='btn btn-danger btn-fix']")
	private WebElement searchButton;
	@FindBy(xpath = "//input[@placeholder='Enter the Subcategory']")
	private WebElement subCategoryFieldUpdate;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr")
	private WebElement tableResult;
	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	private WebElement newCategoryButton;
	@FindBy(xpath = "//a[@class='btn btn-default btn-fix']")
	private WebElement resetButton;
	@FindBy(xpath = "(//div[@class='card-body'])[1]")
	private WebElement searchListSubCategories;
	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	private WebElement newSubCategoryButton;
	@FindBy(xpath = "//input[@type='file']")
	private WebElement image;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successAlert;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement failedAlert;
	@FindBy(xpath = "//input[@placeholder='Enter the Subcategory']")
	private WebElement newSubCategoryField;
	@FindBy(xpath = "//p[text()='Sub Category']")
	private WebElement subCategoryOptionFromSideMenu;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]")
	private WebElement firstRowOfSubCategoryList;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private List<WebElement> subCategoryList;
	@FindBy(xpath = "//button[text()='Update']")
	private WebElement updateButton;
	

	public SubCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnSearchButtonMain() {
		searchButtonMain.click();
	}

	public void searchSubCategoryWithData(String category, String subCategory) {
		clickOnSearchButtonMain();
		selectCategory(category);
		enterSubCategory(subCategory);
		searchButton.click();

	}
	
	public void searchSubCategoryWithoutCategory(String subCategory) {
		clickOnSearchButtonMain();
		enterSubCategory(subCategory);
		searchButton.click();

	}

	public void selectCategory(String category) {
		PageUtility pageutility = new PageUtility(driver);
		pageutility.select_ByVisibleText(categoryDropDown, category);
	}

	public void enterSubCategory(String subCategory) {
		subcategoryField.sendKeys(subCategory);
	}

	public String getSearchResult() {
		return tableResult.getText();

	}
	
	public void clickNewCategoryButton()
	{
		newCategoryButton.click();
	}
	
	public void getTabTitle()
	{
		driver.getTitle();
	}
	
	public void enterIntoNewCategoryPage()
	{
		clickNewCategoryButton();
		getTabTitle();
	}
	
	public void resetSubCategoryWithData(String category, String subCategory)
	{
		clickOnSearchButtonMain();
		selectCategory(category);
		enterSubCategory(subCategory);
		resetButton.click();
		
	}
	
	public boolean checkSearchListSubCategoriesDisplayed()
	{
		return categoryDropDown.isDisplayed();
	}
	
	public void clickNewSubCategoryButton()
	{
		newSubCategoryButton.click();
	}
	
	public void enterSubCategoryForNewCategory(String subCategory) {
		newSubCategoryField.sendKeys(subCategory);
	}
	
	public void uploadImage()
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.uploadFile(image,Constants.SAMPLE_IMAGE_PATH);
	}
	
	public void clickSaveButton()
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.moveToElement(saveButton);
		saveButton.click();
	}
	
	public void createNewCategoryWithImage(String category,String subCategory)
	{
		clickNewSubCategoryButton();
		selectCategory(category);
		enterSubCategoryForNewCategory(subCategory);
		uploadImage();
		clickSaveButton();
	}
	
	public void createNewCategoryWithNoImage(String category,String subCategory)
	{
		clickNewSubCategoryButton();
		selectCategory(category);
		enterSubCategoryForNewCategory(subCategory);
		clickSaveButton();
	}
	
	public String showSuccessfullySavedSubCategoryAlert()
	{
		return successAlert.getText();
	}
	
	public String showFailedToSaveSubCategoryAlert()
	{
		return failedAlert.getText();
	}
	
	public boolean failedAlertIsDisplayed()
	{
		PageUtility pageutility=new PageUtility(driver);
		return pageutility.isDisplayed(failedAlert);
	}
	
	
	public void openSubCategoryFromSideMenu()
	{
		subCategoryOptionFromSideMenu.click();
	}
	
	public String returnFirstRowOfTable()
	{
		return firstRowOfSubCategoryList.getText();
	}
	
	public void clickUpdateButton(String subCategoryName)
	{
		GeneralUtility generalutility=new GeneralUtility(driver);
		PageUtility pageutility=new PageUtility(driver);
		List<String> subCategories=new ArrayList<String>();
		subCategories=generalutility.getTextOfElements(subCategoryList);
		
		int index=0;
		for(index=0;index<subCategories.size();index++)
		{
			if(subCategoryName.equals(subCategories.get(index)))
			{
				index++;
				break;
			}
			
		}
		WebElement update=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+index+"]//a[@class='btn btn-sm btn btn-primary btncss']"));
		pageutility.moveToElement(update);
		update.click();
	}
	
	public String getTableContents(String subCategoryName)
	{
		GeneralUtility generalutility=new GeneralUtility(driver);
		List<String> subCategories=new ArrayList<String>();
		subCategories=generalutility.getTextOfElements(subCategoryList);
		
		int index=0;
		for(index=0;index<subCategories.size();index++)
		{
			if(subCategoryName.equals(subCategories.get(index)))
			{
				index++;
				break;
			}
			
		}
		WebElement rowContent=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+index+"]"));
		return rowContent.getText();
		
		
	}
	
	public void ClickUpdateAfterUpdation()
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.moveToElement(updateButton);
		updateButton.click();
	}
	
	public String showCurrentTabName()
	{
		GeneralUtility generalutility=new GeneralUtility(driver);
		return generalutility.returnTabName();
	}
	
	public void clearSubCategory()
	{
		PageUtility pageutility=new PageUtility(driver);
		pageutility.clearField(subCategoryFieldUpdate);
	}
	
	public void enterSubCategoryToUpdate(String subCategory) {
		subCategoryFieldUpdate.sendKeys(subCategory);
	}
	
	public boolean isSubCategoryNotPresent(String categoryName, String subCategoryName) {

		String xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[td[contains(text(),'"+subCategoryName+"')] and td[contains(text(),'"+categoryName+"')]]";
	    List<WebElement> matchingRows = driver.findElements(By.xpath(xpath));
	    return matchingRows.isEmpty();
	}

	public void clickDeleteButton(String subCategoryName)
	{
			GeneralUtility generalutility=new GeneralUtility(driver);
			PageUtility pageutility=new PageUtility(driver);
			List<String> subCategories=new ArrayList<String>();
			subCategories=generalutility.getTextOfElements(subCategoryList);
			
			int index=0;
			for(index=0;index<subCategories.size();index++)
			{
				if(subCategoryName.equals(subCategories.get(index)))
				{
					index++;
					break;
				}
				
			}
			WebElement delete=driver.findElement(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr["+index+"]//a[@class='btn btn-sm btn btn-danger btncss']"));
			pageutility.moveToElement(delete);
			delete.click();
	}
	
	public String returnAlertMessageForDeletion(String subCategoryName)
	{
		WaitUtility waitutility=new WaitUtility(driver);
		clickDeleteButton(subCategoryName);
		driver.switchTo().alert().accept(); 
		waitutility.waitForElementToBeVisible(successAlert, 20);
		return successAlert.getText();
	}

}
