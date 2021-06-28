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
import io.appium.java_client.functions.ExpectedCondition;

public class webapp {

		
		public static void main(String[] args) throws MalformedURLException, InterruptedException {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", "redmi");
			cap.setCapability("platformName", "Android");
			cap.setCapability("browserName", "chrome");
			cap.setCapability("chromedriverExecutable", "C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
			
			AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), cap);

			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//			driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
			
			driver.get("https://magento.com/");
			
			driver.findElement(By.xpath("//*[text()='Sign in']")).click();
//			Thread.sleep(5000);
			driver.findElementByXPath("//*[@id='email']").sendKeys("balaji0017@gmail.com"); //findelement --> try to find the element in 500ms

			driver.findElementByXPath("//*[@id='pass']").sendKeys("balaji@12345");
			
			if(driver.isKeyboardShown()) { //for these options change java compiler version to 15
				driver.hideKeyboard();
			}
			
			driver.findElementByXPath("//*[@id='send2']").click();
			
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Log Out']")));
			
			String pagetitle = driver.getTitle();
			System.out.println("My page is ****** "+pagetitle);
			
			driver.findElementByXPath("//*[@id='send2']").click();
			
		}
	}

