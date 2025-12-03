package QaAuto.SeleniumFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import QaAuto.SeleniumFramework.abstractcomponents.AbstractComponents;

//These page objects just give you  the locators and action methods which needs to be performed

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver2, this);
	}
	
	private By confirmationMess = By.cssSelector("#toast-container");
	
	private@FindBy(className="hero-primary")
	WebElement confirmMessage;
	
	public String checkConfirmationmessage() {
		waitElementToAppear(confirmationMess);
		String text = confirmMessage.getText();
		return text;
	}
}
