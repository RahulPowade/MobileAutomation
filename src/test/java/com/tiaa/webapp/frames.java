package com.tiaa.webapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class frames {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "redmi");
		cap.setCapability("platformName", "Android");
		cap.setCapability("browserName", "chrome");
		cap.setCapability("chromedriverExecutable", "C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
		
		driver.get("https://netbanking.hdfcbank.com/netbanking");
		
		driver.switchTo().frame(driver.findElementByXPath("//*[contains(@src,'RSLogin')]"));
		
		driver.findElementByXPath("//*[@name='fldLoginUserId']").sendKeys("1234"); //findelement --> try to find the element in 500ms
		driver.switchTo().defaultContent(); // switch back to dafault content
//		driver.quit();

	}

}
