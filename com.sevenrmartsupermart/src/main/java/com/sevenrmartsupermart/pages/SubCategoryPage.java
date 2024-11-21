package com.sevenrmartsupermart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermart.utilities.PageUtility;

public class SubCategoryPage {
	WebDriver driver;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	WebElement searchButtonMain;
	@FindBy(xpath = "//select[@class='form-control selectpicker']")
	WebElement categoryDropDown;
	@FindBy(xpath = "//input[@placeholder='Sub Category']")
	WebElement subcategoryField;
	@FindBy(xpath = "//button[@class='btn btn-danger btn-fix']")
	WebElement searchButton;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr")
	WebElement tableResult;
	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	WebElement newCategoryButton;
	@FindBy(xpath = "//a[@class='btn btn-default btn-fix']")
	WebElement resetButton;
	@FindBy(xpath = "(//div[@class='card-body'])[1]")
	WebElement searchListSubCategories;
	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	WebElement newSubCategoryButton;
	@FindBy(xpath = "//input[@type='file']")
	WebElement image;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement newSubCategorySaveAlert;
	@FindBy(xpath = "//input[@placeholder='Enter the Subcategory']")
	WebElement newSubCategoryField;
	@FindBy(xpath = "//p[text()='Sub Category']")
	WebElement subCategoryOptionFromSideMenu;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[1]")
	WebElement firstRowOfSubCategoryList;


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
		String path="C:\\Users\\hp\\Downloads\\spongebob.jpg";
		pageutility.uploadFile(image, path);
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
	
	public boolean showSuccessfullySavedSubCategoryAlert()
	{
		PageUtility pageutility=new PageUtility(driver);
		return pageutility.isDisplayed(newSubCategorySaveAlert);
	}
	
	public void openSubCategoryFromSideMenu()
	{
		subCategoryOptionFromSideMenu.click();
	}
	
	public String returnFirstRowOfTable()
	{
		return firstRowOfSubCategoryList.getText();
	}

}
