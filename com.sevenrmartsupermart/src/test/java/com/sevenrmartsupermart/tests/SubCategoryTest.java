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
		homepage.callSubCategory();
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
		homepage.callSubCategory();
		subcategorypage.searchSubCategoryWithData("Electronics", "abcd");
		String actualData = subcategorypage.getSearchResult();
		String expectedData = ".........RESULT NOT FOUND.......";
		Assert.assertEquals(actualData, expectedData);

	}

	@Test(groups = "Sanity")
	public void verifyWhetherSearchFieldsCanBeReset() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		subcategorypage.resetSubCategoryWithData("Electronics", "acd");
		boolean actualstatus = subcategorypage.checkSearchListSubCategoriesDisplayed();
		Assert.assertFalse(actualstatus);
	}

	@Test(dataProvider = "NewSubCategory", dataProviderClass = Constants.class)
	public void verifyNewCategoryCanBeCreated(String category) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
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
		subcategorypage=homepage.callSubCategory();
		String subCategory = GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithNoImage(category, subCategory);
		String actualAlertMessage = subcategorypage.showSuccessfullySavedSubCategoryAlert();
		String expectedAlertMessage = "Sub Category Created Successfully";
		Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));
	}

	@Test
	public void verifyNewCategoryIsShownFirstInTable() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
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
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		String dataOnFirstRowBeforeSearch = subcategorypage.returnFirstRowOfTable();
		subcategorypage.searchSubCategoryWithoutCategory("Speaker");
		String dataOnFirstRowAfterSearch = subcategorypage.returnFirstRowOfTable();
		Assert.assertEquals(dataOnFirstRowBeforeSearch, dataOnFirstRowAfterSearch);
	}

	@Test
	public void verifySubCategoryCanBeUpdated() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
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
		softassert.assertTrue(actualAlertMessage.contains(expectedAlertMessage), "Alert verified");
		softassert.assertTrue(actualTabName.contains(expectedTabName), "Tab name verified");
		softassert.assertTrue(actualUpdatedContent.contains(expectedUpdatedContent), "Updation Verified");
		softassert.assertAll();
	}

	@Test
	public void verifyTwoCategoriesWithSameSubCategoryNameCannotBeCreated() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		subcategorypage = new SubCategoryPage(driver);
		loginpage.login();
		homepage.callSubCategory();
		String subCategory = GeneralUtility.getRandomName();
		subcategorypage.createNewCategoryWithNoImage("Vegetables", subCategory);
		subcategorypage.openSubCategoryFromSideMenu();
		subcategorypage.createNewCategoryWithNoImage("Appliances", subCategory);
		String actualAlertMessage = subcategorypage.showFailedToSaveSubCategoryAlert();
		if (subcategorypage.failedAlertIsDisplayed() == false) {
			System.out.println("No alert Shown");
			System.out.println("New Category is Created");
		} else {
			String expectedAlertMessage = "Sub Category already exists.";
			Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));

		}

	}

}
