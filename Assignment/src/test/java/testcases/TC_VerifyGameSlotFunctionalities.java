package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.ProjectConstants;
import base.TestBase;
import pageobjects.NetEntHomePage;

public class TC_VerifyGameSlotFunctionalities extends TestBase{
	
	NetEntHomePage netEntHomePage;

	@Test (description="TC to verify welcome status in the home page when the url loads for first time")
	public void verifyWelcomeStatusInHomePage() {
		netEntHomePage = new NetEntHomePage(dr);
		System.out.println("Welcome message is " + ProjectConstants.welcomeMessage);
		Assert.assertTrue(netEntHomePage.getStatus().equals(ProjectConstants.welcomeMessage), "Welcome message is not corect");
	}
	
	@Test (description="TC to verify number of images when home page loads for first time")
	public void verifyNoOfImages(){
		netEntHomePage = new NetEntHomePage(dr);
		Assert.assertTrue(netEntHomePage.getCountOfImages()==3, "No of Images is not 3.");
	}

	@Test (description="TC to verify the wins and corresponding status")
	public void verifyStatusOfGame() throws InterruptedException {
		netEntHomePage = new NetEntHomePage(dr);
		for (int i = 0; i < ProjectConstants.noOfTimesGamesChecked; i++) {
			netEntHomePage.clickStart();
			Thread.sleep(2000);
			Assert.assertTrue(netEntHomePage.getCountOfImages() == 3, "No of Images is not 3.");
			if (netEntHomePage.compareImages(netEntHomePage.getImageNames()) == 3) {
				if (!netEntHomePage.getStatus().equals(ProjectConstants.bigWin)) {
					Assert.assertTrue(false, "Issues with Big win");
				}
			} else if (netEntHomePage.compareImages(netEntHomePage.getImageNames()) == 2) {
				if (!netEntHomePage.getStatus().equals(ProjectConstants.smallWin)) {
					Assert.assertTrue(false, "Issues with Small win");
				}
			} else if (netEntHomePage.compareImages(netEntHomePage.getImageNames()) == 1) {
				if (!netEntHomePage.getStatus().equals(ProjectConstants.noWin)) {
					Assert.assertTrue(false, "Issues with No win");
				}
			}
		}

	}
	
}
