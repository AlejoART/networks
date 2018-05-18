package com.webpt.testing.atf.web.drivers;

import com.webpt.testing.atf.config.Config;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChromeDriverFactory {

    private Config config;

    public ChromeDriverFactory(Config config) {
        this.config = config;
    }

    /**
     * Get an instance of the local ChromeDriverFactory Driver.
     * @return
     */
    public ChromeDriver getDriver(){
        return new ChromeDriver(getOptions());
    }

    /**
     * Get the capabilities for a ChromeDriverFactory remote webdriver
     * @return
     */
    public DesiredCapabilities getRemoteCapabilities(){
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, getOptions());

        capabilities.setBrowserName("chrome");
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

        String chromeProxy = config.get("chrome.proxy");
        if (chromeProxy != null && !chromeProxy.isEmpty()) {
            Proxy proxy = new Proxy();
            proxy.setHttpProxy(chromeProxy);
            capabilities.setCapability("proxy", proxy);
        }

        return applyCommonCapabilities(capabilities);
    }

    protected DesiredCapabilities applyCommonCapabilities(DesiredCapabilities capabilities){
        return CommonRemoteCapabilities.apply(capabilities);
    }

    protected ChromeOptions getOptions(){
        ChromeOptions options = new ChromeOptions();

        List<String> args = config.getChromeArguments();
        if (args != null && args.size() > 0) {
            options.addArguments(args);
        }

        if (config.getChromeBinary() != null) {
            options.setBinary(new File(config.getChromeBinary()));
        }

        List<String> extensions = config.getChromeExtensions();
        if (extensions != null && extensions.size() > 0) {
            List<File> fileExtensions = new ArrayList<File>();
            for (String extension : extensions) {
                if (!extension.isEmpty()) {
                    fileExtensions.add(new File(extension));
                }
            }
            options.addExtensions(fileExtensions);
        }

        if (config.isChromeHeadless()){
            options.addArguments("--headless");
        }

        // I don't know if the following stuff is needed.
        // Just keeping it here for temporary future reference.
//        String downloadDirectory = config.getDownloadsDirectory();
//        if(!downloadDirectory.startsWith("/")){
//            String workingDirectory = new File("").getAbsolutePath();
//            downloadDirectory = workingDirectory + "/" + downloadDirectory;
//        }
//        File dir = new File(downloadDirectory);
//
//        Map<String, Object> prefs = new HashMap<String, Object>();
//        prefs.put("profile.default_content_settings.popups", 2);
//        prefs.put("download.default_directory", downloadDirectory);
//        prefs.put("profile.default_content_setting_values.notifications", 2);
//        prefs.put("profile.default_content_setting_values.plugins", 1);
//        options.setExperimentalOption("prefs", prefs);

        return options;
    }

}
