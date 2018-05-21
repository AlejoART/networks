package com.webpt.testing.atf.config;

import java.io.File;
import java.util.List;

public class WebDriverConfiguration {

    private ConfigurationManager config = ConfigurationManager.getInstance();

	protected ConfigurationManager getConfigurationManager(){
    	return config;
	}
    
	/********************************************************************************
	 ************* DESIRED CAPABILITIES**********************************************
	 ********************************************************************************/
    public String getWebBrowserName()
    {
    	String key = "browser-name";
    	return config.getStringEntry(key);
    }
    public String getWebBrowserVersion()
    {
    	String key = "browser-version";
    	return config.getStringEntry(key);
    }
    public String getPlatform()
    {
    	String key = "plwebptorm";
    	return config.getStringEntry(key);
    }
    public String getWebBrowserDownloadPath()
    {
    	String path = new File("").getAbsolutePath();
    	String key = "browser-download-path";
    	return path + config.getStringEntry(key);
    }
    public Boolean getTakesScreenshot() {
    	String key = "takes-screenshot";
    	return config.getBooleanConfigEntry(key);
    }
    public Boolean getHandlesAlerts() {
    	String key = "handles-alerts";
    	return config.getBooleanConfigEntry(key);
    }
    public Boolean getCSSSelectorsEnabled() {
    	String key = "css-selectors-enabled";
    	return config.getBooleanConfigEntry(key);
    }
    public Boolean getJavascriptEnabled() {
    	String key = "javascript-enabled";
    	return config.getBooleanConfigEntry(key);
    }
    public Boolean getDatabaseEnabled() {
    	String key = "database-enabled";
    	return config.getBooleanConfigEntry(key);
    }    
    public Boolean getLocationContextEnabled() {
    	String key = "location-context-enabled";
    	return config.getBooleanConfigEntry(key);
    }     
    public Boolean getApplicationCacheEnabled() {
    	String key = "application-cache-enabled";
    	return config.getBooleanConfigEntry(key);
    }    
    public Boolean getBrowserConnectionEnabled() {
    	String key = "browser-connection-enabled";
    	return config.getBooleanConfigEntry(key);
    }      
    public Boolean getWebStorageEnabled() {
    	String key = "web-storage-enabled";
    	return config.getBooleanConfigEntry(key);
    }     
    public Boolean getAcceptSSLCerts() {
    	String key = "accept-ssl-certs";
    	return config.getBooleanConfigEntry(key);
    }      
    public Boolean getRotatable() {
    	String key = "rotatable";
    	return config.getBooleanConfigEntry(key);
    }      
    public Boolean getNativeEvents() {
    	String key = "native-events";
    	return config.getBooleanConfigEntry(key);
    }
    public String getUnexpectedAlertBehavior() {
    	String key = "unexpected-alert-behavior";
    	return config.getStringEntry(key);
    }    
    public Integer getElementScrollBehavior() {
    	String key = "element-scroll-behavior";
    	return config.getIntEntry(key);
    }     
    public String getJSONProxyType() {
    	String key = "json-proxy-type";
    	return config.getStringEntry(key);
    }      
    public String getJSONProxyAutoConfigURL() {
    	String key = "json-proxy-auto-config-url";
    	return config.getStringEntry(key);
    }    
    public String getJSONProxy() {
    	String key = "json-proxy";
    	return config.getStringEntry(key);
    }    
    public String getJSONSocksUsername() {
    	String key = "json-socks-username";
    	return config.getStringEntry(key);
    }    
    public String getJSONSocksPassword() {
    	String key = "json-socks-password";
    	return config.getStringEntry(key);
    }    
    public String getJSONNoProxy() {
    	String key = "json-no-proxy";
    	return config.getStringEntry(key);
    }    
    public String getJSONLoggingComponent() {
    	String key = "json-logging-component";
    	return config.getStringEntry(key);
    }
    public Boolean getRemoteWebDriverQuietExceptions() {
    	String key = "remote-webdriver-quiet-exceptions";
    	return config.getBooleanConfigEntry(key);
    }    
    public Boolean getUseGrid() {
    	String key = "grid-use";
    	Boolean useGrid = config.getBooleanConfigEntry(key);
    	return useGrid != null && useGrid;
    }
	public String getGridUsername() {
		String key = "grid-username";
		return config.getStringEntry(key);
	}
	public String getGridAccessKey() {
		String key = "grid-accesskey";
		return config.getStringEntry(key);
	}
	public String getSauceConnectTunnelName() {
		String key = "sauceConnectTunnelName";
		return config.getStringEntry(key);
	}
	/**
	 * Use gridType to switch from sauceLabs to browserStack or seleniumGrid
	 *
	 */
	public String getGridType() {
		String key = "grid-type";
		return config.getStringEntry(key);
	}
    public String getGridUrl() {
    	String key = "grid-url";
    	return config.getStringEntry(key);
    }   
    public String getGridSeleniumProtocol() {
    	String key = "grid-selenium-protocol";
    	return config.getStringEntry(key);
    }     
    public Integer getGridMaxInstances() {
    	String key = "grid-max-instances";
    	return config.getIntEntry(key);
    }    
  
    
    // CHROME
    public List<String> getChromeArgs() {
    	String key = "chrome.args";
    	return config.getListEntry(key);
    }
    public String getChromeBinary() {
    	String key = "chrome.binary";
    	return config.getStringEntry(key);
    }
    public List<String> getChromeExtensions() {
    	String key = "chrome.extensions";
    	return config.getListEntry(key);
    }
    public String getChromeProxy() {
    	String key = "chrome.proxy";
    	return config.getStringEntry(key);
    }
    public Boolean isChromeHeadless(){
		String key = "chrome.headless";
		return config.getBooleanConfigEntry(key);
	}
    
