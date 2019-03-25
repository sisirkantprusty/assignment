package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NetEntHomePage extends PageObjectBase{

	public NetEntHomePage (WebDriver dr){
		super(dr);
	}
	
	//Start button 
	@FindBy (xpath = "//*[@id='start']")
	private WebElement btnStart;
	
	//Game status 
	@FindBy (xpath = "//*[@id='status']")
	private WebElement lblStatus;
	
	//Result
	@FindBy (xpath = "//*[@id='result']")
	private WebElement result;
	
	
	public void clickStart(){
		btnStart.click();
	}
	
	public String getStatus(){		
		return lblStatus.getText();
	}
	
	public int getCountOfImages(){
		return result.findElements(By.tagName("div")).size();
	}
	
	public int compareImages(String[] imageNames) {

		// If 3 images are equal then return all
		int counter = 1;

		for (int i = 0; i < imageNames.length; i++) {
			for (int j = i + 1; j < imageNames.length; j++) {
				if (imageNames[i].equalsIgnoreCase(imageNames[j])) {
					counter += 1;
				}
			}
			if (counter==imageNames.length){
				break;
			}
		}
		System.out.println("No of matching images " + counter);
		return counter;

	}
	public String[] getImageNames(){
		List<WebElement> we = result.findElements(By.tagName("div"));
		int noOfImages = we.size();
		String[] imageNames = new String[noOfImages];
		for (int i=0;i<noOfImages;i++){
		imageNames[i]=	we.get(i).getAttribute("style").toString();
		System.out.println("Image name is " + imageNames[i]);
		}
		return imageNames;
		
	}
	
}
