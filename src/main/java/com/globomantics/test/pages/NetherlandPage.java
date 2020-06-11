package com.globomantics.test.pages;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.globomantics.test.base.TestBase;

public class NetherlandPage extends TestBase
{
	
	@FindBy(xpath= "//*[@class='form-control']")
	WebElement drpdownbtn;
	

	@FindBy(xpath="//*[@class='table table-hover']/tbody/tr")
	List<WebElement> tableElements;	
	

	@FindBy(xpath="//*[@class='row']//*[@class='col-md-12']")
	WebElement housingArea1;
	
	@FindBy(xpath="//*[@class='price']")
	WebElement cost;
	
	public NetherlandPage(WebDriver d)
	
	{
		this.driver=d;
		PageFactory.initElements(d, this);
	
	}
	
	
	public void clickDrpdown()
	{
		drpdownbtn.click();
	}
	
	public int tablesize()
	{
		return tableElements.size();
		
	}

	public String addressInHomePage()
	{
		return housingArea1.getText()+" "+cost.getText().replace("$","");
	}
	
	 public void selectFromDropDownN(String value)
	            throws TimeoutException {
	        Select select = new Select(drpdownbtn);
	        select.selectByVisibleText(value);
	
	    }
		
	
}
