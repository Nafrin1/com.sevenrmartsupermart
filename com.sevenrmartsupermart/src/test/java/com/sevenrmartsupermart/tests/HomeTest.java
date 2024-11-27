package com.sevenrmartsupermart.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenrmartsupermart.base.Base;
import com.sevenrmartsupermart.pages.HomePage;
import com.sevenrmartsupermart.pages.LoginPage;

public class HomeTest extends Base {
	LoginPage loginpage;
	HomePage homepage;
	SoftAssert softassert=new SoftAssert();

	@Test
	public void verifyHomePageOptions() {
		loginpage = new LoginPage(driver);
		homepage = loginpage.login();
		homepage.clickProfileMenuOnTopRight();
		boolean actualStatusForLogout=homepage.isLogoutDisplayed();
		boolean actualStatusForSettings=homepage.isSettingsDisplayed();
		softassert.assertTrue(actualStatusForLogout);
		softassert.assertTrue(actualStatusForSettings);
	}
	
	@Test
	public void verifyWhetherClickingOnLogoutButtonLogsOutAdminUserFromTheirAccount()
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		homepage.logout();
		String actualTabName=loginpage.getTabName();
		String expectedTabName="Login | 7rmart supermarket";
		boolean actualStatus=loginpage.signInButtonIsDisplayed();
		softassert.assertEquals(actualTabName, expectedTabName);
		softassert.assertTrue(actualStatus);
	}

}
