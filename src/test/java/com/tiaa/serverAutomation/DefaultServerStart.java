package com.tiaa.serverAutomation;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class DefaultServerStart {

	public static void main(String[] args) throws InterruptedException {
		
		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		
		System.out.println(service.isRunning());
		
		service.start();
		
		System.out.println(service.isRunning());
		
		System.out.println(service.getUrl());
		
		Thread.sleep(2000);
		
		service.stop();
		
		System.out.println(service.isRunning());

	}

}
