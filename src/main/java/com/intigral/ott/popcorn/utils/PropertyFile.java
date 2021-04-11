package com.intigral.ott.popcorn.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This is the property file used to parameterised the test data. So that we can alter the
 * test data with out disturbing the internal code.
 */
public class PropertyFile {
    public static Map<String, String> envProp = new HashMap<String, String>();
    public static Properties propMain = new Properties();

    /**
     * Parameterizing the environment depending on the env value provided while running the test.
     * Depending on the env selected, the data will be configured in the suite.
     * Currently its just reading env for the demo purpose.
     */
    public static Map<String, String> envFile() {
        String environment = System.getProperty("env");
        try {
            if (environment.equalsIgnoreCase("nonprod")) {
                FileInputStream nonProdFile = new FileInputStream("src/main/resources/nonProd.properties");
                propMain.load(nonProdFile);
                envProp.put("ServerUrl", propMain.getProperty("ServerUrl"));

            } else if (environment.equalsIgnoreCase("preProd")) {
                FileInputStream preProdFile = new FileInputStream("src/main/resources/preProd.properties");
                propMain.load(preProdFile);
                envProp.put("ServerUrl", propMain.getProperty("ServerUrl"));

            }

        } catch (Exception e) {

        }
        return envProp;
    }

    public static Map<String, String> getConfigReader() {
        if (envProp == null) {
            envProp = envFile();
        }
        return envProp;
    }
}
