package com.sevenrmartsupermart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
	ExcelReader excelreader = new ExcelReader();
	SoftAssert softassert = new SoftAssert();

	@Test
	public void verifySubCategoryListUsingSearchButton() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.clickSubCategory();
		subcategorypage.searchSubCategoryWithData("Electronics", "Speaker");
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
		homepage.clickSubCategory();
		subcategorypage.searchSubCategoryWithData("Electronics", "abcd");
		String actualData = subcategorypage.getSearchResult();
		String expectedData = ".........RESULT NOT FOUND.......";
		Assert.assertEquals(actualData, expectedData);

	}

	@Test(groups = "Sanity")
	public void verifyWhetherSearchFieldCanBeReset() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.clickSubCategory();
		subcategorypage.resetSubCategoryWithData("Electronics", "acd");
		boolean actualstatus = subcategorypage.checkSearchListSubCategoriesDisplayed();
		Assert.assertFalse(actualstatus);
	}

	@Test(dataProvider = "NewSubCategory", dataProviderClass = DataProviderInput.class)
	public void verifyNewCategoryCanBeCreatedWithImage(String category) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.clickSubCategory();
		String subCategory = GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithImage(category, subCategory);
		String actualAlertMessage = subcategorypage.showSuccessfullySavedSubCategoryAlert();
		String expectedAlertMessage = "Sub Category Created Successfully";
		Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));
	}

	@Test(dataProvider = "NewSubCategory", dataProviderClass = DataProviderInput.class)
	public void verifyNewCategoryCanBeCreatedWithoutImage(String category) {
		loginpage = new LoginPage(driver);
		homepage=loginpage.login();
		subcategorypage=homepage.clickSubCategory();
		String subCategory = GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithNoImage(category, subCategory);
		String actualAlertMessage = subcategorypage.showSuccessfullySavedSubCategoryAlert();
		String expectedAlertMessage = "Sub Category Created Successfully";
		Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));
	}

	@Test
	public void verifyNewCategoryIsShownFirstInTable() {
		loginpage = new LoginPage(driver);
		homepage = loginpage.login();
		subcategorypage = homepage.clickSubCategory();
		String subCategory = GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithNoImage("Vegetables", subCategory);
		subcategorypage.openSubCategoryFromSideMenu();
		String actualFirstRow = subcategorypage.returnFirstRowOfTable();
		String expectedFirstRow = subCategory + " Vegetables Active";
		Assert.assertEquals(actualFirstRow, expectedFirstRow);
	}

	@Test
	public void verifySubCategoryCannotBeSearchedWithoutSelectingCategory() {
		loginpage = new LoginPage(driver);
		homepage = loginpage.login();
		subcategorypage = homepage.clickSubCategory();
		String dataOnFirstRowBeforeSearch = subcategorypage.returnFirstRowOfTable();
		subcategorypage.searchSubCategoryWithoutCategory("Speaker");
		String dataOnFirstRowAfterSearch = subcategorypage.returnFirstRowOfTable();
		Assert.assertEquals(dataOnFirstRowBeforeSearch, dataOnFirstRowAfterSearch);
	}

	@Test
	public void verifySubCategoryCanBeUpdated() {
		loginpage = new LoginPage(driver);
		homepage = loginpage.login();
		subcategorypage = homepage.clickSubCategory();
		subcategorypage.createNewCategoryWithNoImage("Ergonomic Iron Hat", "tea");
		subcategorypage.openSubCategoryFromSideMenu();
		subcategorypage.clickUpdateButton("tea");
		subcategorypage.clearSubCategory();
		String newSubCategory = GeneralUtility.getRandomName();
		subcategorypage.enterSubCategoryToUpdate(newSubCategory);
		subcategorypage.ClickUpdateAfterUpdation();
		String actualAlertMessage = subcategorypage.showSuccessfullySavedSubCategoryAlert();
		String expectedAlertMessage = "Sub Category Updated Successfully";
		String actualTabName = subcategorypage.showCurrentTabName();
		String expectedTabName = "List Sub Categories";
		String actualUpdatedContent = subcategorypage.getTableContents(newSubCategory);
		String expectedUpdatedContent = newSubCategory + " Ergonomic Iron Hat";
		softassert.assertTrue(actualAlertMessage.contains(expectedAlertMessage), "Alert verification failed");
		softassert.assertTrue(actualTabName.contains(expectedTabName), "Tab name verification failed");
		softassert.assertTrue(actualUpdatedContent.contains(expectedUpdatedContent), "Updation Verification failed");
		softassert.assertAll();
	}

	@Test
	public void verifyTwoCategoriesWithSameSubCategoryNameCannotBeCreated() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.clickSubCategory();
		String subCategory = GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithNoImage("Vegetables", subCategory);
		subcategorypage.openSubCategoryFromSideMenu();
		subcategorypage.createNewCategoryWithNoImage("Appliances", subCategory);
		String actualAlertMessage = subcategorypage.showFailedToSaveSubCategoryAlert();
		String expectedAlertMessage = "Sub Category already exists.";
		softassert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));
	}
	
	@Test
	public void verifyIfCategoryCanBeDeletedFromSubCategoryList()
	{
			loginpage = new LoginPage(driver);
			homepage = new HomePage(driver);
			subcategorypage = new SubCategoryPage(driver);
			loginpage.login();
			homepage.clickSubCategory();
			String subCategory = GeneralUtility.getRandomName();
			subcategorypage.createNewCategoryWithNoImage("Grocery", subCategory);
			subcategorypage.openSubCategoryFromSideMenu();
			String actualAlertMessage=subcategorypage.returnAlertMessageForDeletion(subCategory);
			String expectedAlertMessage="Sub Category Deleted Successfully";
			boolean actualDeletionStatus = subcategorypage.isSubCategoryNotPresent("Grocery", subCategory);
			softassert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));
			softassert.assertTrue(actualDeletionStatus, "SubCategory has not been deleted as expected!");
			softassert.assertAll();
	}

}
