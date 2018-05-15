package com.webpt.testing.atf.managers;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.apache.commons.configuration.*;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Fail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class WebptConfigurationManager {
	private static Logger log = LoggerFactory.getLogger(WebptConfigurationManager.class);
	private String defaultConfigurationFileName = "webpt-config.xml";

	
	private static WebptConfigurationManager webptConfigurationInstance;
	private WebptConfigurationManager() { loadConfiguration(); }

	/**
	 *WebptConfigurationManager.getInstance
	 * 
	 * @return the instance of the WebptConfigurationManager
	 */
	public static WebptConfigurationManager getInstance() {
		if (webptConfigurationInstance == null) {
			synchronized (WebptConfigurationManager.class) {
				WebptConfigurationManager inst = webptConfigurationInstance;
				if (inst == null) {
					synchronized (WebptConfigurationManager.class) {
						webptConfigurationInstance = new WebptConfigurationManager();
						log.info("Created new instance of the webptConfigurationManager.");
					}
				}
			}
		}
		return webptConfigurationInstance;
	}
	
	/**
	 * AllConfiguration
	 * Contains all configuration entries
	 */
	public Configuration AllConfiguration;

	private void loadConfiguration()
	{
		Properties sysProps = System.getProperties();
        String configFileName = sysProps.getProperty("test.config.filename");
        
        if (StringUtils.isEmpty(configFileName)) {
        	configFileName = defaultConfigurationFileName;
        }
        
        try {
        	AllConfiguration = new DefaultConfigurationBuilder(configFileName).getConfiguration();
            ((HierarchicalConfiguration) AllConfiguration).setExpressionEngine(new XPathExpressionEngine());
        } catch (Exception e) {
            Fail.fail("failed to read config file", e);
            log.error("Failed to read config file", e);
        }		
	}
	
    /**
     * RefreshConfiguration
     * Read the configuration and refresh
     */
    public void RefreshConfiguration()
    {
    	AllConfiguration = null;
    	loadConfiguration();
    }

	/**
	 * getNodesList
	 * @param key - key to the nodes you want to retrieve (ie webpt.databases.database)
	 * @return list of nodes
	 */
	public List<HierarchicalConfiguration> getNodesList(String key) {
		XMLConfiguration xmlConfig = (XMLConfiguration) ((CombinedConfiguration) AllConfiguration).getConfiguration(1);
		List<HierarchicalConfiguration> nodesList = xmlConfig.configurationsAt(key);
		return nodesList;
	}

	/**
     * getBooleanConfigEntry
     * @param xPathKey - Key in XPath format to locate in loaded config file
     * @return - Boolean entry from config file. Will return false if not found.
     */
    public Boolean getBooleanConfigEntry(String xPathKey)
    {
        	if (AllConfiguration.containsKey(xPathKey) && AllConfiguration.getString(xPathKey).length() != 0)
    		{
        		return Boolean.parseBoolean(AllConfiguration.getString(xPathKey));

    		}
        	else return false;
    }
    
    /**
     * getListEntry
     * @param xPathKey
     * @return List of strings. Will return empty List<string> if not found.
     */
    public List<String> getListEntry(String xPathKey)
    {
    	List<String> listOfConfig = new ArrayList<String>();
    	
    	if (AllConfiguration.containsKey(xPathKey)) {
    		listOfConfig = Lists.transform(AllConfiguration.getList(xPathKey), Functions.toStringFunction());
			listOfConfig.removeAll(Arrays.asList("", null));
    	}
    	
    	return listOfConfig;
    }
    
    /**
     * getStringEntry
     * @param xPathKey - Key in XPath format to locate in loaded config file
     * @return - String entry from config file. Will return empty string if not found.
     */
    public String getStringEntry(String xPathKey)
    {
     	String returnValue = "";
    	try {
    		if (AllConfiguration.containsKey(xPathKey)) 
    			return AllConfiguration.getString(xPathKey);
    		else return returnValue;
    	}
    	catch (Exception ex) {
    		return returnValue;
    	}
    }
    
    /**
     * getIntEntry
     * @param xPathKey - Key in XPath format to locate in loaded config file
     * @return - integer entry from config file. Will return 0 if not found.
     */
    public Integer getIntEntry(String xPathKey)
    {
    	try {
        	if (AllConfiguration.containsKey(xPathKey)) 
        		return Integer.parseInt(AllConfiguration.getString(xPathKey));
        	else return null;
    	}
    	catch (Exception e)
    	{
    		return null;
    	}
    }
    
    public void Teardown()
    {
    	// Any specific teardown required for the Configuration Manager
    }
    
	/********************************************************************************/
	/************* CONFIGURATION GET METHODS BELOW **********************************/
	/********************************************************************************/
	public String getTestDataFolder()
	{
		String key = "webpt/customapplicationconfig/testdata-folder";
		return getStringEntry(key);
	}



	/********************************************************************************/
	/************* WEB **************************************************************/
	/********************************************************************************/
	public String getUnexpectedAlertBehaviour()
	{
		String key = "webpt/web/unexpected-alert-behaviour";
		return getStringEntry(key);
	}
    public String getGoogleUrl()
    {
    	String key = "webpt/web/base-url/google";
    	return getStringEntry(key);
    }
    public String getBingUrl()
    {
    	String key = "webpt/web/base-url/bing";
    	return getStringEntry(key);
    }
    
    
	/********************************************************************************/
	/************* DESIRED CAPABILITIES**********************************************/
	/********************************************************************************/
    public String getWebBrowserName()
    {
    	String key = "webpt/desired-capabilities/browser-name";
    	return getStringEntry(key);
    }
    public String getWebBrowserVersion()
    {
    	String key = "webpt/desired-capabilities/browser-version";
    	return getStringEntry(key);
    }
    public String getPlatform()
    {
    	String key = "webpt/desired-capabilities/plwebptorm";
    	return getStringEntry(key);
    }
    public String getWebBrowserDownloadPath()
    {
    	String path = new File("").getAbsolutePath();
    	String key = "webpt/desired-capabilities/browser-download-path";
    	return path + getStringEntry(key);
    }
    public Boolean getTakesScreenshot() {
    	String key = "webpt/desired-capabilities/takes-screenshot";
    	return getBooleanConfigEntry(key);
    }
    public Boolean getHandlesAlerts() {
    	String key = "webpt/desired-capabilities/handles-alerts";
    	return getBooleanConfigEntry(key);
    }
    public Boolean getCSSSelectorsEnabled() {
    	String key = "webpt/desired-capabilities/css-selectors-enabled";
    	return getBooleanConfigEntry(key);
    }
    public Boolean getJavascriptEnabled() {
    	String key = "webpt/desired-capabilities/javascript-enabled";
    	return getBooleanConfigEntry(key);
    }
    public Boolean getDatabaseEnabled() {
    	String key = "webpt/desired-capabilities/database-enabled";
    	return getBooleanConfigEntry(key);
    }    
    public Boolean getLocationContextEnabled() {
    	String key = "webpt/desired-capabilities/location-context-enabled";
    	return getBooleanConfigEntry(key);
    }     
    public Boolean getApplicationCacheEnabled() {
    	String key = "webpt/desired-capabilities/application-cache-enabled";
    	return getBooleanConfigEntry(key);
    }    
    public Boolean getBrowserConnectionEnabled() {
    	String key = "webpt/desired-capabilities/browser-connection-enabled";
    	return getBooleanConfigEntry(key);
    }      
    public Boolean getWebStorageEnabled() {
    	String key = "webpt/desired-capabilities/web-storage-enabled";
    	return getBooleanConfigEntry(key);
    }     
    public Boolean getAcceptSSLCerts() {
    	String key = "webpt/desired-capabilities/accept-ssl-certs";
    	return getBooleanConfigEntry(key);
    }      
    public Boolean getRotatable() {
    	String key = "webpt/desired-capabilities/rotatable";
    	return getBooleanConfigEntry(key);
    }      
    public Boolean getNativeEvents() {
    	String key = "webpt/desired-capabilities/native-events";
    	return getBooleanConfigEntry(key);
    }
    public String getUnexpectedAlertBehavior() {
    	String key = "webpt/desired-capabilities/unexpected-alert-behavior";
    	return getStringEntry(key);
    }    
    public Integer getElementScrollBehavior() {
    	String key = "webpt/desired-capabilities/element-scroll-behavior";
    	return getIntEntry(key);
    }     
    public String getJSONProxyType() {
    	String key = "webpt/desired-capabilities/json-proxy-type";
    	return getStringEntry(key);
    }      
    public String getJSONProxyAutoConfigURL() {
    	String key = "webpt/desired-capabilities/json-proxy-auto-config-url";
    	return getStringEntry(key);
    }    
    public String getJSONProxy() {
    	String key = "webpt/desired-capabilities/json-proxy";
    	return getStringEntry(key);
    }    
    public String getJSONSocksUsername() {
    	String key = "webpt/desired-capabilities/json-socks-username";
    	return getStringEntry(key);
    }    
    public String getJSONSocksPassword() {
    	String key = "webpt/desired-capabilities/json-socks-password";
    	return getStringEntry(key);
    }    
    public String getJSONNoProxy() {
    	String key = "webpt/desired-capabilities/json-no-proxy";
    	return getStringEntry(key);
    }    
    public String getJSONLoggingComponent() {
    	String key = "webpt/desired-capabilities/json-logging-component";
    	return getStringEntry(key);
    }
    public Boolean getRemoteWebDriverQuietExceptions() {
    	String key = "webpt/desired-capabilities/remote-webdriver-quiet-exceptions";
    	return getBooleanConfigEntry(key);
    }    
    public Boolean getUseGrid() {
    	String key = "webpt/desired-capabilities/grid-use";
    	return getBooleanConfigEntry(key);
    }
	public String getGridUsername() {
		String key = "webpt/desired-capabilities/grid-username";
		return getStringEntry(key);
	}
	public String getGridAccessKey() {
		String key = "webpt/desired-capabilities/grid-accesskey";
		return getStringEntry(key);
	}
	public String getSauceConnectTunnelName() {
		String key = "webpt/desired-capabilities/sauceConnectTunnelName";
		return getStringEntry(key);
	}
	/**
	 * Use gridType to switch from sauceLabs to browserStack or seleniumGrid
	 *
	 */
	public String getGridType() {
		String key = "webpt/desired-capabilities/grid-type";
		return getStringEntry(key);
	}
    public String getGridUrl() {
    	String key = "webpt/desired-capabilities/grid-url";
    	return getStringEntry(key);
    }   
    public String getGridSeleniumProtocol() {
    	String key = "webpt/desired-capabilities/grid-selenium-protocol";
    	return getStringEntry(key);
    }     
    public Integer getGridMaxInstances() {
    	String key = "webpt/desired-capabilities/grid-max-instances";
    	return getIntEntry(key);
    }    
  
    
    // CHROME
    public List<String> getChromeArgs() {
    	String key = "webpt/desired-capabilities/chrome/chrome-options/args";
    	return getListEntry(key);
    }
    public String getChromeBinary() {
    	String key = "webpt/desired-capabilities/chrome/chrome-options/binary";
    	return getStringEntry(key);
    }
    public List<String> getChromeExtensions() {
    	String key = "webpt/desired-capabilities/chrome/chrome-options/extensions";
    	return getListEntry(key);
    }
    public String getChromeProxy() {
    	String key = "webpt/desired-capabilities/chrome/proxy";
    	return getStringEntry(key);
    }    
    
    // FIREFOX
    public String getFirefoxProfileDirecotryAndFilename() {
    	String key = "webpt/desired-capabilities/firefox/profile-dir-and-filename";
    	return getStringEntry(key);
    }   
    public String getFirefoxBinary() {
    	String key = "webpt/desired-capabilities/firefox/binary";
    	return getStringEntry(key);
    }    
    public String getFirefoxRCMode() {
    	String key = "webpt/desired-capabilities/firefox/rc-mode";
    	return getStringEntry(key);
    }    
    public Boolean getFirefoxRCCaptureNetworkTraffic() {
    	String key = "webpt/desired-capabilities/firefox/rc-capture-network-traffic";
    	return getBooleanConfigEntry(key);
    }
    public Boolean getFirefoxRCAddCustomReqHeader() {
    	String key = "webpt/desired-capabilities/firefox/rc-add-custom-request-header";
    	return getBooleanConfigEntry(key);
    }
    public Boolean getFirefoxRCTrustAllSSLCerts() {
    	String key = "webpt/desired-capabilities/firefox/rc-trust-all-ssl-cert";
    	return getBooleanConfigEntry(key);
    }
    public Boolean getFirefoxAcceptUntrustedCerts() {
    	String key = "webpt/desired-capabilities/firefox/webdriver-accept-untrusted-certs";
    	return getBooleanConfigEntry(key);
    }
    public Boolean getFirefoxAssumeUntrustedIssuer() {
    	String key = "webpt/desired-capabilities/firefox/webdriver-assume-untrusted-issuer";
    	return getBooleanConfigEntry(key);
    }   
    public String getFirefoxLogDriverLevel() {
    	String key = "webpt/desired-capabilities/firefox/webdriver-log-driver-level";
    	return getStringEntry(key);
    }
    public String getFirefoxLogFile() {
    	String key = "webpt/desired-capabilities/firefox/webdriver-log-file";
    	return getStringEntry(key);
    }
    public String getFirefoxLoadStrategy() {
    	String key = "webpt/desired-capabilities/firefox/webdriver-load-strategy";
    	return getStringEntry(key);
    }
    public Integer getFirefoxPort() {
    	String key = "webpt/desired-capabilities/firefox/webdriver-port";
    	return getIntEntry(key);
    }
	public String getGeckoDriverLocation() {
		String key = "webpt/desired-capabilities/firefox/webdriver-gecko-location";
		return getStringEntry(key);
	}
   
	/********************************************************************************/
	/************* REPORTING  *******************************************************/
	/********************************************************************************/
    public Boolean getWebUseJSErrorCollectorWithFirefox() {
    	String key = "webpt/reporting/use-jserrorcollector-with-firefox";
    	return getBooleanConfigEntry(key);
    }      
    public String getBufferedImageSaveLocation() {
    	String key = "webpt/reporting/buffered-image-save-location";
    	return getStringEntry(key);   	
    }
    public String getBufferedImageSaveFormat() {
    	String key = "webpt/reporting/buffered-image-save-format";
    	return getStringEntry(key);   	
    }
    public String getTakesScreenshotSaveLocation() {
    	String key = "webpt/reporting/takes-screenshot-save-location";
    	return getStringEntry(key);   	
    }
	
}