    // FIREFOX
    public String getFirefoxProfileDirecotryAndFilename() {
    	String key = "firefox.profile-dir-and-filename";
    	return config.getStringEntry(key);
    }   
    public String getFirefoxBinary() {
    	String key = "firefox.binary";
    	return config.getStringEntry(key);
    }    
    public String getFirefoxRCMode() {
    	String key = "firefox.rc-mode";
    	return config.getStringEntry(key);
    }    
    public Boolean getFirefoxRCCaptureNetworkTraffic() {
    	String key = "firefox.rc-capture-network-traffic";
    	return config.getBooleanConfigEntry(key);
    }
    public Boolean getFirefoxRCAddCustomReqHeader() {
    	String key = "firefox.rc-add-custom-request-header";
    	return config.getBooleanConfigEntry(key);
    }
    public Boolean getFirefoxRCTrustAllSSLCerts() {
    	String key = "firefox.rc-trust-all-ssl-cert";
    	return config.getBooleanConfigEntry(key);
    }
    public Boolean getFirefoxAcceptUntrustedCerts() {
    	String key = "firefox.webdriver-accept-untrusted-certs";
    	return config.getBooleanConfigEntry(key);
    }
    public Boolean getFirefoxAssumeUntrustedIssuer() {
    	String key = "firefox.webdriver-assume-untrusted-issuer";
    	return config.getBooleanConfigEntry(key);
    }   
    public String getFirefoxLogDriverLevel() {
    	String key = "firefox.webdriver-log-driver-level";
    	return config.getStringEntry(key);
    }
    public String getFirefoxLogFile() {
    	String key = "firefox.webdriver-log-file";
    	return config.getStringEntry(key);
    }
    public String getFirefoxLoadStrategy() {
    	String key = "firefox.webdriver-load-strategy";
    	return config.getStringEntry(key);
    }
    public Integer getFirefoxPort() {
    	String key = "firefox.webdriver-port";
    	return config.getIntEntry(key);
    }
	public String getGeckoDriverLocation() {
		String key = "firefox.webdriver-gecko-location";
		return config.getStringEntry(key);
	}
   
	/********************************************************************************
	 ************* REPORTING  *******************************************************
	 ********************************************************************************/
    public Boolean getWebUseJSErrorCollectorWithFirefox() {
    	String key = "reporting.use-jserrorcollector-with-firefox";
    	return config.getBooleanConfigEntry(key);
    }      
    public String getBufferedImageSaveLocation() {
    	String key = "reporting.buffered-image-save-location";
    	return config.getStringEntry(key);   	
    }
    public String getBufferedImageSaveFormat() {
    	String key = "reporting.buffered-image-save-format";
    	return config.getStringEntry(key);   	
    }
    public String getTakesScreenshotSaveLocation() {
    	String key = "reporting.takes-screenshot-save-location";
    	return config.getStringEntry(key);   	
    }
	
}

