package com.tiaa.nativeapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.WaitOptions;

public class UIScrollable_TouchActions {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("deviceName", "Redmi K20");
		cap.setCapability("platformName", "Android");
		cap.setCapability("app", "C:\\Components\\app\\Khan Academy_v6.3.0_apkpure.com.apk");
//		cap.setCapability("udid", "f43c4160");
		
		AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(new URL("http://localhost:47231/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.findElementByXPath("//*[@text='Dismiss']").click();
		
		Thread.sleep(3000);
		
		
		
//		538,1332
		
		
		TouchAction action=new TouchAction(driver);
		
		//tap on a elements
		action
		.tap(TapOptions.tapOptions()
				.withElement(
						ElementOption.element(
								driver.findElementByXPath("//*[@text='Sign in']")
								)).withTapsCount(5)).perform();
		
		
		//tap 5 times using coordinates
		action
		.tap(TapOptions.tapOptions().withPosition(PointOption.point(0,0)).withTapsCount(5)).perform();
				
		
		//longPress using coordinates
		action.longPress(PointOption.point(0,0)).perform();
		
		//longPress on a elements
		action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(driver.findElementByXPath("")))
				.withDuration(Duration.ofSeconds(5))).perform();
	
		
		//get element coordinates and tap
		Point p=driver.findElementByXPath("//*[@text='Sign in']").getLocation();
		
		System.out.println(p.x);
		System.out.println(p.y);
		action.tap(PointOption.point(p.x,p.y)).perform();
		
		//tap using coordinates
		action.tap(PointOption.point(538,1332)).perform();
		
		action.tap(PointOption.point(538,1332)).perform();
		action
		.press(PointOption.point(530, 1406))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(515,412))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.release()
		.perform();
	
		Thread.sleep(5000);
		
		action
		.press(PointOption.point(530, 1406))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(515,412))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.release()
		.perform();
		
		Thread.sleep(5000);
		
		action
		.press(PointOption.point(530, 1406))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(515,412))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.release()
		.perform();
		//	(new TouchAction(driver))
//	  .press({x: 968, y: 1406})
//	  .moveTo({x: 984: y: 1091})
//	  .release()
//	  .perform()
//	  

	}

}
