package org.example;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import jakarta.inject.Singleton;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hello world!
 *
 */
@Singleton
public final class AppiumServiceTool
{
    private static AppiumServiceTool appiumServiceTool;

    public static AppiumDriverLocalService appiumDriverLocalService;

    private IOSDriver iosDriver;

    private AppiumServiceTool(){

    }

    public static AppiumServiceTool getAppiumDriverServiceToolInstance(){
        if(appiumServiceTool == null) {
            appiumServiceTool = new AppiumServiceTool();
        }
        return appiumServiceTool;
    }

    /**
     * Start Appium Service.
     * @return appiumDriverLocalService
     */
    public AppiumDriverLocalService startAppiumService(){
        if(appiumDriverLocalService!= null) {
            appiumDriverLocalService.stop();
        } else if ((appiumDriverLocalService != null) && (appiumDriverLocalService.isRunning() == false)) {
            appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
            appiumDriverLocalService.start();
            System.out.println("\n\n\n\n\n\nService is initialised.\n\n\n\n\n\n");
            System.out.println("Appium Server started: " + appiumDriverLocalService.getUrl().toString());
        }else if ((appiumDriverLocalService == null)){
            appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
            appiumDriverLocalService.start();
            System.out.println("\n\n\n\n\n\nService is initialised.\n\n\n\n\n\n");
            System.out.println("Appium Server started: " + appiumDriverLocalService.getUrl().toString());
        }
        return appiumDriverLocalService;
    }

    /**
     * Stop Appium Service after quiting the driver.
     * @param appiumDriverLocalService
     */
    public void stopAppiumService(AppiumDriverLocalService appiumDriverLocalService){
        if (appiumDriverLocalService.isRunning()) {
            appiumDriverLocalService.stop();
            System.out.println("Appium Server stopped: " + appiumDriverLocalService.getUrl().toString());
        }
    }

    public IOSDriver initDriver(){
        String appiumServerPath = "/usr/local/bin/node_modules/appium";
//        String appiumServerURLStr = "http://localhost:4723/wd/hub";
        String appiumServerURLStr = "http://0.0.0.0:4723/";

//        startAppiumService(appiumServerPath);
        DesiredCapabilities desiredCapabilities = setDesiredCapabilities();
        return startDriver(appiumServerURLStr, desiredCapabilities);
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

    private IOSDriver startDriver(String serverUrlStr, DesiredCapabilities desiredCapabilities){
        URL appiumServerURL = null;
        try {
            appiumServerURL = new URL(serverUrlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        try {
//            iosDriver = new IOSDriver(appiumServerURL, desiredCapabilities);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }

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

    public boolean isAppiumServiceRunning(){
        if(appiumDriverLocalService == null) {
            return false;
        }
        return appiumDriverLocalService!= null && appiumDriverLocalService.isRunning();
    }
}
