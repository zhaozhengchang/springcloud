package com.ceiec.twmp.tmp.utils.connection;

import com.ceiec.twmp.tmp.utils.security.ConfigSecurityUtils;
import com.ceiec.twmp.tmp.utils.security.GeneralAuInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * CreateDate：2018/4/18 <br/>
 * Author：wenliang <br/>
 * Description: utils to read calling project's properties
 **/
public class PropertyReadUtils {

    /**
     * read common properties for calling project
     *
     * @param propertyFileName original property file name of calling project
     * @param connectConfigServer whether need to connect the config-server center for forward config information
     */
    public static Properties readProjectProperties(String propertyFileName, boolean connectConfigServer) {
        try {
            //init project properties to read
            Properties projectProperties = new Properties();

            //init project common properties
            Properties commonProperties = readExternalConfig(EPropertyType.PROPERTY_COMMON, null); //read properties from common config file
            String applicationName = PropertiesLoaderUtils.loadAllProperties(propertyFileName).getProperty("spring.application.name");
            String port = commonProperties.getProperty(applicationName + ".server.port");
            projectProperties.setProperty("server.port", port); //init project port
            String eurekaIP = commonProperties.getProperty("eureka.instance.hostname");
            String eurekaPort = commonProperties.getProperty("eureka.server.port");
            projectProperties.setProperty("eureka.client.serviceUrl.defaultZone", "http://" + eurekaIP + ":" + eurekaPort + "/eureka"); //update eureka-server connection information
            projectProperties.setProperty("eureka.instance.lease-renewal-interval-in-seconds", commonProperties.getProperty("eureka.instance.lease-renewal-interval-in-seconds"));
            projectProperties.setProperty("eureka.instance.lease-expiration-duration-in-seconds", commonProperties.getProperty("eureka.instance.lease-expiration-duration-in-seconds"));

            //init project exclusive properties
            if (connectConfigServer) {
                //update config-server connection information
                projectProperties.setProperty("spring.cloud.config.discovery.enabled", commonProperties.getProperty("spring.cloud.config.discovery.enabled"));
                projectProperties.setProperty("spring.cloud.config.discovery.service-id", commonProperties.getProperty("spring.cloud.config.discovery.service-id"));
                projectProperties.setProperty("spring.cloud.config.username", commonProperties.getProperty("spring.cloud.config.username"));
                projectProperties.setProperty("spring.cloud.config.password", ConfigSecurityUtils.decrypt(commonProperties.getProperty("spring.cloud.config.password")));
                projectProperties.setProperty("spring.cloud.config.fail-fast", commonProperties.getProperty("spring.cloud.config.fail-fast"));
                projectProperties.setProperty("spring.cloud.config.retry.max-attempts", commonProperties.getProperty("spring.cloud.config.retry.max-attempts"));
            } else {
                //update project properties information
                Properties projectProps = readExternalConfig(EPropertyType.PROPERTY_PROJECT, applicationName);
                if ((projectProps != null) && !(projectProps.isEmpty())) {
                    for (Object propKey : projectProps.keySet()) {
                        //if the project is config-server, then special parse is needed
                        if ("encrypt.key".equalsIgnoreCase(propKey.toString())) {
                            projectProps.setProperty(propKey.toString(), ConfigSecurityUtils.getEncryptKey());
                        }
                        if (propKey.toString().endsWith("spring.zipkin.base-url")) {
                            projectProps.setProperty(propKey.toString(), "http://" + commonProperties.getProperty("zipkin.server.ip") + ":" + commonProperties.getProperty("zipkin.server.port"));
                        }
                        //update current project property
                        projectProperties.put(propKey.toString(), projectProps.get(propKey.toString()));
                    }
                }
            }

            return projectProperties;
        } catch (Exception e) {
            throw new RuntimeException("unable to read outside common properties", e);
        }
    }

    /**
     * read external config file information
     *
     * @param propertyType config file type
     * @param serviceID service id of project, if common config file needed, pass null
     * @return properties in external config file
     * @throws IOException IO exception
     */
    private static Properties readExternalConfig(EPropertyType propertyType, String serviceID) throws IOException {
        InputStream is = null;
        try {
            //get external config file path
            String path = GeneralAuInfo.COMMON_PROPERTIES_PATH;
            if (propertyType == EPropertyType.PROPERTY_PROJECT) { //read actual project config properties
                path += serviceID + "-config/application.properties";
            } else { //read common config properties
                path += "common.properties";
            }

            //read properties
            if (path.startsWith("https:")) {
                //read properties by http
                URL gitUrl = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) gitUrl.openConnection();
                is = conn.getInputStream();
            } else {
                //read properties locally
                is = new FileInputStream(path);
            }

            //put properties read to result
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
}
