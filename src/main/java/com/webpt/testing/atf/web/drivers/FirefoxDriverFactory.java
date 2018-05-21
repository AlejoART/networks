package com.webpt.testing.atf.web.drivers;

import com.webpt.testing.atf.config.WebDriverConfiguration;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class FirefoxDriverFactory {

    private WebDriverConfiguration config;

    public FirefoxDriverFactory(WebDriverConfiguration config) {
        this.config = config;
    }

    /**
     * Get an instance of the local ChromeDriverFactory Driver.
     * @return
     */
    public FirefoxDriver getDriver(){
        return new FirefoxDriver(getOptions());
    }

    /**
     * Get the capabilities for a ChromeDriverFactory remote webdriver
     * @return
     */
    public DesiredCapabilities getRemoteCapabilities(){
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getOptions());

        if (config.getFirefoxRCMode() != null) {
            capabilities.setCapability("mode", config.getFirefoxRCMode());
        }
        if (config.getFirefoxRCCaptureNetworkTraffic() != null) {
            capabilities.setCapability("captureNetworkTraffic", config.getFirefoxRCCaptureNetworkTraffic());
        }
        if (config.getFirefoxRCAddCustomReqHeader() != null) {
            capabilities.setCapability("addCustomRequestHeaders", config.getFirefoxRCAddCustomReqHeader());
        }
        if (config.getFirefoxRCTrustAllSSLCerts() != null) {
            capabilities.setCapability("trustAllSSLCertificates", config.getFirefoxRCTrustAllSSLCerts());
        }

        // todo: this doesn't make sense. Why set the binary to the download path?
        String binaryPath = config.getFirefoxBinary();
        if (binaryPath != null) {
            File binaryFile = new File(binaryPath);
            if (binaryFile.exists()) {
                // todo: not sure if this line is really needed
                System.setProperty("webdriver.firefox.bin", binaryPath);
                capabilities.setCapability("binary", binaryPath);
                capabilities.setCapability("firefox_binary", binaryPath);
            }
        }

        return applyCommonCapabilities(capabilities);
    }

    protected DesiredCapabilities applyCommonCapabilities(DesiredCapabilities capabilities){
        return CommonRemoteCapabilities.apply(capabilities);
    }

    // todo: more testing needs to be done with these options. I don't know if they are all needed or working as expected.
    protected FirefoxOptions getOptions(){

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", config.getWebBrowserDownloadPath());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
        File profileFile = null;
        if (config.getFirefoxProfileDirecotryAndFilename() !=  null) {
            profileFile = new File(config.getFirefoxProfileDirecotryAndFilename());
        }

        if (profileFile != null && profileFile.exists()) {
            profile = new FirefoxProfile(profileFile);
        }

        Boolean trust = config.getFirefoxRCTrustAllSSLCerts();
        if (trust != null) {
            profile.setPreference("webdriver_accept_untrusted_certs", trust);
            profile.setPreference("webdriver_assume_untrusted_issuer", !trust);
        }
        if (config.getFirefoxLogDriverLevel() != null) {
            profile.setPreference("webdriver.log.driver", config.getFirefoxLogDriverLevel());
        }
        if (config.getFirefoxLogFile() != null) {
            profile.setPreference("webdriver.log.file", config.getFirefoxLogFile());
        }
        if (config.getFirefoxLoadStrategy() != null) {
            profile.setPreference("webdriver.load.strategy", config.getFirefoxLoadStrategy());
        }
        if (config.getFirefoxPort() != null) {
            profile.setPreference("webdriver_firefox_port", config.getFirefoxPort());
        }

        if (config.getGeckoDriverLocation() != null) {
            System.setProperty("webdriver.gecko.driver", config.getGeckoDriverLocation());
        }

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        return options;
    }

}
