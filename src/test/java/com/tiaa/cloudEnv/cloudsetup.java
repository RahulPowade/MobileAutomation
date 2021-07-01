package com.tiaa.cloudEnv;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class cloudsetup {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
	      
	      // Set your access credentials
	      caps.setCapability("browserstack.user", "rahulpowade_7CoIVD");
	      caps.setCapability("browserstack.key", "kz6wqsbrc3zdK2ESvvxB");
	      
	      // Set URL of the application under test
	      caps.setCapability("app", "bs://a5af4f769dc25fe893f8107f739d9fe5977eb4c5");
	      
	      // Specify device and os_version for testing
	      caps.setCapability("device", "Google Pixel 3");
	      caps.setCapability("os_version", "9.0");
	        
	      // Set other BrowserStack capabilities
	      caps.setCapability("project", "KhanAcadamey");
	      caps.setCapability("build", "Android");
	      caps.setCapability("name", "Cloud_Test");
	        
	      
	      // Initialise the remote Webdriver using BrowserStack remote URL
	      // and desired capabilities defined above
	        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
	            new URL("http://hub.browserstack.com/wd/hub"), caps);
	        
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			driver.findElementByXPath("//*[@text='Dismiss']").click();
			
			driver.findElementByXPath("//*[@text='Profile']").click();

			driver.findElementByXPath("//*[@text='Sign in']").click();
			
			 driver.findElementByXPath("//*[@text='Enter an e-mail address or username']").sendKeys("Powade");
			 driver.findElementByXPath("//*[@text='Password']").sendKeys("Rahul");
			 driver.findElementByXPath("//*[@content-desc='Sign in']").click();
			 
			 String errormsg= driver.findElementByXPath("//*[contains(@text,'Invalid')]").getText();
			 System.out.println("my new msg "+errormsg);
	        
	        
	      // Invoke driver.quit() after the test is done to indicate that the test is completed.
	      driver.quit();
	    
	    }


}
