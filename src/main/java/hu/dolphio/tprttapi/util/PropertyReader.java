/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author david
 */
public class PropertyReader {

    private final static Logger LOG = LogManager.getLogger(PropertyReader.class.getName());
    public static String propertyFile;
    // Private constructor prevents instantiation from other classes
    private String rttHost;
    private String tpHost;
    private String jiraHost;
    private Integer jiraPort;
    private String rttUserName;
    private String rttPassword;
    private String tpUserName;
    private String tpPassword;
    private String jiraUserName;
    private String jiraPassword;
    private Integer connectionTimeout;
    private String rttReportByReportUrl;
    private String rttReportByProjectUrl;
    private String flushSystem;

    private PropertyReader() {
        this(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
    }

    private PropertyReader(InputStream resourceAsStream) {
        Properties p = new Properties();
        try {
            p.load(resourceAsStream);
            rttHost = p.getProperty("rttHost");
            tpHost = p.getProperty("tpHost");
            //jiraHost = p.getProperty("jiraHost");
            //jiraPort = Integer.parseInt(p.getProperty("jiraPort"));
            rttUserName = p.getProperty("rttUserName");
            rttPassword = p.getProperty("rttPassword");
            tpUserName = p.getProperty("tpUserName");
            tpPassword = p.getProperty("tpPassword");
            //jiraUserName = p.getProperty("jiraUserName");
            //jiraPassword = p.getProperty("jiraPassword");
            connectionTimeout = Integer.parseInt(p.getProperty("clientTimeOut"));
            rttReportByReportUrl = p.getProperty("rttReportByReportUrl");
            rttReportByProjectUrl = p.getProperty("rttReportByProjectUrl");
            flushSystem = p.getProperty("flushSystem");

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * @return the rttHost
     */
    public String getRttHost() {
        return rttHost;
    }

    /**
     * @return the tpHost
     */
    public String getTpHost() {
        return tpHost;
    }

    /**
     * @return the rttUserName
     */
    public String getRttUserName() {
        return rttUserName;
    }

    /**
     * @return the rttPassword
     */
    public String getRttPassword() {
        return rttPassword;
    }

    /**
     * @return the tpUserName
     */
    public String getTpUserName() {
        return tpUserName;
    }

    /**
     * @return the tpPassword
     */
    public String getTpPassword() {
        return tpPassword;
    }

    /**
     * @return the connectionTimeout
     */
    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * @return the rttReportByReportUrl
     */
    public String getRttReportByReportUrl() {
        return rttReportByReportUrl;
    }

    /**
     * @return the rttReportByProjectUrl
     */
    public String getRttReportByProjectUrl() {
        return rttReportByProjectUrl;
    }

    /**
     * @return the jiraHost
     */
    public String getJiraHost() {
        return jiraHost;
    }

    /**
     * @return the jiraUserName
     */
    public String getJiraUserName() {
        return jiraUserName;
    }

    /**
     * @return the jiraPassword
     */
    public String getJiraPassword() {
        return jiraPassword;
    }

    /**
     * @return the jiraPort
     */
    public Integer getJiraPort() {
        return jiraPort;
    }

    /**
     * @return the flushSystem
     */
    public String getFlushSystem() {
        return flushSystem;
    }

    /**
     * SingletonHolder is loaded on the first execution of
     * Singleton.getInstance() or the first access to SingletonHolder.INSTANCE,
     * not before.
     */
    private static class SingletonHolder {

        private static final PropertyReader INSTANCE;

        static {
            if (PropertyReader.propertyFile != null) {
                try {
                    LOG.trace("Initializing from file " + propertyFile);
                    INSTANCE = new PropertyReader(new FileInputStream(propertyFile));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException("Failed to load properties", ex);
                }
            } else {
                LOG.trace("Initializing from file from default location");
                INSTANCE = new PropertyReader();
            }
        }
    }

    public static PropertyReader getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
