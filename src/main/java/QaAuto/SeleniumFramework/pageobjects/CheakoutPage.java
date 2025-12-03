package QaAuto.SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;

import QaAuto.SeleniumFramework.abstractcomponents.AbstractComponents;

//These page objects just give you  the locators and action methods which needs to be performed

public class CheakoutPage extends AbstractComponents {

	WebDriver driver;

	public CheakoutPage(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver2, this);
	}
	
	private By blockPayment = By.cssSelector("div .payment");
	private By optionCountry = By.xpath("//*[contains(@class,'ta-results')]");

	
	private @FindBy(css=".form-group input.text-validated")
	WebElement inputCountry;
	
	private @FindBy(xpath="//input[contains(@class,'input txt') and preceding-sibling::div[contains(text(),'CVV Code ')]]")
	WebElement inpitCVV;

	private @FindBy(xpath="//input[contains(@class,'input txt') and preceding-sibling::div[text()='Name on Card ']]")
	WebElement inputCard;
	
	private @FindBy(css=".action__submit")
	WebElement btnSubmit;
	
	private @FindBy(xpath="//*[@aria-label='Order Placed Successfully']")
	WebElement paragraphMess;
	
	private @FindBy(className="hero-primary")
	WebElement confirmMessage;
	
	public void fillForm(String country) {
		inputCountry.sendKeys(country);
		waitElementToAppear(optionCountry);
		inputCountry.sendKeys(Keys.ARROW_DOWN);
		inputCountry.sendKeys(Keys.ENTER);
//		inpitCVV.sendKeys("677");
//		inputCard.sendKeys("Sofia Test");
		waitWebElementToBeClickable(btnSubmit);
		btnSubmit.click();
	}
	
	public ConfirmationPage goToConfirmationPage() {
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}
	


}
