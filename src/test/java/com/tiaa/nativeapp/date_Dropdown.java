package com.tiaa.nativeapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class date_Dropdown {

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
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

		driver.findElementByXPath("//*[@text='Sign up with email']").click();
		
		 driver.findElementByXPath("//*[@text='First name']").sendKeys("Rahul");
		 driver.findElementByXPath("//*[@text='Last name']").sendKeys("Rahul");
		 driver.findElementByXPath("//*[@text='Birthday']").click();
		 
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[1]").click();
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[1]").clear();
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[1]").sendKeys("Aug");
		 
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[2]").click();
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[2]").clear();
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[2]").sendKeys("08");
		 
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[3]").click();
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[3]").clear();
		 driver.findElementByXPath("(//*[@resource-id='android:id/numberpicker_input'])[3]").sendKeys("1990");
		 
		 driver.findElementByXPath("//*[@resource-id='android:id/button1']").click();
		 
//		 String errormsg= driver.findElementByXPath("//*[contains(@text,'Invalid')]").getText();
//		 System.out.println("my new msg "+errormsg);
//		 
//		 Thread.sleep(5000);
//		 driver.closeApp();

	}

}
