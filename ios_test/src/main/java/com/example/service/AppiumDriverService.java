package com.example.service;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;


@Factory
public class AppiumDriverService implements IAppiumDriverProvider {
    AppiumDriverService appiumDriverService;
    AppiumDriverLocalService appiumDriverLocalService;

    private IOSDriver iosDriver;
    public AppiumDriverService(){

    }

    @Override
    public AppiumDriverService getAppiumDriverServiceInstance(){
        if(appiumDriverService == null) {
            appiumDriverService = new AppiumDriverService();
        }
        return appiumDriverService;
    }

    @Override
    public IOSDriver initDriver(){
        String appiumServerURLStr = "http://0.0.0.0:4723/";

        DesiredCapabilities desiredCapabilities = setDesiredCapabilities();
        return startDriver(appiumServerURLStr, desiredCapabilities);
    }

    /**
     * Start Appium service first.
     * @param
     */
    @Override
    public AppiumDriverLocalService startAppiumService(){
        appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();

        appiumDriverLocalService.start();

        // Print the Appium server URL
        String appiumServerUrl = appiumDriverLocalService.getUrl().toString();
        System.out.println("Appium Server started: " + appiumServerUrl);
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
    @Override
    public void quitDriver(IOSDriver iosDriver){
        if (iosDriver != null) {
            iosDriver.quit();
        }
    }

    /**
     * Stop Appium Service after quiting the driver.
     * @param appiumDriverLocalService
     */
    @Override
    public void stopAppiumService(AppiumDriverLocalService appiumDriverLocalService){
        if (appiumDriverLocalService.isRunning()) {
            appiumDriverLocalService.stop();
            System.out.println(Boolean.toString(appiumDriverLocalService.isRunning()));
        }
    }

    private DesiredCapabilities setDesiredCapabilities(){
        // Set the desired capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16.4");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        // Set the path to the app file (.app or .ipa)
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/akshat.tambe/Downloads/UIKitCatalog.app");

        return desiredCapabilities;
    }

}
