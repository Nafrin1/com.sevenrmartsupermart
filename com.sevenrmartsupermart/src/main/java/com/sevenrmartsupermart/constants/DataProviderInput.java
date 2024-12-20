package com.sevenrmartsupermart.constants;

import org.testng.annotations.DataProvider;

import com.sevenrmartsupermart.utilities.ExcelReader;

public class DataProviderInput {
	ExcelReader excelreader = new ExcelReader();

	@DataProvider(name = "NewSubCategory")
	public Object[][] subCategorydetails() {
		excelreader.setExcelFile("SubCategory", "CategoryNames");
		return excelreader.getMultidimentionalData(4, 1);
	}
	
	@DataProvider(name = "NewAdminUser")
	public Object[][] newAdminUserDetails() {
		excelreader.setExcelFile("AdminUser", "UserType");
		return excelreader.getMultidimentionalData(4, 1);
	}

}
