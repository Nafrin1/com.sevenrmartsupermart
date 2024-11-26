package com.sevenrmartsupermart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenrmartsupermart.base.Base;
import com.sevenrmartsupermart.pages.HomePage;
import com.sevenrmartsupermart.pages.LoginPage;
import com.sevenrmartsupermart.utilities.ExcelReader;

public class LoginTest extends Base {
	LoginPage loginpage;
	HomePage homepage;
	SoftAssert softassert = new SoftAssert();
	ExcelReader excelreader = new ExcelReader();

	@Test
	public void verifyAdminUserLogin() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login("admin", "admin");
		String actualProfileName = homepage.getProfileName();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName);

	}

	@Test
	public void verifyRememberMeCheckboxIsEnabled() {
		loginpage = new LoginPage(driver);
		boolean actualStatus = loginpage.returnCheckBoxEnableStatus();
		Assert.assertTrue(actualStatus);
	}

	@Test
	public void checkGroceryAppName() {
		loginpage = new LoginPage(driver);
		String actualName = loginpage.nameOfGroceryApp();
		String expectedName = "7rmart supermarket";
		Assert.assertEquals(actualName, expectedName);
	}

	@Test(groups = { "Sanity", "Smoke" })
	public void verifyLoginDetailsAreSavedAfterClickingRememberMeCheckBox() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.loginWithRememberMeCheckBox();
		homepage.logout();

		String actualUserNameValue = loginpage.getValueAttributeOfUserNameField();
		String actualPasswordValue = loginpage.getValueAttributeOfPasswordField();
		String expectedUserNameValue = "admin";
		String expectedPasswordValue = "admin";

		softassert.assertEquals(actualUserNameValue, expectedUserNameValue);
		softassert.assertEquals(actualPasswordValue, expectedPasswordValue);
		softassert.assertAll();
	}

	@Test(retryAnalyzer = com.sevenrmartsupermart.listeners.RetryAnalyzer.class)
	public void verifyLoginIsNotAllowedWithoutPassword() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.loginWithoutPassword("admin");
		String actualTabName = homepage.getTabName();
		String expectedTabName = "Login | 7rmart supermarket";
		Assert.assertEquals(actualTabName, expectedTabName, "Login is allowed");
	}

	@Test
	public void verifyLoginIsNotAllowedWithoutUsername() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.loginWithoutUsername("admin");
		String actualTabName = homepage.getTabName();
		String expectedTabName = "Login | 7rmart supermarket";
		Assert.assertEquals(actualTabName, expectedTabName, "Login is allowed");
	}

	@Test
	public void verifyAlertMessageIsShownWithWrongPassword() {
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "abcd");
		boolean actualAlertstatus = loginpage.alertTextIsDisplayed();
		Assert.assertEquals(actualAlertstatus, true);
	}

	@Test
	public void verifyAlertMessageIsShownWithWrongUsername() {
		loginpage = new LoginPage(driver);
		loginpage.login("rftg", "admin");
		boolean actualAlertstatus = loginpage.alertTextIsDisplayed();
		Assert.assertEquals(actualAlertstatus, true);
	}

	@Test
	public void verifyAlertMessageIsShownWithWrongUsernameAndPassword() {
		loginpage = new LoginPage(driver);
		excelreader.setExcelFile("LoginPageData", "SignInData");
		String userNameData = excelreader.getCellData(3, 0);
		String passwordData = excelreader.getCellData(3, 1);
		loginpage.login(userNameData, passwordData);
		boolean actualAlertstatus = loginpage.alertTextIsDisplayed();
		Assert.assertEquals(actualAlertstatus, true);
	}

	@Test
	public void verifyCSSAttributesofSignInButton() {
		loginpage = new LoginPage(driver);
		String actualColor = loginpage.getColorOfElement();
		String actualTextColor = loginpage.getColorOfText();
		String actualFontSize = loginpage.getFontSize();
		String actualFontStyle = loginpage.getFontStyle();
		String expectedColor = "rgba(52, 58, 64, 1)";
		String expectedTextColor = "rgba(255, 255, 255, 1)";
		String expectedFontSize = "16px";
		String expectedFontStyle = "normal";
		softassert.assertEquals(actualColor, expectedColor);
		softassert.assertEquals(actualTextColor, expectedTextColor);
		softassert.assertEquals(actualFontSize, expectedFontSize);
		softassert.assertEquals(actualFontStyle, expectedFontStyle);
		softassert.assertAll();
	}

	@Test
	public void verifyLoginIsAllowedUsingEnterKeyInKeyboard() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.loginUsingEnterKey();
		String actualProfileName = homepage.getProfileName();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName);
	}

}
