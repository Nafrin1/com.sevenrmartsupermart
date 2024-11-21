package com.sevenrmartsupermart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermart.base.Base;
import com.sevenrmartsupermart.constants.Constants;
import com.sevenrmartsupermart.constants.DataProviderInput;
import com.sevenrmartsupermart.pages.HomePage;
import com.sevenrmartsupermart.pages.LoginPage;
import com.sevenrmartsupermart.pages.SubCategoryPage;
import com.sevenrmartsupermart.utilities.ExcelReader;
import com.sevenrmartsupermart.utilities.GeneralUtility;

public class SubCategoryTest extends Base {
	LoginPage loginpage;
	HomePage homepage;
	SubCategoryPage subcategorypage;
	ExcelReader excelreader=new ExcelReader();

	@Test
	public void verifySubCategoryListUsingSearchButton() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		String product=GeneralUtility.getRandomName();
		subcategorypage.searchSubCategoryWithData("Electronics", product);
		String actualData = subcategorypage.getSearchResult();
		String expectedData = "Speaker Electronics Active";
		Assert.assertEquals(actualData, expectedData);

	}
	
	@Test(groups = "Smoke")
	public void verifySubCategoryListUsingSearchButtonWithWrongData() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		subcategorypage.searchSubCategoryWithData("Electronics", "abcd");
		String actualData = subcategorypage.getSearchResult();
		String expectedData = ".........RESULT NOT FOUND.......";
		Assert.assertEquals(actualData, expectedData);

	}
	
	@Test(groups="Sanity")
	public void verifyWhetherSearchFieldsCanBeReset()
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		subcategorypage.resetSubCategoryWithData("Electronics", "acd");
		boolean actualstatus=subcategorypage.checkSearchListSubCategoriesDisplayed();
		Assert.assertFalse(actualstatus);
	}
	
	@Test(dataProvider="NewSubCategory", dataProviderClass = Constants.class)
	public void verifyNewCategoryCanBeCreated(String category)
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		String subCategory=GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithImage(category,subCategory);
		boolean actualAlertStatus=subcategorypage.showSuccessfullySavedSubCategoryAlert();
		Assert.assertTrue(actualAlertStatus);
	}
	
	@Test(dataProvider="NewSubCategory", dataProviderClass = DataProviderInput.class)
	public void verifyNewCategoryCanBeCreatedWithoutImage(String category)
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		String subCategory=GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithNoImage(category,subCategory);
		boolean actualAlertStatus=subcategorypage.showSuccessfullySavedSubCategoryAlert();
		Assert.assertTrue(actualAlertStatus);
	}
	
	@Test
	public void verifyNewCategoryIsShownFirstInTable()
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		String subCategory=GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithNoImage("Vegetables",subCategory);
		subcategorypage.openSubCategoryFromSideMenu();
		String actualFirstRow=subcategorypage.returnFirstRowOfTable();
		String expectedFirstRow=subCategory+" Vegetables Active";
		Assert.assertEquals(actualFirstRow, expectedFirstRow);
	}
	
	@Test
	public void verifySubCategoryCannotBeSearchedWithoutSelectingCategory()
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		String dataOnFirstRowBeforeSearch=subcategorypage.returnFirstRowOfTable();
		subcategorypage.searchSubCategoryWithoutCategory("Speaker");
		String dataOnFirstRowAfterSearch=subcategorypage.returnFirstRowOfTable();
		Assert.assertEquals(dataOnFirstRowBeforeSearch, dataOnFirstRowAfterSearch);
	}
	

}
