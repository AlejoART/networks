package com.webpt.testing.atf.config;

import java.util.List;

/**
 * Manages project configuration variables.
 * You can add what ever environment variables your project needs.
 * You can access those variables with the get and set methods
 * or you can add getters and setters to this config for convenience.
 */
public class Config extends AbstractConfig {

    public String getWebdriverDriver(){
        String driver = get("webdriver.driver");
        if(driver == null){
            driver = "chrome";
        }
        return driver.toLowerCase();
    }

    public String getWebdriverBaseUrl(){
        return get("webdriver.base.url");
    }

    public String getWebdriverRemoteDriver(){
        return get("webdriver.remote.driver");
    }

    public String getWebdriverRemoteUrl(){
        return get("webdriver.remote.url");
    }

    public boolean isRemoteDriver(){
        return getWebdriverRemoteDriver() != null && !getWebdriverRemoteDriver().isEmpty();
    }

    public String getWebdriverPlatform(){
        return get("webdriver.platform");
    }

    // CHROME

    public List<String> getChromeArguments(){
        return getAsList("chrome.arguments");
    }

    public String getChromeBinary(){
        return get("chrome.binary");
    }

    public List<String> getChromeExtensions(){
        return getAsList("chrome.extensions");
    }

    public Boolean isChromeHeadless(){
        return getAsBoolean("chrome.headless");
    }

    public String getDownloadsDirectory(){
        return get("downloads.directory");
    }

    // FIREFOX

    public String getFireFoxProfileFile(){
        return get("firefox.profile");
    }

    public String getFireFoxMode(){
        return get("firefox.mode");
    }

    public Boolean getFireFoxCaptureNetworkTraffic(){
        return getAsBoolean("firefox.capture_network_traffic");
    }

    public Boolean getFireFoxTrustAllSSLCertificates(){
        return getAsBoolean("firefox.trust_all_ssl_certificates");
    }

    public Boolean getFireFoxAddCustomRequestHeader(){
        return getAsBoolean("firefox.add_custom_request_header");
    }

    public String getFireFoxBinary(){
        return get("firefox.binary");
    }

    public String getFireFoxLogLevel(){
        return get("firefox.log.level");
    }

    public String getFireFoxLogFile(){
        return get("firefox.log.file");
    }

    public String getFireFoxLoadStrategy(){
        return get("firefox.load.strategy");
    }

    public Integer getFireFoxPort(){
        return getAsInt("firefox.port");
    }

    public String getFireFoxGeckoBinary(){
        return get("firefox.gecko");
    }

    // Capabilities

    public String getUnexpectedAlertBehavior() {
        return get("capabilities.unexpectedAlertBehaviour");
    }

    public String getBrowserVersion(){
        return get("capabilities.browserVersion");
    }

    public Boolean getTakesScreenshot(){
        return getAsBoolean("capabilities.takesScreenshot");
    }

    public Boolean getHandlesAlerts(){
        return getAsBoolean("capabilites.getHandlesAlerts");
    }

    public Boolean getCSSSelectorsEnabled(){
        return getAsBoolean("capabilities.cssSelectorsEnabled");
    }

    public Boolean getJavascriptEnabled(){
        return
    }

}
