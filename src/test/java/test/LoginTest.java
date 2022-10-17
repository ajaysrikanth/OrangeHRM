package test;

import java.io.IOException;

import org.testng.annotations.Test;
import base.BrowserSetup;
import pages.LoginPage;
import util.ExcelReader;


public class LoginTest extends BrowserSetup{
	
	LoginPage lp;
	
	ExcelReader ex  = new ExcelReader();
	
	@Test(priority = 0)
	public void verifyLogin () throws InterruptedException, IOException {
	
		test = extent.createTest("verifyLogin");
		
		lp = new LoginPage(driver); //extends BrowserSetup acquires driver configuration
		
		lp.myAccount();
		lp.userName(ex.reader("Sheet1", 0, 0));
		lp.password(ex.reader("Sheet1", 1, 0));
		lp.register();				
	}
	

}
