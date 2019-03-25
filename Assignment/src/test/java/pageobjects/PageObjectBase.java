package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class PageObjectBase {

	protected WebDriver dr;

	public PageObjectBase(WebDriver dr) {
		TestBase.dr = dr;
		PageFactory.initElements(dr, this);
	}
}
