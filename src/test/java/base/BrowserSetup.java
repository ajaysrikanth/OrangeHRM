package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import listeners.WebDriverListener;
import util.Configuration;

public class BrowserSetup {

	public static WebDriver dr;
	
	public static EventFiringWebDriver driver;
	
	public static WebDriverListener eventlistener;
	
	static Configuration config = new Configuration();

	protected static Logger log = Logger.getLogger(BrowserSetup.class);
	
	// Extent reports
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest test;

	public static void testBase() throws IOException {

		log.info("**** Configure chrome Browser ***");

		if ((config.configReader("browser")).equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "./BrowserDrivers/chromedriver.exe");

			dr = new ChromeDriver();

						
		}

		else if ((config.configReader("browser")).equalsIgnoreCase("firefox")) {

			log.info("**** Configure firefox Browser ***");

			System.setProperty("webdriver.gecko.driver", "./BrowserDrivers/geckodriver.exe");

			dr = new FirefoxDriver();

			}
		
		// creating the instance of EventFiring WebDriver
		driver = new EventFiringWebDriver(dr);
		
		// create the instance of WebDriver listener
		eventlistener = new WebDriverListener();		
		driver.register(eventlistener);
		
		// registering event listener 
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		


	}

	@BeforeTest
	public void initiate() throws IOException {
		// BrowserSetup browserSetup = new BrowserSetup();
		// browserSetup.testBase("chrome");

		// set the location of the report
				htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/extentReport.html");
				// set title
				htmlReporter.config().setDocumentTitle("Automation Report");
				// Tile of report
				// set report name
				htmlReporter.config().setReportName("Automation Practice Tests");
				// Name of the report
				htmlReporter.config().setTheme(Theme.DARK);

				extent = new ExtentReports();
				extent.attachReporter(htmlReporter);


				BrowserSetup.testBase();

	}
	
	@BeforeMethod
	 public void login() {
		log.info("Opening URL");

		dr.get("https://practice.automationtesting.in/");

	}
	
	@AfterMethod
	public void logout(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent
			// report
			String screenshotPath = getScreenshot(driver, result.getName());
			System.out.println("ScreenshortPath" + screenshotPath);
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		
		driver.close();
	}

	@AfterTest
	public void tearDown() {
		
		extent.flush();
		
		log.info("**** Closing the Browser ***");
		driver.quit();

	}

	public static String getScreenshot(EventFiringWebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/screenshot/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
