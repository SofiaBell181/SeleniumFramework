package QaAuto.SeleniumFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import QaAuto.SeleniumFramework.TestComponents.Retry;

import QaAuto.SeleniumFramework.TestComponents.BaseTest;
import QaAuto.SeleniumFramework.pageobjects.CartCatalogPage;
import QaAuto.SeleniumFramework.pageobjects.CheakoutPage;
import QaAuto.SeleniumFramework.pageobjects.ConfirmationPage;
import QaAuto.SeleniumFramework.pageobjects.MyCartPage;
import QaAuto.SeleniumFramework.pageobjects.OrderPage;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		String country = "sweden";
		String urlCart = "https://rahulshettyacademy.com/client/#/dashboard/cart";
		String urlDash = "https://rahulshettyacademy.com/client/#/dashboard/dash";

		CartCatalogPage cartcatalogPage = landingPage.login(input.get("email"), input.get("password"));
//		CartCatalogPage cartcatalogPage = new CartCatalogPage(driver);
		cartcatalogPage.waitUrlMatches(urlDash);
		List<WebElement> products = cartcatalogPage.getProductsList();
		cartcatalogPage.getProductByName(input.get("productname"));
		Boolean booleanVariable = cartcatalogPage.clickOnProduct(input.get("productname"));
		Assert.assertTrue(booleanVariable);

		MyCartPage mycartPage = cartcatalogPage.goToCartMenu(urlCart);
		mycartPage.getNameProducts();
		mycartPage.getMatchesProducts(input.get("productname"));
		Assert.assertTrue(mycartPage.getMatchesProducts(input.get("productname")));

		CheakoutPage cheakoutPage = mycartPage.goToOrder();
		cheakoutPage.fillForm(country);

		ConfirmationPage confirmationpage = cheakoutPage.goToConfirmationPage();
		String textMessage = confirmationpage.checkConfirmationmessage();
		Assert.assertTrue(textMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		CartCatalogPage cartcatalogPage = landingPage.login("sofyabel99@gmail.com", "2aHdj#y89nhyDiP");
		OrderPage orderPage = cartcatalogPage.goToOrdersMenu();
		Assert.assertTrue(orderPage.getMatchesOrder(productName));
	}

	// OPTION 3 MODERN
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = readJsonData(System.getProperty("user.dir")
				+ "//src//test//java//QaAuto//SeleniumFramework//data//PurchaseOrderData.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	
	
	// OPTION 2 MODERN
//	@DataProvider
//	public Object[][] getData() throws IOException {
//	HashMap<Object, Object> map = new HashMap<Object, Object>();
//	map.put("email", "sofyabel99@gmail.com");
//	map.put("password", "2aHdj#y89nhyDiP");
//	map.put("productname", "ADIDAS ORIGINAL");
//	
//	HashMap<Object, Object> map2 = new HashMap<Object, Object>();
//	map2.put("email", "sofiibeloborodtseva@mail.ru");
//	map2.put("password", "h$bq5ctpmA4w$VE");
//	map2.put("productname", "IPHONE 13 PRO");
	
//	return new Object[][] { {map}, {map2} };	
//}
	
	
	// OPTION 1
//	@DataProvider
//	public Object[][] getData() {
//		return new Object [][] {{"sofyabel99@gmail.com", "2aHdj#y89nhyDiP", "ADIDAS ORIGINAL"}, {"sofiibeloborodtseva@mail.ru", "h$bq5ctpmA4w$VE", "IPHONE 13 PRO"}};
//	}

}
