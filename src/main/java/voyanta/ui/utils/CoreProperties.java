package voyanta.ui.utils;


//import static StringConversionUtils.toBoolean;


import org.apache.log4j.Logger;
import voyanta.ui.utils.unused.CoreSystemProperty;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CoreProperties {

    private static final Logger LOGGER = Logger.getLogger(CoreProperties.class);
    private final Properties properties;
    public static final String SYSTEM_PROPERTY_USER_NAME = "user.name";
    private final String PROPERTIES_PATH = "properties/";
    private final String PROPERTIES_EXTENSION = ".properties";
    private final String DEFAULT_ENVIRONMENT_PROPERTIES = "base" + PROPERTIES_EXTENSION;
//    private final String ENVIRONMENT_OVERRIDE_PROPERTIES = getSystemProperty(TEST_ENV) + PROPERTIES_EXTENSION;
    private final String USER_OVERRIDE_PROPERTIES = System.getProperty(SYSTEM_PROPERTY_USER_NAME, "") + PROPERTIES_EXTENSION;
    private boolean logging = true;
    private String basePropertiesFileName;
    private String environmentOverridePropertiesFileName;
    private String userOverridePropertiesFileName;
    private List<String> environmentSetMode = new ArrayList<String>();
    private static CoreProperties instance;
//
    public CoreProperties() {
        getPropertyFileNames();
        properties = new Properties();
//        InputStream basePropertiesFile = getBasePropertiesFile();
//        InputStream environmentOverridePropertiesFile = getEnvironmentOverridePropertiesFile();
//        InputStream userOverridePropertiesFile = getUserOverridePropertiesFile();
//        properties = getAllProperties(basePropertiesFile, environmentOverridePropertiesFile, userOverridePropertiesFile);
    }

    public static void setSystemProperty(CoreSystemProperty coreSystemProperty, String value) {
        System.setProperty(coreSystemProperty.getPropertyName(), value);
    }

    public static String getSystemProperty(CoreSystemProperty coreSystemProperty) {
        return System.getProperty(coreSystemProperty.getPropertyName());
    }

    public static CoreProperties getNewJacuzziProperties() {
        return new CoreProperties();
    }

    public static CoreProperties getJacuzziProperties() {
        if (instance == null) {
            LOGGER.info("New Jacuzzi Properties instance created");
            instance = getNewJacuzziProperties();
        } else {
            LOGGER.debug("Using same Jacuzzi Properties instance");
        }
        return instance;
    }

    public static boolean getBooleanSystemProperty(CoreSystemProperty systemProperty) {
        String property = System.getProperty(systemProperty.getPropertyName());
        return Boolean.parseBoolean(property) || (System.getProperties().containsKey(systemProperty.getPropertyName()) && property.isEmpty());
    }

    public Properties getProperties() {
        return properties;
    }

    public CoreProperties withLogging(boolean logging) {
        this.logging = logging;
        return this;
    }

    public String getBasePropertiesFileName() {
        return basePropertiesFileName;
    }

    public String getEnvironmentOverridePropertiesFileName() {
        return environmentOverridePropertiesFileName;
    }

    public String getUserOverridePropertiesFileName() {
        return userOverridePropertiesFileName;
    }

    public String getPropertyValidated(String key) {
        String property = properties.getProperty(key);
        if (property == null) throw new RuntimeException("no value exists for property '" + key + "'");
        String logMessage = "Returning property [" + key + "] as [" + property + "]";
        if (logging) LOGGER.info(logMessage);
        return property;
    }

    public static String getUserPrefix(Integer userNumber) {
        return (userNumber == null || userNumber.equals(1)) ? "" : ".user." + userNumber;
    }

    public static String getUserNumberPrefix(Integer userNumber) {
        return (userNumber == null || userNumber.equals(1)) ? "" : "." + userNumber.toString();
    }

    public static String getPublicPrefix(Boolean isPublicAccess) {
        return isPublicAccess ? ".public" : "";
    }

    public String getString(String key) {
        return getPropertyValidated(key);
    }

//    private InputStream getBasePropertiesFile() {
//        InputStream inputStream = CoreResources.getFileInputStream(basePropertiesFileName);
//        LOGGER.info("Using base properties file '" + basePropertiesFileName + "'");
//        return inputStream;
//    }

//    private InputStream getEnvironmentOverridePropertiesFile() {
//        return getOverridePropertiesFile(environmentOverridePropertiesFileName, "environment");
//    }
//
//    private InputStream getUserOverridePropertiesFile() {
//        return getOverridePropertiesFile(userOverridePropertiesFileName, "user");
//    }
//
//    private InputStream getOverridePropertiesFile(String overrideFileName, String type) {
//        InputStream inputStream = null;
//        if (overrideFileName != null) {
//            inputStream = CoreResources.getFileInputStream(overrideFileName, false);
//            if (inputStream != null) {
//                LOGGER.info("Using " + type + " override properties file [" + overrideFileName + "]");
//            } else {
//                LOGGER.warn(type + " override properties file [" + overrideFileName + "] was not found");
//            }
//        }
//        return inputStream;
//    }

    private void getPropertyFileNames() {
//        String environmentPropertiesFileName = null;
//        if (isSet(ENVIRONMENT_OVERRIDE_PROPERTIES)) {
//            environmentSetMode.add(TEST_ENV.getPropertyName());
//            environmentPropertiesFileName = ENVIRONMENT_OVERRIDE_PROPERTIES;
//        }
//        if (environmentPropertiesFileName != null)
//            environmentOverridePropertiesFileName = PROPERTIES_PATH + environmentPropertiesFileName;
//
//        if (isSet(USER_OVERRIDE_PROPERTIES)) {
//            userOverridePropertiesFileName = PROPERTIES_PATH + USER_OVERRIDE_PROPERTIES;
//            environmentSetMode.add(SYSTEM_PROPERTY_USER_NAME);
//        }
//        basePropertiesFileName = PROPERTIES_PATH + DEFAULT_ENVIRONMENT_PROPERTIES;
    }

//    private boolean isSet(String propertiesFile) {
////        return !StringUtils.isNullOrNullString(propertiesFile) && !propertiesFile.equals(PROPERTIES_EXTENSION);
//    }

    private Properties getAllProperties(InputStream basePropertiesFile, InputStream environmentOverridePropertiesFile, InputStream userOverridePropertiesFile) {
        Properties properties = new Properties();
        try {
            properties.load(basePropertiesFile);
            if (environmentOverridePropertiesFile != null) {
                Properties environmentOverrideProperties = new Properties();
                environmentOverrideProperties.load(environmentOverridePropertiesFile);
                properties.putAll(environmentOverrideProperties);
            }
            if (userOverridePropertiesFile != null) {
                Properties userOverrideProperties = new Properties();
                userOverrideProperties.load(userOverridePropertiesFile);
                properties.putAll(userOverrideProperties);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties", e);
        }
        return properties;
    }

//    public String getTestEnvironmentInfo() {
//        List<String> files = new ArrayList<String>();
//        String basePropertyOnlyBeingUsed = "base property settings";
//        Sequence<String> environments = sequence(environmentSetMode);
//        if (environmentOverridePropertiesFileName != null && CoreResources.fileExists(environmentOverridePropertiesFileName)) {
//            files.add("[" + FilenameUtils.getBaseName(environmentOverridePropertiesFileName) + "] set by [" + environments.first() + "] System Property");
//        } else {
//            files.add(basePropertyOnlyBeingUsed);
//        }
//        if (userOverridePropertiesFileName != null && CoreResources.fileExists(userOverridePropertiesFileName)) {
//            files.add("override [" + FilenameUtils.getBaseName(userOverridePropertiesFileName) + "] set by [" + environments.last() + "] System Property");
//        }
//        if (files.size() == 1 && files.get(0).equals(basePropertyOnlyBeingUsed)) {
//            return "Testing on [unknown] - no environment or user override property files found";
//        } else {
//            return "Testing on " + StringUtils.join(files, ", ");
//        }
//    }

    public String getProperty(String key) {
        Object value = properties.get(key);
        return value == null ? null : value.toString();
    }

    public boolean doesPropertyExist(String key) {
        return properties.containsKey(key);
    }
}
