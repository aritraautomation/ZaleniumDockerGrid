package test.zaleniumDocker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ZaleniumGridDockerConcept {
	
WebDriver driver;	
	
	@BeforeTest
	@Parameters("browser")
	public void setup(String br)throws MalformedURLException, InterruptedException{		
					
			DesiredCapabilities cap =new DesiredCapabilities();
			String hubURL = "http://172.21.236.81:4444/wd/hub";
		
			if(br.equals("chrome")){
				/*cap.setCapability(CapabilityType.BROWSER_NAME,BrowserType.CHROME);
				cap.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);*/
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.LINUX);
				
				ChromeOptions options = new ChromeOptions();
				options.merge(cap);
								
				driver = new RemoteWebDriver(new URL(hubURL),options);	
				
			} else if(br.equals("firefox")){
				cap.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
				cap.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
				
				driver = new RemoteWebDriver(new URL(hubURL),cap);				
			}
			driver.get("https://ui.freecrm.com/");
		}
	
	@Test
	public void validateTitle(){
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Cogmento CRM");
	}
	
	@AfterMethod
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
