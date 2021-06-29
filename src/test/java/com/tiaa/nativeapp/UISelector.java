package com.tiaa.nativeapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class UISelector {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "redmi");
		cap.setCapability("platformName", "Android");
		cap.setCapability("app", "C:\\Users\\Admin\\Downloads\\Khan Academy_v6.3.0_apkpure.com.apk");
		
		AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		
		driver.findElementByAndroidUIAutomator("UiSelector().text(\"Dismiss\")").click();
//		driver.findElementByAndroidUIAutomator("UiSelector().textContains(\"Dismiss\")").click();
		
		driver.findElementByAndroidUIAutomator("UiSelector().description(\"Profile tab\")").click();
		
		driver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"org.khanacademy.android:id/tab_bar_button_home\")").click();

	}

}
