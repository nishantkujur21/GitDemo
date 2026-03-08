package nishantSelenium.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailTest implements IRetryAnalyzer {
	
	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}


}
