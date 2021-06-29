package com.tiaa.nativeapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AutomateNativeApp1Test {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "redmi");
		cap.setCapability("platformName", "Android");
		cap.setCapability("app", "C:\\Users\\Admin\\Downloads\\Khan Academy_v6.3.0_apkpure.com.apk");
		
		AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.findElementByXPath("//*[@text='Dismiss']").click();
		
		driver.findElementByXPath("//*[@text='Profile']").click();
//		driver.findElementByXPath("//*[@content-desc='Profile tab']").click();
//		driver.findElementByXPath("//*[@resource-id='org.khanacademy.android:id/tab_bar_button_profile']").click();

		driver.findElementByXPath("//*[@text='Sign in']").click();
		
		 driver.findElementByXPath("//*[@text='Enter an e-mail address or username']").sendKeys("Powade");
		 driver.findElementByXPath("//*[@text='Password']").sendKeys("Rahul");
		 driver.findElementByXPath("//*[@content-desc='Sign in']").click();
		 
		 String errormsg= driver.findElementByXPath("//*[contains(@text,'Invalid')]").getText();
		 System.out.println("my new msg "+errormsg);
		 
		 Thread.sleep(5000);
		 driver.closeApp();

	}

}
