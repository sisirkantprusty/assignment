package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.ProjectConstants;
import base.TestBase;

public class TakeScreenShotOnFailure implements ITestListener{
	// String filePath = ProjectConstants.filePath;
	DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test case " + result.getName() + " has started..");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test case " + result.getName() + " has passed..");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().toString().trim();
		System.out.println("method name is " + methodName);
		takeScreenShot(methodName, TestBase.dr);
	}

	private void takeScreenShot(String methodName, WebDriver dr) {
		File scrFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with test
		// method name
		try {
			System.out.println("FILE PATH " + ProjectConstants.filePath);
			FileUtils.copyFile(scrFile,
					new File(ProjectConstants.filePath + methodName + df.format(new Date()) + ".png"));
			System.out.println("***Placed screen shot in " + ProjectConstants.filePath + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
