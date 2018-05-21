package com.webpt.testing.atf.web;

import com.webpt.testing.atf.config.WebDriverConfiguration;
import com.webpt.testing.atf.web.drivers.ChromeDriverFactory;
import com.webpt.testing.atf.web.drivers.FirefoxDriverFactory;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	private static Logger log = LoggerFactory.getLogger(WebDriverManager.class);

	private ChromeDriverFactory chromeDriverFactory;
	private FirefoxDriverFactory firefoxDriverFactory;
	private WebDriverConfiguration config;

	public WebDriverManager() {
		log.info("Initializing the WebDriverManager.");
		this.config = new WebDriverConfiguration();
		this.chromeDriverFactory = new ChromeDriverFactory(config);
		this.firefoxDriverFactory = new FirefoxDriverFactory(config);
	}

	/**
	 * @return the instance of the webDriver. will create new instance if
	 *         one doesn't already exist to return.
	 */
	public WebDriver getWebDriver() {
		WebDriver currentDriver = webDriver.get();
		if (currentDriver == null) {
			currentDriver = webDriverSetup();
			setWebDriver(currentDriver);
		}
		return currentDriver;
	}

	private void setWebDriver(WebDriver driver){
		webDriver.set(driver);
	}

	/**
	 * webDriverSetup load the configured webdriver and options
	 *
	 * @return WebDriver
	 * @throws MalformedURLException 
	 */
	private WebDriver webDriverSetup(){

		String browserName = config.getWebBrowserName();

		// Set defaults if not configured
		if (StringUtils.isBlank(browserName)) {
			browserName = BrowserType.CHROME;
		}

        WebDriver driver;
		if(config.getUseGrid()){
			driver = getRemoteDriver(browserName);
		}else{
			driver = getLocalDriver(browserName);
		}

		return applyTimeouts(driver);
	}

	private WebDriver getRemoteDriver(String browserName){
		DesiredCapabilities capabilities;

		if (browserName.contains("chrome")) {
			capabilities = chromeDriverFactory.getRemoteCapabilities();
		}else if(browserName.contains("firefox")) {
			capabilities = firefoxDriverFactory.getRemoteCapabilities();
		}else{
			throw new RuntimeException("Browser name [" + browserName + "] is not configured for remote support.");
		}

		URL gridUrl;
		try {
			gridUrl = new URL (config.getGridUrl());
		} catch (MalformedURLException e) {
			throw new RuntimeException("Unable to create grid URL to create remote web driver.", e);
		}

		return new RemoteWebDriver(gridUrl, capabilities);
	}

	private WebDriver getLocalDriver(String browserName){
		if (browserName.contains("firefox")) {
			return firefoxDriverFactory.getDriver();
		} else if (browserName.toLowerCase().contains("chrome")) {
			return chromeDriverFactory.getDriver();
		}
		throw new RuntimeException("Browser name [" + browserName + "] is not configured for local support.");
	}

	private WebDriver applyTimeouts(WebDriver driver){
		Timeouts timeouts = driver.manage().timeouts();
		timeouts.implicitlyWait(10, TimeUnit.SECONDS);
		timeouts.setScriptTimeout(30, TimeUnit.SECONDS);
		timeouts.pageLoadTimeout(30 * 2, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}

	// todo: this should be configurable
	public WebDriver ManageWindowSize() {
		Dimension size = new Dimension(1520, 960);
		getWebDriver().manage().window().setSize(size);
		return getWebDriver();
	}

	public void teardown() {
		if (getWebDriver() != null) {
			try {
				// Shut down the webdriver
				log.info("Webdriver teardown started.");

				getWebDriver().quit();
				setWebDriver(null);
				log.info("Webdriver teardown complete.");

			} catch (WebDriverException wde) {
				log.error("Error encountered during Webdriver teardown.", wde);
			}
		}
	}

	public String getSessionId() {
		try {
			String sessionId = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
			return sessionId;
		} catch (NullPointerException npe) {
			log.error("Unable to get the sessionId.", npe);
			return null;
		}
	}
}