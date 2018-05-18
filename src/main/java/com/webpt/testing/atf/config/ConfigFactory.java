package com.webpt.testing.atf.config;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * This configuration factory will initializes and returns a Config object that contains
 * all test configuration options.
 *
 * Base configuration options come from the resources/application.yml file.
 *
 * You can override any of the options in this file for different environments in 2 ways.
 * 1. put and application.properties file in the base project folder. This is a properties file that should not
 *    be checked in. It is supposed to be an environment specific file.
 * 2. Any of the properties can be over written by environment variables. If you want to override the
 *    webdriver.driver property then set WEBDRIVER_DRIVER as an environment varible. This can be done at run time
 *    with the -DWEBDRIVER_DRIVER={your value} option or by just exporting the variable to the shell then running.
 *
 */
public class ConfigFactory {

    private static Logger logger = LoggerFactory.getLogger(ConfigFactory.class);

    private static Config config;

    public static Config getConfig(){
        if(config == null){
            config = new Config();
            applyYamlFileToConfig(getApplicationYmlFilePath());
            applyYamlFileToConfig(getOverrideFilePath());
            applyEnvironmentVariables();
        }
        return config;
    }

    private static String getConfigProperty(String systemProperty){
        String value = System.getProperty(systemProperty);
        if(value != null){
            return value;
        }
        return getEnvironment(systemProperty.toUpperCase().replace(".","_"));
    }

    public static String getEnvironment(String variable){
        return System.getenv(variable);
    }

    private static void applyEnvironmentVariables(){
        for (String property: config.getKeys()) {
            String envVal = getConfigProperty(property);
            if(envVal != null){
                config.set(property, envVal);
            }
        }
    }

    private static String getApplicationYmlFilePath() {
        URL resource = Config.class.getResource("/application.yml");
        if(resource == null){
            throw new RuntimeException("application.yml file not found. There needs to be an application.yml file in your resources folder");
        }
        return resource.getPath();
    }

    /**
     * @return
     * @throws IOException
     */
    private static String getOverrideFilePath() {
        String projectDir = System.getProperty("user.dir");
        File f = new File(projectDir);
        if(!f.exists() || !f.isDirectory()){
            logger.warn("user.dir property returned invalid directory ["+projectDir+"]");
        }
        String overrideFile = projectDir+"/application.yml";
        f = new File(overrideFile);
        if(f.exists() && !f.isDirectory()){
            return overrideFile;
        }
        return null;
    }

    /**
     * @param fileName
     * @return
     */
    private static void applyYamlFileToConfig(String fileName){
        if(fileName == null){
            return;
        }
        try {
            String fileContents = FileUtils.readFileToString(new File(fileName));
            applyMapToConfig(
                    yamlToMap(fileContents),
                    ""
            );
        }catch(IOException ex){
            logger.warn("Could not read input config file: [" + fileName +"]");
            ex.printStackTrace();
        }
    }

    private static Map<String, Object> yamlToMap(String yaml) {
        if(yaml == null || yaml.isEmpty()){
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(yaml, new TypeReference<Map<String, Object>>() {});
        }catch(IOException ex){
            throw new RuntimeException("Could not convert yaml to map", ex);
        }
    }

    private static void applyMapToConfig(Map<String, Object> map, String parentKey){
        if(map == null){
            return;
        }
        for (String mapKey : map.keySet()) {
            String propertyKey = parentKey + mapKey;
            Object value = map.get(mapKey);
            if(value instanceof Map){
                applyMapToConfig((Map) value, propertyKey + ".");
            }else{
                config.set(propertyKey, value);
            }
        }
    }

}
