package QaAuto.SeleniumFramework.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import QaAuto.SeleniumFramework.pageobjects.MyCartPage;
import QaAuto.SeleniumFramework.pageobjects.OrderPage;

// This is a file which exclusively store all reusable content.
public class AbstractComponents {
	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponents(WebDriver driver2) {
		this.driver = driver2;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		PageFactory.initElements(driver2, this);

	}
	
	@FindBy(xpath="//button[@class='btn btn-custom' and contains(text(),'Cart')]")
	WebElement btnCart;
	
	@FindBy(css="nav ul li:nth-child(3)")
	WebElement btnOrders;

	

	public void waitElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitWebElementToAppear(WebElement findBy) {
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitWebElementToBeClickable(WebElement findBy) {
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	
	public void waitUrlMatches(String url) {
		wait.until(ExpectedConditions.urlMatches(url));

	}
	
	public MyCartPage goToCartMenu(String urlCart) {
		btnCart.click();
		waitUrlMatches(urlCart);
		MyCartPage mycartPage = new MyCartPage(driver);
		return mycartPage;

	}
	
	public OrderPage goToOrdersMenu() {
		btnOrders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
