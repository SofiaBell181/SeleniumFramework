package QaAuto.SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import QaAuto.SeleniumFramework.abstractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	WebDriver driver;
	
	public OrderPage(WebDriver driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver2, this);

	}
	
	private @FindBy(css="td:nth-child(3)")
	List<WebElement> orders;
	
	public List<WebElement> getNameOrders() {
		return orders;
	}
	
	public Boolean getMatchesOrder(String productName) {
		Boolean matchName = getNameOrders().stream().anyMatch(cartName -> cartName.getText().equalsIgnoreCase(productName));
		return matchName;
	}

}
