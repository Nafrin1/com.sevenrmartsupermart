package com.sevenrmartsupermart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	int retryLimit = 3;

	public boolean retry(ITestResult itestresult) {
		if (itestresult.getStatus() == ITestResult.FAILURE && counter < retryLimit) {
			counter++;
			return true;
		} else {
			return false;
		}

	}

}
