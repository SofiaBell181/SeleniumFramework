package QaAuto.SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import QaAuto.SeleniumFramework.abstractcomponents.AbstractComponents;

//These page objects just give you  the locators and action methods which needs to be performed

public class MyCartPage extends AbstractComponents {

	WebDriver driver;

	public MyCartPage(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver2, this);
	}
	
	private By blockPayment = By.cssSelector("div .payment");
	
	private @FindBy(xpath ="//div[@class='cartSection']//h3")
	List<WebElement> titles;
	
	private @FindBy(css=".totalRow button")
	WebElement bthCheckout;
	
	public List<WebElement> getNameProducts() {
		return titles;
	}
	
	public Boolean getMatchesProducts(String productName) {
		Boolean matchName = getNameProducts().stream().anyMatch(cartName -> cartName.getText().equalsIgnoreCase(productName));
		return matchName;
	}
	
	public CheakoutPage goToOrder() {
		bthCheckout.click();
		waitElementToAppear(blockPayment);
		CheakoutPage orderPage = new CheakoutPage(driver);
		return orderPage;
	}

}
