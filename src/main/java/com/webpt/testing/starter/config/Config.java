package com.webpt.testing.starter.config;

import com.webpt.testing.atf.config.WebDriverConfiguration;

public class Config extends WebDriverConfiguration {

    public String getGoogleUrl()
    {
        String key = "google.url";
        return getConfigurationManager().getStringEntry(key);
    }
    public String getBingUrl()
    {
        String key = "bing.url";
        return getConfigurationManager().getStringEntry(key);
    }

}
