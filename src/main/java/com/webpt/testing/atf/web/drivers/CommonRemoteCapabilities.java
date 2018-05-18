package com.webpt.testing.atf.web.drivers;

import com.webpt.testing.atf.config.Config;
import com.webpt.testing.atf.config.ConfigFactory;
import com.webpt.testing.atf.config2.WebptConfigurationManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CommonRemoteCapabilities {

    public static DesiredCapabilities apply(DesiredCapabilities capabilities) {
        Config config = ConfigFactory.getConfig();

        String platform = config.getWebdriverPlatform();
        if (platform != null) {
            platform = platform.toLowerCase();
            if (platform.contains("windows")) {
                capabilities.setCapability("platform", Platform.WINDOWS);
            } else if (platform.contains("win8")) {
                capabilities.setCapability("platform", Platform.WIN8);
            } else if (platform.contains("win81")) {
                capabilities.setCapability("platform", Platform.WIN8_1);
            } else if (platform.contains("linux")) {
                capabilities.setCapability("platform", Platform.LINUX);
            } else if (platform.contains("mac")) {
                capabilities.setCapability("platform", Platform.MAC);
            } else if (platform.contains("XP")) {
                capabilities.setCapability("platform", Platform.XP);
            } else if (platform.contains("unix")) {
                capabilities.setCapability("platform", Platform.UNIX);
            } else if (platform.contains("Vista")) {
                capabilities.setCapability("platform", Platform.VISTA);
            } else if (platform.contains("android")) {
                capabilities.setCapability("platform", Platform.ANDROID);
            }
        }else{
            capabilities.setCapability("platform", Platform.ANY);
        }

        if (config.getUnexpectedAlertBehavior() != null) {
            capabilities.setCapability("unexpectedAlertBehaviour", config.getUnexpectedAlertBehavior());
        } else {
            capabilities.setCapability("unexpectedAlertBehaviour", "dismiss");
        }
        if (config.getBrowserVersion() != null) {
            capabilities.setCapability("browserVersion", config.getBrowserVersion());
        }
        if (config.getTakesScreenshot() != null) {
            capabilities.setCapability("takesScreenshot", config.getTakesScreenshot());
        }
        if (config.getHandlesAlerts() != null) {
            capabilities.setCapability("handlesAlerts", config.getHandlesAlerts());
        }
        if (config.getCSSSelectorsEnabled() != null) {
            capabilities.setCapability("cssSelectorsEnabled", config.getCSSSelectorsEnabled());
        }
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

}
