package com.tiaa.serverAutomation;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class CustomizeServerStart {

	public static void main(String[] args) {
		AppiumDriverLocalService service=null;
		
		try {
			AppiumServiceBuilder builder=new AppiumServiceBuilder()
//					.usingPort(1212)
					.usingAnyFreePort()
//					.withLogFile(new File("serverlog.log"))
					.withLogFile(new File("src/test/resources/logs/server_"+LocalDateTime.now().toString().replace(":", "-")+".log"))
					.withArgument(GeneralServerFlag.RELAXED_SECURITY);
			
			service= AppiumDriverLocalService.buildService(builder);
			
			if(!service.isRunning()) {
				service.start();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		finally {
			service.stop();
		}
		


	}

}
