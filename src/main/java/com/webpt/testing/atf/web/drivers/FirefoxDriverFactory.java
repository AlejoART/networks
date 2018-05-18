package com.webpt.testing.atf.web.drivers;

import com.webpt.testing.atf.config.Config;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class FirefoxDriverFactory {

    private Config config;

    public FirefoxDriverFactory(Config config) {
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

        if (config.getFireFoxMode() != null) {
            capabilities.setCapability("mode", config.getFireFoxMode());
        }
        if (config.getFireFoxCaptureNetworkTraffic() != null) {
            capabilities.setCapability("captureNetworkTraffic", config.getFireFoxCaptureNetworkTraffic());
        }
        if (config.getFireFoxAddCustomRequestHeader() != null) {
            capabilities.setCapability("addCustomRequestHeaders", config.getFireFoxAddCustomRequestHeader());
        }
        if (config.getFireFoxTrustAllSSLCertificates() != null) {
            capabilities.setCapability("trustAllSSLCertificates", config.getFireFoxTrustAllSSLCertificates());
        }

        // todo: this doesn't make sense. Why set the binary to the download path?
        String binaryPath = config.getFireFoxBinary();
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
        profile.setPreference("browser.download.dir", config.getDownloadsDirectory());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
        File profileFile = null;
        if (config.getFireFoxProfileFile() !=  null) {
            profileFile = new File(config.getFireFoxProfileFile());
        }

        if (profileFile != null && profileFile.exists()) {
            profile = new FirefoxProfile(profileFile);
        }

        Boolean trust = config.getFireFoxTrustAllSSLCertificates();
        if (trust != null) {
            profile.setPreference("webdriver_accept_untrusted_certs", trust);
            profile.setPreference("webdriver_assume_untrusted_issuer", !trust);
        }
        if (config.getFireFoxLogLevel() != null) {
            profile.setPreference("webdriver.log.driver", config.getFireFoxLogLevel());
        }
        if (config.getFireFoxLogFile() != null) {
            profile.setPreference("webdriver.log.file", config.getFireFoxLogFile());
        }
        if (config.getFireFoxLoadStrategy() != null) {
            profile.setPreference("webdriver.load.strategy", config.getFireFoxLoadStrategy());
        }
        if (config.getFireFoxPort() != null) {
            profile.setPreference("webdriver_firefox_port", config.getFireFoxPort());
        }

        if (config.getFireFoxGeckoBinary() != null) {
            System.setProperty("webdriver.gecko.driver", config.getFireFoxGeckoBinary());
        }

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        return options;
    }

}
