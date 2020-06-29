package com.live.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.live.base.Base;
import com.live.pages.HomePage;

public class HomePageTest extends Base{
	
	HomePage homePage;
		
	@BeforeMethod
	public void setUp(){
		homePage = new HomePage();
	}

	@Test(enabled=true)
	public void homePageChecks(){
		SoftAssert softAssert = new SoftAssert();
		Reporter.log("Validating screen title", true);
		softAssert.assertEquals(homePage.getScreenTitle(), prop.GetDataFromConfig("homescreentitle"), "Home screen title is not as expected");
		Reporter.log("Checking for Sign in button", true);
		Assert.assertEquals(homePage.isSignInButtonShown(), true, "\"Sign in\" button is not shown in home screen");
		Reporter.log("Checking for Logo", true);
		softAssert.assertEquals(homePage.isLogoDisplayed(), true,"Home page logo not diaplayed");
		Reporter.log("Verifying if call us now is shown and had expected value", true);
		softAssert.assertEquals(homePage.getCallUs().contains(prop.GetDataFromConfig("callus")),true, "Call us now is not as expected");
		Reporter.log("Checking if Top menu had menu items", true);
		homePage.moveToTopMenu();
		softAssert.assertEquals(homePage.checkMenuItems(), true, "Sub menu item not displayed");
		Reporter.log("Checking if items shown for search query", true);
		softAssert.assertEquals(homePage.searchSuggestions(), true, "Search results not found");
		softAssert.assertAll();
//		homePage.productsDetails();
//		new BrokenLinkCheck();
	}
}
