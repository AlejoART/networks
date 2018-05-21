package com.webpt.testing.atf.config;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.apache.commons.configuration.*;
import org.assertj.core.api.Fail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigurationManager {

    private static Logger log = LoggerFactory.getLogger(WebDriverConfiguration.class);

    private static final String defaultConfigurationFileName = "webpt-config.xml";

    private static ConfigurationManager webptConfigurationInstance;

    /**
     * AllConfiguration
     * Contains all configuration entries
     */
    private Configuration AllConfiguration;

    private ConfigurationManager() { loadConfiguration(); }

    /**
     *WebDriverConfiguration.getInstance
     *
     * @return the instance of the WebDriverConfiguration
     */
    public static ConfigurationManager getInstance() {
        if (webptConfigurationInstance == null) {
            synchronized (WebDriverConfiguration.class) {
                ConfigurationManager inst = webptConfigurationInstance;
                if (inst == null) {
                    synchronized (WebDriverConfiguration.class) {
                        webptConfigurationInstance = new ConfigurationManager();
                        log.info("Created new instance of the webptConfigurationManager.");
                    }
                }
            }
        }
        return webptConfigurationInstance;
    }

    private void loadConfiguration()
    {
        String configFileAbsolutePath = getConfigFilePath(defaultConfigurationFileName);

        try {
            AllConfiguration = new DefaultConfigurationBuilder(configFileAbsolutePath).getConfiguration();
            //((HierarchicalConfiguration) AllConfiguration).setExpressionEngine(new XPathExpressionEngine());
        } catch (Exception e) {
            Fail.fail("failed to read config file", e);
            log.error("Failed to read config file", e);
        }
    }

    private String getConfigFilePath(String fileName) {
        URL url = this.getClass().getClassLoader().getResource(fileName);
       
        if(url == null){
            throw new RuntimeException("Config file not found in resources directory: [" + fileName + "]");
        }
        System.out.println("dfddfasf" + url.getPath());
        return url.getPath();
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

}
