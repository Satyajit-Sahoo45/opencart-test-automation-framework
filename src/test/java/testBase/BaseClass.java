package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
	public Logger logger; // Log4j
	public Properties p;
	
	@BeforeClass(groups = {"Sanity", "Regression", "Master", "Datadriven"})
	@Parameters({"os", "browser"})
	public void setup(String os, String browser) throws IOException {
		
		// Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		
		logger = LogManager.getLogger(this.getClass());
		
		// Grid Setup
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			//to pass OS and BROWSER details
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching OS!!!");
				return;
			}
			
			//browser
			if(browser.toLowerCase().equals("chrome")) {
				capabilities.setBrowserName("chrome");			
			} else if(browser.toLowerCase().equals("edge")) {
				capabilities.setBrowserName("MicrosoftEdge");
			}else if(browser.toLowerCase().equals("firefox")) {
				capabilities.setBrowserName("firefox");
			} else {
				System.out.println("No matching Browser!!!");
				return;
			}
			
			// donot know on which browser we want to run our test i.e: RemoteWebDriveR
			//TRIGGER GRID ENVIRONMENT
			tdriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			if(browser.toLowerCase().equals("chrome")) {
				tdriver.set(new ChromeDriver());			
			} else if(browser.toLowerCase().equals("edge")) {
				tdriver.set(new EdgeDriver());
			}else if(browser.toLowerCase().equals("firefox")) {
				tdriver.set(new FirefoxDriver());
			} else {
				tdriver.set(new ChromeDriver());
			}			
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		getDriver().get(p.getProperty("appUrl"));
		getDriver().manage().window().maximize();
	}
	
	@AfterClass(groups = {"Sanity", "Regression", "Master", "Datadriven"})
	public void tearDown() {
		getDriver().quit();
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(8);
		return generatedAlphaNumeric;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
	public WebDriver getDriver() {
	    return tdriver.get();
	}
}
