package QaAuto.SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import QaAuto.SeleniumFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public String browserName;

	// This class will contain an initializeDriver() method that handles all driver
	// setup.
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		// Just create object FileInputStream class, that convert file into input stream
		// object
		// Dynamically get the path file
		FileInputStream filestream = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//QaAuto//SeleniumFramework//resources//GlobalData.properties");
		// Load your Globaldata.properties file
		// this method will automatically parse that properties file and extract all key
		// value pairs from it.
		prop.load(filestream);
		
		// Extract property from Globaldata.properties file or from terminal
		if(System.getProperty("browser") != null) {
			// we get browser from terminal
			browserName = System.getProperty("browser");
		} else { // if there is no variable in the terminal we get variable from GlobalData.properties
			browserName = prop.getProperty("browser");
		}
		
		// ternary version
		//String browserName = getProperty("browser") !=null 
		//? System.getProperty("browser") 
		//: prop.getProperty("browser");

		// Browser initialization
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			// add method to invoke browser in a headless mode
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440,900)); // full screen
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
//			driver.manage().window().setSize(new Dimension(1440,900)); 
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToPage();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();

	}

	// Get external Json file
	public List<HashMap<String, String>> readJsonData(String path) throws IOException {
		// read json into string

		File file = new File(path);
		// Read entire JSON file as String (UTF-8)
		String stringFile = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		// Convert JSON string to List<HashMap<String, String>> using jackson.databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(stringFile,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String makeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File filePath = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, filePath);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

}
