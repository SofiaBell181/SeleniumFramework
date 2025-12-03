package QaAuto.SeleniumFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import QaAuto.SeleniumFramework.abstractcomponents.AbstractComponents;

// These page objects just give you  the locators and action methods which needs to be performed

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	
	// Create parameterized constructor - this method is the first thing to execute in class
	public LandingPage(WebDriver driver2) {
		super(driver2);
		// assigning driver2 from StandAlonetest to local class variable - driver
		this.driver=driver2;
		// Construction of this(@FindBy(id="userEmail")) will be triggered when you call this method initElements
		PageFactory.initElements(driver2, this); 
	}
	
	// Declare Webelement with PAGE FACTORY
	private @FindBy(id="userEmail")
	WebElement userEmail;
	
	private @FindBy(id="userPassword")
	WebElement userPassword;
	
	private @FindBy(id="login")
	WebElement btnLogin;
	
	private @FindBy(css=".toast-error")
	WebElement errorMessage;
	
	public CartCatalogPage login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		btnLogin.click();
		//if you are sure that login will take you to product catalog at the last step on that method only, 
		//let's create object and let's send it so we can catch it back after you call this method and you continue. 
		//You already have object with you now.
		CartCatalogPage cartcatalogPage = new CartCatalogPage(driver);
		return cartcatalogPage;
		
	}
	
	public void goToPage() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String loginInvalidData() {
		waitWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}

}
