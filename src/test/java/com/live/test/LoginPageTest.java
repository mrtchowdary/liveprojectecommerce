package com.live.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.live.base.Base;
import com.live.pages.HomePage;
import com.live.pages.LoginPage;
import com.live.pages.MyAccountPage;

public class LoginPageTest extends Base{

	HomePage homePage;
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp(){
		homePage = new HomePage();
		loginPage = homePage.clickOnSignInButton();
		System.out.println("setUp - login - before isAuthenticationScreen()");
		Assert.assertEquals(loginPage.isAuthenticationScreen(), true, "App not in Sign in screen");
		System.out.println("setUp - login - after isAuthenticationScreen()");
	}
		
	@Test(priority=1,groups={"needed"},enabled=false)
	public void noEmailPasswordTest(){
		logger=report.createTest("empty email - password");
		logger.info("Before - Login attempt with empty username and password");
		Reporter.log("Login attempt with empty username and password", true);
		Assert.assertTrue(loginPage.noEmailPassword().contains("email address required"), "Error message not matched");
		logger.info("After - Login attempt with empty username and password");
		report.flush();
	}
	
	@Test(priority=2,groups={"needed"},enabled=false)
	public void wrongEmailtype1EmptyPasswordTest(){
		logger=report.createTest("wrong email - empty password");
		logger.info("Before - Login attempt with wrong username (eg: username) and empty password");
		Reporter.log("Login attempt with wrong username (eg: username) and empty password", true);
		Assert.assertTrue(loginPage.wrongEmail1EmptyPassword().contains("Invalid email address"), "Error message not matched");
		logger.info("After - Login attempt with wrong username (eg: username) and empty password");
		report.flush();
	}
	
	@Test(priority=3,enabled=false)
	public void wrongEmailtype2EmptyPasswordTest(){
		logger=report.createTest("wrong email2 - empty password");
		logger.info("Before - Login attempt with wrong username (eg: username@gmail) and empty password");
		Reporter.log("Login attempt with wrong username (eg: username@gmail) and empty password", true);
		Assert.assertTrue(loginPage.wrongEmail2EmptyPassword().contains("Invalid email address"), "Error message not matched");
		logger.info("After - Login attempt with wrong username (eg: username@gmail) and empty password");
		report.flush();
	}
	
	@Test(priority=4,enabled=false)
	public void correctEmailEmptyPasswordTest(){
		logger=report.createTest("correct email - empty password");
		logger.info("Before - Login attempt with correct username and empty password");
		Reporter.log("Login attempt with correct username and empty password", true);
		Assert.assertTrue(loginPage.correctEmailEmptyPassword().contains("Password is required"), "Error message not matched");
		logger.info("After - Login attempt with correct username and empty password");
		report.flush();
	}
	
	@Test(priority=5,enabled=false)
	public void correctEmailWrongPasswordTest(){
		logger=report.createTest("corret email - wrong password");
		logger.info("Before - Login attempt with correct username and wrong password");
		Reporter.log("Login attempt with correct username and wrong password", true);
		Assert.assertTrue(loginPage.correctEmailWrongPassword().contains("Authentication failed"), "Error message not matched");
		logger.info("After - Login attempt with correct username and wrong password");
		report.flush();
	}
	
	@Test(priority=6,enabled=false)
	public void correctEmailCorrectPasswordTest(){
		SoftAssert softAssert = new SoftAssert();
		logger=report.createTest("corret email - correct password");
		logger.info("Before - Login attempt with correct username and correct password");
		Reporter.log("Login attempt with correct username and correct password", true);
		myAccountPage = loginPage.correctEmailCorrectPassword();
		logger.info("Validating if user in myaccount screen");
		softAssert.assertEquals(myAccountPage.myaccountHeadingText(), "MY ACCOUNT","Not in my accounts page");
		logger.info("User in myaccount screen, so logging out");
		loginPage = myAccountPage.logout();
		logger.info("Logged out, checking if user in sign in screen");
		softAssert.assertEquals(loginPage.LoginHeadingText(), "AUTHENTICATION", "App not in Sign in screen");
		logger.info("After - Login attempt with correct username and wrong password");
		softAssert.assertAll();
		report.flush();
	}
}
