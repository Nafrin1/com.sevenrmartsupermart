package com.sevenrmartsupermart.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		List<String> actualOptions = homepage.getHomePageOptions();
		List<String> expectedOptions = homepage.originalHomePageOptions();
		List<String> processedActualOptions = actualOptions.stream()
		        .map(option -> option.replaceAll("\\d+", "").replaceAll("More info", "").trim())
		        .collect(Collectors.toList());

		 Assert.assertEquals(processedActualOptions, expectedOptions, "Lists do not match!");
	}

}
