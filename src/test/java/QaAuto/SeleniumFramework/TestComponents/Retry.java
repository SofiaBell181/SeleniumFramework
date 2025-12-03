package QaAuto.SeleniumFramework.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int retryCount  = 0;
	int maxTry = 2;
	@Override
	public boolean retry(ITestResult result) {
		// increment attempts of trying running if test failed 
		// i give 2 attempts for retry
		if(retryCount <maxTry) {
			retryCount ++;
			return true;
		}
		
		return false;
	}

}
