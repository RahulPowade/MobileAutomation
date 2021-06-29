package com.tiaa.nativeapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;

public class scroll {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "redmi");
		cap.setCapability("platformName", "Android");
		cap.setCapability("app", "C:\\Users\\Admin\\Downloads\\Khan Academy_v6.3.0_apkpure.com.apk");
		
		AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.findElementByXPath("//*[@text='Dismiss']").click();
		
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		while (driver.findElementsByXPath("//*[@text='Arts and humanities']").size()==0) {
			
			driver.executeScript("mobile:shell", ImmutableMap.of("command", "input touchscreen swipe 1000 800 1000 400"));
			
		}
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElementByXPath("//*[@text='Arts and humanities']").click();
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		while (driver.findElementsByXPath("//*[@text='Art of Asia']").size()==0) {
			
			driver.executeScript("mobile:shell", ImmutableMap.of("command", "input touchscreen swipe 1000 800 1000 400"));
			
		}
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElementByXPath("//*[@text='Art of Asia']").click();
		

	}

}
