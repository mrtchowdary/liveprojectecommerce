package com.live.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.live.base.Base;

public class LoginPage extends Base{
	
	String[] emailValidation = {"emailid","emailid@gmail","emailid@gmail.com"};
	String passwordValidation = "password";

	@FindBy(xpath="//h1[@class='page-heading']")
	WebElement authentication;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="passwd")
	WebElement password;
	
	@FindBy(id="SubmitLogin")
	WebElement signInBtn;
	
	@FindBy(className="lost_password form-group")
	WebElement forgotPassword;
	
	@FindBy(xpath="//div[@class='alert alert-danger']//li")
	WebElement errorMsg;
	
//	@FindBy(className="login")
//	WebElement homeSignInBtn;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean isAuthenticationScreen(){
		boolean bFound = false;
		try{
			if(authentication.isDisplayed()) bFound = true;
		}catch(Exception e){
			System.out.println("Not in authentication screen");
		}
		return bFound;
	}
	
	public String LoginHeadingText(){
		return authentication.getText();
	}
	
//	public void clickOnSignInButton(){
//		homeSignInBtn.click();
//	}
	
	public void clickInEmail(){
		email.click();
		email.clear();
	}
	
	public void enterEmail(String em){
		email.click();
		email.clear();
		email.sendKeys(em);
	}
	
	public void clickInPassword(){
		password.click();
		password.clear();
	}
	
	public void enterPassword(String pw){
		password.click();
		password.clear();
		password.sendKeys(pw);
	}
	
	public void clickSignInButton(){
		signInBtn.click();
//		return new MyAccountPage();
	}
	
	public String getErrorMessage(){
		return errorMsg.getText();
	}
	
	public void clickForgotPassword(){
		forgotPassword.click();
	}
	
	public String noEmailPassword(){
//		clickInEmail();
		enterEmail("");
//		clickInPassword();
		enterPassword("");
		clickSignInButton();
		
		return getErrorMessage();
	}
	
	public String wrongEmail1EmptyPassword(){
//		System.out.println(excel.GetStringDataBySheetName("login", 0, 0) +" and "+ excel.GetStringDataBySheetName("login", 0, 1));
//		clickInEmail();
		enterEmail(emailValidation[0]);
//		clickInPassword();
		enterPassword("");
		clickSignInButton();
		
		return getErrorMessage();
	}
	
	public String wrongEmail2EmptyPassword(){
//		clickInEmail();
		enterEmail(emailValidation[1]);
//		clickInPassword();
		enterPassword("");
		clickSignInButton();
		
		return getErrorMessage();
	}
	
	public String correctEmailEmptyPassword(){
//		clickInEmail();
		enterEmail(emailValidation[2]);
//		clickInPassword();
		enterPassword("");
		clickSignInButton();
		
		return getErrorMessage();
	}
	
	public String correctEmailWrongPassword(){
//		System.out.println(excel.GetStringDataBySheetName("login", 3, 0) +" and "+ excel.GetStringDataBySheetName("login", 3, 1));
//		clickInEmail();
		enterEmail(emailValidation[2]);
//		clickInPassword();
		enterPassword(passwordValidation);
		clickSignInButton();
		
		return getErrorMessage();
	}
	
	public MyAccountPage correctEmailCorrectPassword(){
		System.out.println(excel.GetStringDataBySheetName("login", 3, 0) +" and "+ excel.GetStringDataBySheetName("login", 3, 1));
//		clickInEmail();
		enterEmail(excel.GetStringDataBySheetName("login", 3, 0));
//		clickInPassword();
		enterPassword(excel.GetStringDataBySheetName("login", 3, 1));
		clickSignInButton();
		
		return new MyAccountPage();
	}
}
