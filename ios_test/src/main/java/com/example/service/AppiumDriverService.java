package com.example.service;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import jakarta.inject.Singleton;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

//import static jdk.internal.logger.LoggerFinderLoader.service;

@Singleton
public final class AppiumDriverService {

    private static AppiumDriverService appiumDriverService;

    private AppiumDriver appiumDriver;
    private AppiumDriverService(){

    }

    public static AppiumDriverService getAppiumDriverServiceInstance(){
        if(appiumDriverService == null) {
            appiumDriverService = new AppiumDriverService();
        }
        return appiumDriverService;
    }

    public IOSDriver initDriver(){
        String appiumServerPath = "/usr/local/bin/node_modules/appium";
        String appiumServerURLStr = "http://localhost:4723/wd/hub";

        startAppiumService(appiumServerPath);
        DesiredCapabilities desiredCapabilities = setDesiredCapabilities();
        IOSDriver iosDriver = startDriver(appiumServerURLStr, desiredCapabilities);

        return iosDriver;
    }

    AppiumDriverLocalService appiumDriverLocalService;
    AppiumServiceBuilder appiumServiceBuilder;

    /**
     * Start Appium service first.
     * @param appiumServerPath
     */
    public void startAppiumService(String appiumServerPath){
//        AppiumDriverLocalService appiumService =  AppiumDriverLocalService.buildService(
//                new AppiumServiceBuilder().usingDriverExecutable(new File(appiumServerPath)));
//        appiumService.start();
//
//        appiumServiceBuilder = new AppiumServiceBuilder()
//                .usingAnyFreePort()
//                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withArgument(GeneralServerFlag.LOG_LEVEL, "error");
//        appiumDriverLocalService = appiumServiceBuilder.build();
//        appiumDriverLocalService.start();

//        appiumDriverLocalService =  AppiumDriverLocalService
//                .buildService(new AppiumServiceBuilder()
//                        .usingDriverExecutable(new File("/usr/local/bin/node"))
//                        .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//                        .withIPAddress("127.0.0.1")
//                        .usingAnyFreePort()); // usingPort(4723)
//        appiumDriverLocalService.start();

        appiumDriverLocalService =
                new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .usingPort(4723)
                .build();
        appiumDriverLocalService.clearOutPutStreams();
        appiumDriverLocalService.start();

//        AppiumDriverLocalService
//                .buildService(new AppiumServiceBuilder()
//                        .usingDriverExecutable(new File("/usr/local/bin/node"))
//                        .withAppiumJS(
//                                new File(
//                                        “/usr/local/lib/node_modules/appium/build/lib/main.js”))
//                .withIPAddress(“127.0.0.1”).usingPort(4723));
//        service.start();

        // Print the Appium server URL
        String appiumServerUrl = appiumDriverLocalService.getUrl().toString();
        System.out.println("Appium Server started: " + appiumServerUrl);
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
        assert appiumServerURL != null;
        return new IOSDriver(appiumServerURL, desiredCapabilities);
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
        appiumDriverLocalService.stop();
    }

    private DesiredCapabilities setDesiredCapabilities(){
        // Set the desired capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16.4.1");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Xcode simulator");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        // Set the path to the app file (.app or .ipa)
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/akshat.tambe/Downloads/UIKitCatalog.app");

        return desiredCapabilities;
    }

}
