package com.globomantics.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globomantics.test.base.BrowserFactory;
import com.globomantics.test.base.TestBase;

public class HomePage extends TestBase {
	@FindBy(xpath = "//img[contains(@alt,'inquiry')]")
	WebElement emailicon;

	@FindBy(xpath = "//*[@class='logo']")
	WebElement logoicon;

	@FindBy(xpath = "//*[@class='col-md-7']//*[@alt='House']")
	WebElement homepageimage;

	@FindBy(xpath = "//*[@id='name']")
	WebElement name;

	@FindBy(xpath = "//*[@id='email']")
	WebElement emailaddress;

	@FindBy(xpath = "//*[@id='remarks']")
	WebElement remarks;

	@FindBy(xpath = "//*[@class='btn btn-primary']")
	WebElement submitbtn;

	@FindBy(xpath = "//*[@class='col-md-4']//*[@class='form-control']")
	WebElement drpdownbtn;

	// initialize webelements
	public HomePage(WebDriver d) {
		driver = d;
		PageFactory.initElements(d, this);
	}

	public String validateHomPageTitle() {
		return driver.getTitle();

	}

	public boolean visibilityOfEmailIcon()

	{
		return emailicon.isDisplayed();
	}

	public boolean visibilityOfLogo() {
		return logoicon.isDisplayed();
	}

	public boolean visibilityOfHomePageImage() {
		return homepageimage.isDisplayed();
	}

	public void register(String nm, String em, String rem)

	{
		emailicon.click();
		name.sendKeys(nm);
		emailaddress.sendKeys(em);
		remarks.sendKeys(rem);
		submitbtn.click();

	}
}
