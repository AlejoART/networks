package com.webpt.testing.atf.managers;


import com.webpt.testing.atf.WebptATFHandler;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebptWebAutomationManager {

	private static Logger log = LoggerFactory.getLogger(WebptWebAutomationManager.class);
	private Dimension size = null;
	

	/**
	 * getWebDriver
	 *
	 * @return the local instance of the webDriver. will create new instance if
	 *         one doesn't already exist to return.
	 */
	public WebDriver getWebDriver() {
		if (WebptWebDriverManager.getDriver() == null) {

			WebptWebDriverManager.setWebDriver(WebptATFHandler.getInstance().getWebAutomation().webDriverSetup());
		}

		return WebptWebDriverManager.getDriver();
	}
	public WebDriver ManageWindowSize() {
		size = new Dimension(1520, 960);
		getWebDriver().manage().window().setSize(size);
		return getWebDriver();
	}

	private TakesScreenshot takesScreenshot;

	public TakesScreenshot getTakesScreenshot() {
		return takesScreenshot;
	}

	public WebptWebAutomationManager() {
		log.info("Initializing the WebAutomationManager.");
	
	}

	/**
	 * webDriverSetup load the configured webdriver and options
	 *
	 * @return WebDriver
	 * @throws MalformedURLException 
	 */
	@SuppressWarnings("deprecation")
	private WebDriver webDriverSetup(){
		WebDriver driver = null;

		// SETUP Common Capabilities
		// Configure Candidate Browser Information

		String browserName = WebptConfigurationManager.getInstance().getWebBrowserName();
		String platform = WebptConfigurationManager.getInstance().getPlatform();

		// Set defaults if not configured
		if (StringUtils.isBlank(browserName)) {
			browserName = BrowserType.FIREFOX;
		}

		if (StringUtils.isBlank(platform)) {
			platform = Platform.ANY.toString();
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

		if (browserName.toLowerCase().contains("firefox")) {
			capabilities = DesiredCapabilities.firefox();

			capabilities = setCommonCapabilities(capabilities);

			FirefoxBinary binary = null;
			File binaryFile = null;
			if (WebptConfigurationManager.getInstance().getWebBrowserDownloadPath().length() > 0) {
				binaryFile = new File(WebptConfigurationManager.getInstance().getWebBrowserDownloadPath());
			}

			if (binaryFile != null && binaryFile.exists()) {
				binary = new FirefoxBinary(binaryFile);
				System.setProperty("webdriver.firefox.bin",
						WebptConfigurationManager.getInstance().getWebBrowserDownloadPath());
				capabilities.setCapability("binary", WebptConfigurationManager.getInstance().getWebBrowserDownloadPath());
				capabilities.setCapability("firefox_binary",
						WebptConfigurationManager.getInstance().getWebBrowserDownloadPath());

			}

			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", "download/reports");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("pdfjs.disabled", true);
			File profileFile = null;
			if (WebptConfigurationManager.getInstance().getFirefoxProfileDirecotryAndFilename().length() > 0) {
				profileFile = new File(WebptConfigurationManager.getInstance().getFirefoxProfileDirecotryAndFilename());
			}

			if (profileFile != null && profileFile.exists()) {
				profile = new FirefoxProfile(profileFile);

			}

			if (WebptConfigurationManager.getInstance().getFirefoxAcceptUntrustedCerts() != null) {
				profile.setPreference("webdriver_accept_untrusted_certs",
						WebptConfigurationManager.getInstance().getFirefoxAcceptUntrustedCerts());
			}
			if (WebptConfigurationManager.getInstance().getFirefoxAssumeUntrustedIssuer() != null) {
				profile.setPreference("webdriver_assume_untrusted_issuer",
						WebptConfigurationManager.getInstance().getFirefoxAssumeUntrustedIssuer());
			}
			if (WebptConfigurationManager.getInstance().getFirefoxLogDriverLevel() != null)
				profile.setPreference("webdriver.log.driver",
						WebptConfigurationManager.getInstance().getFirefoxLogDriverLevel());
			if (WebptConfigurationManager.getInstance().getFirefoxLogFile().length() > 0)
				profile.setPreference("webdriver.log.file", WebptConfigurationManager.getInstance().getFirefoxLogFile());
			if (WebptConfigurationManager.getInstance().getFirefoxLoadStrategy().length() > 0)
				profile.setPreference("webdriver.load.strategy",
						WebptConfigurationManager.getInstance().getFirefoxLoadStrategy());

			if (WebptConfigurationManager.getInstance().getFirefoxPort() != null)
				profile.setPreference("webdriver_firefox_port", WebptConfigurationManager.getInstance().getFirefoxPort());

			capabilities.setCapability(FirefoxDriver.PROFILE, profile);

			if (WebptConfigurationManager.getInstance().getFirefoxRCMode().length() > 0)
				capabilities.setCapability("mode", WebptConfigurationManager.getInstance().getFirefoxRCMode());
			if (WebptConfigurationManager.getInstance().getFirefoxRCCaptureNetworkTraffic() != null)
				capabilities.setCapability("captureNetworkTraffic",
						WebptConfigurationManager.getInstance().getFirefoxRCCaptureNetworkTraffic());
			if (WebptConfigurationManager.getInstance().getFirefoxRCAddCustomReqHeader() != null)
				capabilities.setCapability("addCustomRequestHeaders",
						WebptConfigurationManager.getInstance().getFirefoxRCAddCustomReqHeader());
			if (WebptConfigurationManager.getInstance().getFirefoxRCTrustAllSSLCerts() != null)
				capabilities.setCapability("trustAllSSLCertificates", WebptConfigurationManager.getInstance());
			// Marionette/gecko support
			
			  if (WebptConfigurationManager.getInstance().getGeckoDriverLocation()
			  != null) { System.setProperty("webdriver.gecko.driver",
			  WebptConfigurationManager.getInstance().getGeckoDriverLocation()); }
			 
			if (WebptConfigurationManager.getInstance().getUseGrid() != null
					&& !WebptConfigurationManager.getInstance().getUseGrid()) {
				//driver = new FirefoxDriver(binary, profile, capabilities);
			}
         } else if (browserName.toLowerCase().contains("chrome")) {
			capabilities = DesiredCapabilities.chrome();
			capabilities = setCommonCapabilities(capabilities);
			
			ChromeOptions options = new ChromeOptions();
			capabilities.setCapability("ignoreZoomStting", true);
			capabilities.setCapability(options.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			
			if (WebptConfigurationManager.getInstance().getChromeArgs() != null
					&& WebptConfigurationManager.getInstance().getChromeArgs().size() > 0) {
				options.addArguments(WebptConfigurationManager.getInstance().getChromeArgs());
			}

			if (WebptConfigurationManager.getInstance().getChromeBinary().length() > 0) {
				options.setBinary(new File(WebptConfigurationManager.getInstance().getChromeBinary()));
				
			}

			if (WebptConfigurationManager.getInstance().getChromeExtensions() != null
					&& WebptConfigurationManager.getInstance().getChromeExtensions().size() > 0) {
				List<File> fileExtensions = new ArrayList<File>();
				for (String extension : WebptConfigurationManager.getInstance().getChromeExtensions()) {
					if (extension != "") {
						fileExtensions.add(new File(extension));
					}
				}
				options.addExtensions(fileExtensions);
			}
			// options.addArguments("--load-extension=ABSOLUTE_PATH_TO_EXTENSION");
			//options.addArguments("-incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("--test-type");
			options.addArguments("chrome.switches","--disable-extensions");
			options.addArguments("--disable-popup-blocking");
		//	options.addArguments("--disable-camera");
			options.addArguments("--use-fake-ui-for-media-stream");
			options.addArguments("--headless");
			options.addArguments("disable-infobars");
			String downloadDirectory = new File("").getAbsolutePath(); 
			downloadDirectory = downloadDirectory +"//download//report//";
			downloadDirectory = downloadDirectory.replace("//", "\\");				
			File dir = new File(downloadDirectory);	  
			 //for (File f : dir.listFiles()) {
		      //      f.delete();
			// }
			//if(!dir.exists()){
			//	dir.mkdir();
			//}
						
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 2);
			prefs.put("download.default_directory", downloadDirectory);
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_setting_values.plugins", 1);
			options.setExperimentalOption("prefs", prefs);
			
			
			//driver = new ChromeDriver(options);
			//options.addArguments("--auto-open-devtools-for-tabs");
					
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			
			if (WebptConfigurationManager.getInstance().getChromeProxy().length() > 0) {
				Proxy proxy = new Proxy();
				proxy.setHttpProxy(WebptConfigurationManager.getInstance().getChromeProxy());
				capabilities.setCapability("proxy", proxy);
			}

			System.setProperty("webdriver.chrome.driver",
					WebptConfigurationManager.getInstance().getWebBrowserDownloadPath());
			if (WebptConfigurationManager.getInstance().getUseGrid() != null
					&& !WebptConfigurationManager.getInstance().getUseGrid()) {
				
				capabilities.setCapability("disable-popup-blocking", true);
				capabilities.setCapability("--disable-notifications", true);
				
				capabilities.setJavascriptEnabled(true);
				driver = new ChromeDriver(capabilities);
							
			}
		}
		if (WebptConfigurationManager.getInstance().getUseGrid()) {
			URL gridUrl = null;

			// Code for Jekins configuration of a multi-matrix
			
			try {
		
			      gridUrl = new URL ("/wd/hub");
			} catch (MalformedURLException e) {
				log.error("Unable to create grid URL to create remote web driver.");
			}
			capabilities.setBrowserName("chrome");
			capabilities.setCapability("platform", Platform.ANY);
			driver = new RemoteWebDriver(gridUrl, capabilities);
		}
		Timeouts timeouts = driver.manage().timeouts();
		timeouts.implicitlyWait(10, TimeUnit.SECONDS);
		timeouts.setScriptTimeout(30, TimeUnit.SECONDS);
		timeouts.pageLoadTimeout(30 * 2, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}
	
	private DesiredCapabilities setCommonCapabilities(DesiredCapabilities capabilities) {
		capabilities.setCapability("platform", Platform.VISTA);

		if (WebptConfigurationManager.getInstance().getPlatform().length() > 0) {
			if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("windows")) {
				capabilities.setCapability("platform", Platform.WINDOWS);
			} else if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("win8")) {
				capabilities.setCapability("platform", Platform.WIN8);
			} else if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("win81")) {
				capabilities.setCapability("platform", Platform.WIN8_1);
			} else if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("linux")) {
				capabilities.setCapability("platform", Platform.LINUX);
			} else if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("mac")) {
				capabilities.setCapability("platform", Platform.MAC);
			} else if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("XP")) {
				capabilities.setCapability("platform", Platform.XP);
			} else if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("unix")) {
				capabilities.setCapability("platform", Platform.UNIX);
			} else if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("Vista")) {
				capabilities.setCapability("platform", Platform.VISTA);
			} else if (WebptConfigurationManager.getInstance().getPlatform().toLowerCase().contains("android")) {
				capabilities.setCapability("platform", Platform.ANDROID);
			}
		}

		if (WebptConfigurationManager.getInstance().getUnexpectedAlertBehavior().length() > 0) {
			capabilities.setCapability("unexpectedAlertBehaviour",
					WebptConfigurationManager.getInstance().getUnexpectedAlertBehavior());
		} else {
			capabilities.setCapability("unexpectedAlertBehaviour", "dismiss");
		}
		if (WebptConfigurationManager.getInstance().getWebBrowserVersion().length() > 0)
			capabilities.setCapability("browserVersion", WebptConfigurationManager.getInstance().getWebBrowserVersion());
		if (WebptConfigurationManager.getInstance().getTakesScreenshot() != null)
			capabilities.setCapability("takesScreenshot", WebptConfigurationManager.getInstance().getTakesScreenshot());
		if (WebptConfigurationManager.getInstance().getHandlesAlerts() != null)
			capabilities.setCapability("handlesAlerts", WebptConfigurationManager.getInstance().getHandlesAlerts());
		if (WebptConfigurationManager.getInstance().getCSSSelectorsEnabled() != null)
			capabilities.setCapability("cssSelectorsEnabled",
					WebptConfigurationManager.getInstance().getCSSSelectorsEnabled());
		if (WebptConfigurationManager.getInstance().getJavascriptEnabled() != null)
			capabilities.setCapability("javascriptEnabled", WebptConfigurationManager.getInstance().getJavascriptEnabled());
		if (WebptConfigurationManager.getInstance().getDatabaseEnabled() != null)
			capabilities.setCapability("databaseEnabled", WebptConfigurationManager.getInstance().getDatabaseEnabled());
		if (WebptConfigurationManager.getInstance().getLocationContextEnabled() != null)
			capabilities.setCapability("locationContextEnabled",
					WebptConfigurationManager.getInstance().getLocationContextEnabled());
		if (WebptConfigurationManager.getInstance().getApplicationCacheEnabled() != null)
			capabilities.setCapability("applicationCacheEnabled",
					WebptConfigurationManager.getInstance().getApplicationCacheEnabled());
		if (WebptConfigurationManager.getInstance().getBrowserConnectionEnabled() != null)
			capabilities.setCapability("browserConnectionEnabled",
					WebptConfigurationManager.getInstance().getBrowserConnectionEnabled());
		if (WebptConfigurationManager.getInstance().getWebStorageEnabled() != null)
			capabilities.setCapability("webStorageEnabled", WebptConfigurationManager.getInstance().getWebStorageEnabled());
		if (WebptConfigurationManager.getInstance().getAcceptSSLCerts() != null)
			capabilities.setCapability("acceptSslCerts", WebptConfigurationManager.getInstance().getAcceptSSLCerts());
		if (WebptConfigurationManager.getInstance().getRotatable() != null)
			capabilities.setCapability("rotatable", WebptConfigurationManager.getInstance().getRotatable());
		if (WebptConfigurationManager.getInstance().getNativeEvents() != null)
			capabilities.setCapability("nativeEvents", WebptConfigurationManager.getInstance().getNativeEvents());
		if (WebptConfigurationManager.getInstance().getUnexpectedAlertBehavior() != null)
			capabilities.setCapability("unexpectedAlertBehaviour",
					WebptConfigurationManager.getInstance().getUnexpectedAlertBehavior());
		if (WebptConfigurationManager.getInstance().getElementScrollBehavior() != null)
			capabilities.setCapability("elementScrollBehavior",
					WebptConfigurationManager.getInstance().getElementScrollBehavior());

		// JSON Proxy
		if (WebptConfigurationManager.getInstance().getJSONProxyType().length() > 0)
			capabilities.setCapability("proxyType", WebptConfigurationManager.getInstance().getJSONProxyType());
		if (WebptConfigurationManager.getInstance().getJSONProxyAutoConfigURL().length() > 0)
			capabilities.setCapability("proxyAutoconfigUrl",
					WebptConfigurationManager.getInstance().getJSONProxyAutoConfigURL());
		if (WebptConfigurationManager.getInstance().getJSONProxy().length() > 0)
			capabilities.setCapability(WebptConfigurationManager.getInstance().getJSONProxy(),
					WebptConfigurationManager.getInstance().getJSONProxy());
		if (WebptConfigurationManager.getInstance().getJSONSocksUsername().length() > 0)
			capabilities.setCapability("socksUsername", WebptConfigurationManager.getInstance().getJSONSocksUsername());
		if (WebptConfigurationManager.getInstance().getJSONSocksPassword().length() > 0)
			capabilities.setCapability("socksPassword", WebptConfigurationManager.getInstance().getJSONSocksPassword());
		if (WebptConfigurationManager.getInstance().getJSONNoProxy().length() > 0)
			capabilities.setCapability("noProxy", WebptConfigurationManager.getInstance().getJSONNoProxy());
		if (WebptConfigurationManager.getInstance().getJSONLoggingComponent().length() > 0)
			capabilities.setCapability("component", WebptConfigurationManager.getInstance().getJSONLoggingComponent());
		if (WebptConfigurationManager.getInstance().getRemoteWebDriverQuietExceptions() != null)
			capabilities.setCapability("webdriver.remote.quietExceptions",
					WebptConfigurationManager.getInstance().getRemoteWebDriverQuietExceptions());

		return capabilities;
	}

	public void teardown() {
		if (getWebDriver() != null) {
			try {
				// Shut down the webdriver
				log.info("Webdriver teardown started.");

				getWebDriver().quit();
				WebptWebDriverManager.setWebDriver(null);
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