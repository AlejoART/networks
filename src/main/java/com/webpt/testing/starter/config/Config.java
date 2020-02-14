package com.webpt.testing.starter.config;

import com.webpt.testing.atf.config.WebDriverConfiguration;

public class Config extends WebDriverConfiguration {

    public String getEmrLoginUrl()
    {
        String key = "emr.url";
        return getConfigurationManager().getStringEntry(key);
    }

}
