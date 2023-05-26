package com.example.service;

import com.example.Utils.PropertiesReader;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import jakarta.inject.Singleton;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;

@Singleton
public final class AppiumDriverService {
    private final Logger LOG = LoggerFactory.getLogger(AppiumDriverService.class);
    private static AppiumDriverService appiumDriverService;
    AppiumDriverLocalService appiumDriverLocalService;

    private IOSDriver iosDriver;

    /**
     * Constructor.
     */
    private AppiumDriverService(){
    }

    /**
     * Provide a single instance of the AppiumDriverService.
     */
    public static AppiumDriverService getAppiumDriverServiceInstance(){
        if(appiumDriverService == null) {
            appiumDriverService = new AppiumDriverService();
        }
        return appiumDriverService;
    }

    /**
     * 1. Call for setting the desired capabilities for the driver.
     * 2. Initialize iOS driver.
     * @param appPath
     * @return IOSDriver object.
     */
    public IOSDriver initDriver(String appPath){
        String appiumServerURLStr = "http://0.0.0.0:4723/";
        DesiredCapabilities desiredCapabilities = setDesiredCapabilities(appPath);
        return startDriver(appiumServerURLStr, desiredCapabilities);
    }

    /**
     * Start Appium service first.
     * @param
     */
    public AppiumDriverLocalService startAppiumService(){
        appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();

        appiumDriverLocalService.start();

        // Print the Appium server URL
        String appiumServerUrl = appiumDriverLocalService.getUrl().toString();
        LOG.info("Appium Server started: " + appiumServerUrl);
        return appiumDriverLocalService;
    }

    /**
     * Start the Driver.
     * @param serverUrlStr
     * @param desiredCapabilities
     * @return
     */
    private IOSDriver startDriver(String serverUrlStr, DesiredCapabilities desiredCapabilities){
        URL appiumServerURL = null;
        try {
            appiumServerURL = new URL(serverUrlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        iosDriver = new IOSDriver(appiumServerURL, desiredCapabilities);
        return iosDriver;
    }

    /**
     * Stop the driver before stopping Appium Service.
     * @param iosDriver
     */
    public void quitDriver(IOSDriver iosDriver){
        if (iosDriver != null) {
            iosDriver.quit();
        }
    }

    /**
     * Stop Appium Service after quiting the driver.
     * @param appiumDriverLocalService
     */
    public void stopAppiumService(AppiumDriverLocalService appiumDriverLocalService){
        if (appiumDriverLocalService.isRunning()) {
            appiumDriverLocalService.stop();
            LOG.info(Boolean.toString(appiumDriverLocalService.isRunning()));
        }
    }

    /**
     * Set the desired capabilities.
     * @param appPath
     * @return desiredCapabilities
     */
    private DesiredCapabilities setDesiredCapabilities(String appPath){
        // Set the desired capabilities
        PropertiesReader propertiesReader = new PropertiesReader();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, propertiesReader.readConfProperties().getProperty("PLATFORM_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, propertiesReader.readConfProperties().getProperty("PLATFORM_VERSION"));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, propertiesReader.readConfProperties().getProperty("DEVICE_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, propertiesReader.readConfProperties().getProperty("AUTOMATION_NAME"));
        // Set the path to the app file (.app or .ipa)
        if (appPath != null) {
            desiredCapabilities.setCapability(MobileCapabilityType.APP, appPath);
        } else {
            desiredCapabilities.setCapability(MobileCapabilityType.APP, propertiesReader.readConfProperties().getProperty("IOS_APP_PATH"));
        }

        return desiredCapabilities;
    }

    /**
     * Check if the app path is valid.
     * @param appPath
     * @return true if the app path is valid. Else throw an exception.
     */
    public boolean checkAppPath(String appPath){
        appPath = appPath.trim();
        File appFile = null;
        try {
            appFile = new File(appPath);
        } catch (NullPointerException e) {
            LOG.error("Invalid app path provided: appPath is null");
            throw new NullPointerException();
        } catch (InvalidPathException e) {
            LOG.error("Invalid app path provided: " + e.getMessage());
            throw new InvalidPathException("Invalid: ", appPath);
        } catch (SecurityException e) {
            LOG.error("Invalid app path provided: appPath is null");
            throw new SecurityException();
        }

        if(!(appPath.endsWith(".app") || appPath.endsWith(".ipa") || appPath.endsWith(".zip"))){
            LOG.error("Invalid app path provided. Please provide a valid app path.");
            throw new IllegalArgumentException("The app path must be.app or.ipa or .zip");
        } else if (!(appFile.exists())) {
            LOG.error("Please ensure the provided app path is for an existing iOS app file.");
            throw new IllegalArgumentException("The app file does not exist at provided location.");
        } else if (!(appFile.isFile())) {
            LOG.error("Please ensure the provided app path is a valid file.");
            throw new IllegalArgumentException("The path provided is not a file.");
        } else if (!(appFile.canExecute())) {
            LOG.error("Please ensure the provided path is for an existing iOS app that is executable.");
            throw new IllegalArgumentException("The app should be executable.");
        }
        return true;
    }

}
