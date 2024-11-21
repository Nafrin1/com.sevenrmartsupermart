package com.sevenrmartsupermart.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermart.base.Base;
import com.sevenrmartsupermart.pages.HomePage;
import com.sevenrmartsupermart.pages.LoginPage;

public class HomeTest extends Base {
	LoginPage loginpage;
	HomePage homepage;

	@Test
	public void verifyHomePageOptions() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		List<String> actualoptions = homepage.getDashboardOptions();
		List<String> expectedOptions = new ArrayList();
		expectedOptions = homepage.originalDashboardOptions();
		Assert.assertEquals(actualoptions, expectedOptions);
	}

	@Test
	public void verifySideMenuBarCanBeHidden() {

		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		homepage.clickSideMenuButton();
		String actualwidth = homepage.getSideMenuAttributeValue("style");
		String expectedwidth = "overflow-y: scroll;";
		Assert.assertEquals(actualwidth, expectedwidth);

	}
	
	@Test
	public void verifySideMenuCanBeExpandedByHoveringOver()
	{
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		homepage.hoverOverCollapsedSideMenu();
		String actualwidth = homepage.getSideMenuAttributeValue("style");
		String expectedwidth = "os-scrollbar os-scrollbar-horizontal os-scrollbar-unusable ";
		Assert.assertEquals(actualwidth, expectedwidth);
	}

}
