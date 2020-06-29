package com.live.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.live.base.Base;

public class HomePage extends Base{

	
	@FindBy(className="login")
	WebElement signInBtn;
	
	@FindBy(className="shop-phone")
	WebElement callUs;
	
	@FindBy(id="header_logo")
	WebElement logo;
	
	@FindBy(xpath="//a[@class='sf-with-ul'][contains(text(),'Women')]")
	WebElement topMenu;
	
	@FindBy(xpath="//li[@class='sfHover']//a[contains(text(),'Blouses')]")
	WebElement subMenuBlouse;
	
	@FindBy(id="search_query_top")
	WebElement search;
	
	@FindBy(name="submit_search")
	WebElement searchButton;
	
	@FindBy(xpath="//div[@class='ac_results']/ul/li")
	WebElement searchResults;
	
	@FindBy(xpath="//ul[@id='homefeatured']//a[@class='product-name']")
	List<WebElement> homepageProductName;
	
	@FindBy(xpath="//ul[@id='homefeatured']//span[@class='price product-price']")
	List<WebElement> homepageProductPrice;
		
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public String getScreenTitle(){
		return driver.getTitle();
	}
	
	public boolean isSignInButtonShown(){
		return signInBtn.isDisplayed();
	}
	
	public LoginPage clickOnSignInButton(){
		signInBtn.click();
		return new LoginPage();
	}
	
	public String getCallUs(){
		return callUs.getText();
	}
	
	public boolean isLogoDisplayed(){
		return logo.isDisplayed();
	}
	
	public void goToHomeScreen(){
		logo.click();
	}
	
	public void moveToTopMenu(){
//		Actions builder = new Actions(driver);
		new Actions(driver).moveToElement(topMenu).build().perform();
	}
	
	public boolean checkMenuItems(){
		return subMenuBlouse.isDisplayed();
	}
	
	public boolean searchSuggestions(){
		search.click();
		search.sendKeys("dress");
		if(searchResults.getText() != null)
			return true;
		else
			return false;
	}
	
	public String[] productNamesInHomepage(){
		String[] products = new String[homepageProductName.size()];
		int i = 0;
		for(WebElement ele: homepageProductName){
			products[i] = ele.getAttribute("title");
			i++;
		}
		return products;
	}
	
	public String[] productPricesInHomepage(){
		String price="";
		int i=0;
		String[] products = productNamesInHomepage();
		for(WebElement ele: homepageProductPrice){
			price=ele.getText();
			if(!price.trim().equalsIgnoreCase("")){
				products[i] += "="+price;
				i++;
			}
		}
		return products;
	}
	
	public void productsDetails(){
		String[] products = productPricesInHomepage();
		for(int i=0; i<products.length; i++)
			System.out.println(products[i]);
	}
}
