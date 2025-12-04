package QaAuto.SeleniumFramework.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import QaAuto.SeleniumFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlonetest {

	public static void main(String[] args) {
		// All steps witout spliting
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup(); // Downloads & configures correct ChromeDriver
		WebDriver driver = new ChromeDriver(); // Starts Chrome & returns driver instance
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("sofyabel99@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("2aHdj#y89nhyDiP");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.urlMatches("https://rahulshettyacademy.com/client/#/dashboard/dash"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
		// products.stream().filter(s -> s.getText().contains("ADIDAS
		// ORIGINAL")).forEach(s -> System.out.println(s));

		// Add product to the cart
		/* OPTION 1 */
		//products.stream().filter(s -> s.getText().contains("ADIDAS ORIGINAL"))
		//.map(s -> s.findElement(By.cssSelector("button.w-10"))).forEach(s -> s.click());

		// Add product to the cart
		/* OPTION 2 */
		//List<WebElement> button = products.stream().filter(s-> s.getText().contains("ADIDAS ORIGINAL")).map(s-> getButton(s))
		//.collect(Collectors.toList());
		//button.stream().forEach(s-> s.click());

		// Add product to the cart
		/* OPTION 3 Rahul example */
		WebElement productMatch = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		productMatch.findElement(By.cssSelector("button.w-10")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Product Added To Cart']")));
		boolean bolean = driver.findElement(By.cssSelector("div[aria-label='Product Added To Cart']")).isDisplayed();
		Assert.assertTrue(bolean);
		driver.findElement(By.xpath("//button[@class='btn btn-custom' and contains(text(),'Cart')]")).click();
		
		List <WebElement> cartNameProduct = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
		Boolean matchName = cartNameProduct.stream().anyMatch(cartName -> cartName.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(matchName);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div .payment"))));
		WebElement input = driver.findElement(By.cssSelector(".form-group input.text-validated"));
		input.sendKeys("sweden");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(@class,'ta-results')]"))));
		input.sendKeys(Keys.ARROW_DOWN);
		input.sendKeys(Keys.ENTER);
		WebElement span = driver.findElement(By.className("numberCircle"));
		driver.findElement(RelativeLocator.with(By.xpath("//input[contains(@class,'input txt')]")).near(span)).sendKeys("677");
		driver.findElement(By.xpath("//input[contains(@class,'input txt') and preceding-sibling::div[text()='Name on Card ']]")).sendKeys("Sofia Test");
		driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@aria-label='Order Placed Successfully']")).isDisplayed());
		String confirmMessage = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
		
	}

	/* OPTION 2 */
//	private static WebElement getButton (WebElement s) {
//		WebElement btn = s.findElement(By.cssSelector("button.w-10"));
//		return btn;
//	}

}
