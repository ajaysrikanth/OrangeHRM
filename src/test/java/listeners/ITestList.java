package listeners;

import org.testng.ITestContext;
import org.testng.ITestResult;

import base.BrowserSetup;

public class ITestList extends BrowserSetup implements org.testng.ITestListener {
	
	public void onTestStart(ITestResult result) {

		System.out.println("Test case started is :" + result.getName());
	}

	public void onTestSuccess(ITestResult result) {

		System.out.println("Test case success is :"+ result.getName());
		
	}

	public void onTestFailure(ITestResult result) {

		System.out.println("Test case failure is :"+result.getName());
//		TakesScreenshot ts = (TakesScreenshot)driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		File dest = new File("./screenshot", result.getName()+"screenshot.png");
//		try {
//			FileUtils.copyFile(source, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
}

	public void onTestSkipped(ITestResult result) {

		System.out.println("Test case skipped is :"+result.getName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		System.out.println("Test case success percent is :"+result.getName());
		
	}

	public void onStart(ITestContext context) {

		System.out.println("Test started");
		
	}

	public void onFinish(ITestContext context) {

		System.out.println("Test finished");
		
	}

}
