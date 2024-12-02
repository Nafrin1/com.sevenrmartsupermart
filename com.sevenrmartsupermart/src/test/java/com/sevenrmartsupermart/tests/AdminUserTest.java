package com.sevenrmartsupermart.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenrmartsupermart.base.Base;
import com.sevenrmartsupermart.constants.DataProviderInput;
import com.sevenrmartsupermart.pages.AdminUserPage;
import com.sevenrmartsupermart.pages.HomePage;
import com.sevenrmartsupermart.pages.LoginPage;
import com.sevenrmartsupermart.utilities.GeneralUtility;

public class AdminUserTest extends Base {
	LoginPage loginpage;
	HomePage homepage;
	AdminUserPage adminuserpage;
	SoftAssert softassert = new SoftAssert();

	@Test
	public void verifyWhetherAdminUserListHasAllRequiredColoumns() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		homepage.clickAdminUser();
		List<String> actualColumnList = adminuserpage.getTableColumn();
		List<String> expectedColumnList = new ArrayList<String>();
		expectedColumnList.add("Username");
		expectedColumnList.add("Usertype");
		expectedColumnList.add("Status");
		expectedColumnList.add("Password");
		expectedColumnList.add("Action");
		Assert.assertEquals(actualColumnList, expectedColumnList);
	}

	@Test(dataProvider = "NewAdminUser", dataProviderClass = DataProviderInput.class)
	public void verifyThatNewAdminUsersWithDifferentUserTypeCanBeAdded(String userType) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		homepage.clickAdminUser();
		String password = GeneralUtility.getRandomPassword();
		adminuserpage.createNewAdminUser("Peppa", password, userType);
		String actualAlertMessage = adminuserpage.getSuccessAlertMessage();
		String expectedAlertMessage = "User Created Successfully";
		String actualFirstRow = adminuserpage.returnFirstRowOfTable();
		String expectedFirstRow = "Peppa " + userType;
		softassert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));
		softassert.assertTrue(actualFirstRow.contains(expectedFirstRow));
		softassert.assertAll();
	}

	@Test(dataProvider = "NewAdminUser", dataProviderClass = DataProviderInput.class)
	public void verifyNewAdminUserWithinExistingUserNameWithSameUserTypeCannotBeCreated(String userType) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		homepage.clickAdminUser();
		adminuserpage.createNewAdminUser("Peppa", "Pig4567", userType);
		adminuserpage.createNewAdminUser("Peppa", "89076", userType);
		String actualAlertMessage = adminuserpage.getFailureAlert();
		String expectedAlertMessage = "Username already exists.";
		Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));

	}

	@Test(retryAnalyzer = com.sevenrmartsupermart.listeners.RetryAnalyzer.class)
	public void verifyStatusChangeIsPossibleThroughButton()
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		adminuserpage = new AdminUserPage(driver);
		loginpage.login();
		homepage.clickAdminUser();
		String userName=GeneralUtility.getRandomName();
		adminuserpage.createNewAdminUser(userName, "4567", "Staff");
		adminuserpage.clickStatusButton(userName);
		String actualAlertMessage=adminuserpage.getSuccessAlertMessage();
		String expectedAlertMessage="User Status Changed Successfully";
		String actualStatus=adminuserpage.getStatusChange(userName);
		String expectedStatus="Inactive";
		softassert.assertTrue(actualAlertMessage.contains(expectedAlertMessage),"Status Change was not successfull");
		softassert.assertTrue(actualStatus.contains(expectedStatus),"Does not show correct Status");
		softassert.assertAll();
	}
}
