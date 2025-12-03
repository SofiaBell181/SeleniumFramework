package QaAuto.SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QaAuto.SeleniumFramework.abstractcomponents.AbstractComponents;

//These page objects just give you  the locators and action methods which needs to be performed

public class CartCatalogPage extends AbstractComponents{
	WebDriver driver;
	
	// Create parameterized constructor - this method is the first thing to execute in class
	public CartCatalogPage(WebDriver driver2) {
		super(driver2);
		// assigning driver2 from StandAlonetest to local class variable - driver
		this.driver=driver2;
		// Construction of this(@FindBy(id="userEmail")) will be triggered when you call this method initElements
		PageFactory.initElements(driver2, this); 
	}
	
	// Declare Webelement with PAGE FACTORY
	
	private By cartBy = By.cssSelector(".mb-3");
	private By confirmationMess = By.cssSelector("div[aria-label='Product Added To Cart']");
	private By btn = By.cssSelector("button.w-10");
	
	@FindBy(css="div.mb-3")
	List<WebElement> products;
	
	@FindBy(css="div[aria-label='Product Added To Cart']")
	WebElement message;


	
	public List<WebElement> getProductsList() {
		waitElementToAppear(cartBy);
		return products;		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public boolean clickOnProduct(String productName) {
		WebElement prodMatch = getProductByName(productName);
		prodMatch.findElement(btn).click();
		waitElementToAppear(confirmationMess);
		boolean bolean = message.isDisplayed();
		return bolean;

	}
	

	


}
