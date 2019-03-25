package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBase {
	public static WebDriver dr;
	InputStream inputFile = null;
	Properties configFile = new Properties();
	
	
	public void setUpChromeBrowser() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver\\chromedriver.exe");
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		System.out.println("Chrome driver initialized");
	}

	public void setUpFirefoxBrowser() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver\\geckodriver.exe");
		dr = new FirefoxDriver();
		dr.manage().window().maximize();
		System.out.println("Firefox driver initialized");
	}
	
	public void assignValuesToProjectConstants() {
		ProjectConstants.welcomeMessage = configFile.getProperty("welcomeMessage");
		ProjectConstants.bigWin = configFile.getProperty("bigWin");
		ProjectConstants.noWin = configFile.getProperty("noWin");
		ProjectConstants.smallWin = configFile.getProperty("smallWin");
		ProjectConstants.noOfTimesGamesChecked = Integer.parseInt(configFile.getProperty("noOfTimesGameToBeChecked"));
	}
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void initializeTest(String browser) throws InterruptedException, IOException {
		String testUrl = null;
		// Read Properties file
		inputFile = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\config.properties");
		configFile.load(inputFile);
		ProjectConstants.filePath = configFile.getProperty("SCREENSHOT_FILEPATH").toString();
		// System.out.println("FILE PATH " + ProjectConstants.filePath);
		testUrl = configFile.getProperty("GAMEURL").toString();
		System.out.println(testUrl);
		System.out.println("Open the browser: " + browser + "\n");
		if (browser.equalsIgnoreCase("chrome")) {
			setUpChromeBrowser();
		} else if (browser.equalsIgnoreCase("firefox")) {
			setUpFirefoxBrowser();
		} else {
			System.out.println("Select Chrome or Firefox");
		}
		dr.get(testUrl);
		assignValuesToProjectConstants();
		Thread.sleep(3000);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownTestMethods(ITestResult result) throws IOException {
		inputFile.close();
		dr.close();
		System.out.println("Driver is closed");
	}
}
