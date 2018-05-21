package com.webpt.testing.atf.web.drivers;

import com.webpt.testing.atf.config.WebDriverConfiguration;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CommonRemoteCapabilities {

    public static DesiredCapabilities apply(DesiredCapabilities capabilities) {
        WebDriverConfiguration wc = new WebDriverConfiguration();

        String platform = wc.getPlatform();
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

        if (wc.getUnexpectedAlertBehavior() != null) {
            capabilities.setCapability("unexpectedAlertBehaviour", wc.getUnexpectedAlertBehavior());
        } else {
            capabilities.setCapability("unexpectedAlertBehaviour", "dismiss");
        }
        if (wc.getWebBrowserVersion() != null) {
            capabilities.setCapability("browserVersion", wc.getWebBrowserVersion());
        }
        if (wc.getTakesScreenshot() != null) {
            capabilities.setCapability("takesScreenshot", wc.getTakesScreenshot());
        }
        if (wc.getHandlesAlerts() != null) {
            capabilities.setCapability("handlesAlerts", wc.getHandlesAlerts());
        }
        if (wc.getCSSSelectorsEnabled() != null) {
            capabilities.setCapability("cssSelectorsEnabled", wc.getCSSSelectorsEnabled());
        }
        if (wc.getJavascriptEnabled() != null)
            capabilities.setCapability("javascriptEnabled", wc.getJavascriptEnabled());
        if (wc.getDatabaseEnabled() != null)
            capabilities.setCapability("databaseEnabled", wc.getDatabaseEnabled());
        if (wc.getLocationContextEnabled() != null)
            capabilities.setCapability("locationContextEnabled",
                    wc.getLocationContextEnabled());
        if (wc.getApplicationCacheEnabled() != null)
            capabilities.setCapability("applicationCacheEnabled",
                    wc.getApplicationCacheEnabled());
        if (wc.getBrowserConnectionEnabled() != null)
            capabilities.setCapability("browserConnectionEnabled",
                    wc.getBrowserConnectionEnabled());
        if (wc.getWebStorageEnabled() != null)
            capabilities.setCapability("webStorageEnabled", wc.getWebStorageEnabled());
        if (wc.getAcceptSSLCerts() != null)
            capabilities.setCapability("acceptSslCerts", wc.getAcceptSSLCerts());
        if (wc.getRotatable() != null)
            capabilities.setCapability("rotatable", wc.getRotatable());
        if (wc.getNativeEvents() != null)
            capabilities.setCapability("nativeEvents", wc.getNativeEvents());
        if (wc.getUnexpectedAlertBehavior() != null)
            capabilities.setCapability("unexpectedAlertBehaviour",
                    wc.getUnexpectedAlertBehavior());
        if (wc.getElementScrollBehavior() != null)
            capabilities.setCapability("elementScrollBehavior",
                    wc.getElementScrollBehavior());

        // JSON Proxy
        if (wc.getJSONProxyType().length() > 0)
            capabilities.setCapability("proxyType", wc.getJSONProxyType());
        if (wc.getJSONProxyAutoConfigURL().length() > 0)
            capabilities.setCapability("proxyAutoconfigUrl",
                    wc.getJSONProxyAutoConfigURL());
        if (wc.getJSONProxy().length() > 0)
            capabilities.setCapability(wc.getJSONProxy(),
                    wc.getJSONProxy());
        if (wc.getJSONSocksUsername().length() > 0)
            capabilities.setCapability("socksUsername", wc.getJSONSocksUsername());
        if (wc.getJSONSocksPassword().length() > 0)
            capabilities.setCapability("socksPassword", wc.getJSONSocksPassword());
        if (wc.getJSONNoProxy().length() > 0)
            capabilities.setCapability("noProxy", wc.getJSONNoProxy());
        if (wc.getJSONLoggingComponent().length() > 0)
            capabilities.setCapability("component", wc.getJSONLoggingComponent());
        if (wc.getRemoteWebDriverQuietExceptions() != null)
            capabilities.setCapability("webdriver.remote.quietExceptions",
                    wc.getRemoteWebDriverQuietExceptions());

        return capabilities;
    }

}
