package com.live.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.live.base.Base;

public class MyAccountPage extends Base{
	
	public MyAccountPage(){
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[@class='page-heading']")
	WebElement myAccount;
	
	@FindBy(className="logout")
	WebElement logout;
	
	public String myaccountHeadingText(){
		return myAccount.getText();
	}
	
	public LoginPage logout(){
		logout.click();
		return new LoginPage();
	}
}
