package com.ts.commons;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TSRetry  implements IRetryAnalyzer{

	private int retryCount = 0;
	private final int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {		
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}
}
