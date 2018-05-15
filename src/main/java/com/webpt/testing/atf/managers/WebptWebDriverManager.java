package com.webpt.testing.atf.managers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebptWebDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    public static ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal<RemoteWebDriver>();
   
    public static WebDriver getDriver() {
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
    
    public static RemoteWebDriver getRemoteDriver(){
    	return remoteWebDriver.get();
    }
    
    public static void setRemoteWebDriver(WebDriver remoteDriver) {
    
			webDriver.set(remoteDriver);
		
   }
}

