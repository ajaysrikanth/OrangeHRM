package pages;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginPage {
	
	EventFiringWebDriver driver;
	
	Logger log = Logger.getLogger(LoginPage.class);
	
	@FindBy(xpath = "//a[text()='My Account']")
	WebElement MyAccount; 
	
	@FindBy(xpath = "//input[@id='reg_email']")
	WebElement UserName;
	
	@FindBy(xpath = "//input[@id='reg_password']")
	WebElement Password;
	
	@FindBy(xpath ="//input[@value='Register']")
	WebElement Register;
	
	public LoginPage(EventFiringWebDriver driver) throws FileNotFoundException {
		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
		
	}
		
	
	public void myAccount (){		
		MyAccount.click();
		
		log.info("MyAccount is clicked");
	}
	
	public void userName(String userName) {
		UserName.clear();
		UserName.sendKeys(userName);
		}
	
	public void password(String password) {
		Password.clear();
		Password.sendKeys(password);
	}
	
	public void register() {
		Register.click();
	}
}
