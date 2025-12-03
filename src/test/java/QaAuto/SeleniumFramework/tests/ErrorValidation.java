package QaAuto.SeleniumFramework.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import QaAuto.SeleniumFramework.TestComponents.Retry;

import QaAuto.SeleniumFramework.TestComponents.BaseTest;
import QaAuto.SeleniumFramework.pageobjects.CartCatalogPage;
import QaAuto.SeleniumFramework.pageobjects.MyCartPage;

public class ErrorValidation extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	String urlDash = "https://rahulshettyacademy.com/client/#/dashboard/dash";
	String urlCart = "https://rahulshettyacademy.com/client/#/dashboard/cart";

	@Test(groups= {"ErrorLogin"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException {

		landingPage.login("sofyabel99@gmail.com", "2aHdj#y89nhyDiPs");
		String messageText = landingPage.loginInvalidData();
		Assert.assertEquals("Incorrect email or password.!", messageText);

	}

	@Test
	public void ProductErrorValidation() {
		CartCatalogPage cartcatalogPage = landingPage.login("sofyabel99@gmail.com", "2aHdj#y89nhyDiP");
		cartcatalogPage.waitUrlMatches(urlDash);
		List<WebElement> products = cartcatalogPage.getProductsList();
		cartcatalogPage.getProductByName(productName);
		Boolean booleanVariable = cartcatalogPage.clickOnProduct(productName);
		Assert.assertTrue(booleanVariable);

		MyCartPage mycartPage = cartcatalogPage.goToCartMenu(urlCart);
		mycartPage.getNameProducts();
		Boolean match = mycartPage.getMatchesProducts("ADIDAS ORIGINAL 33");
		Assert.assertFalse(match);

	}

}
