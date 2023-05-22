package com.example;

import com.example.service.AppiumDriverService;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.*;

public class AppiumDriverServiceTest {
    private AppiumDriverService appiumDriverService;
    private IOSDriver iosDriver;

    private AppiumDriverLocalService appiumDriverLocalService;

    @Before
    public void setup(){
        appiumDriverService = AppiumDriverService.getAppiumDriverServiceInstance();
//        appiumDriverLocalService = appiumDriverService.startAppiumService();
        iosDriver = appiumDriverService.initDriver();
    }

    @After
    public void teardown(){
        //Appium Service will continue to run in this case.
        appiumDriverService.quitDriver(iosDriver);
//        appiumDriverService.stopAppiumService(appiumDriverLocalService);
    }
    @Test
    public void test() throws InterruptedException {
        System.out.println("test is good.");
        boolean isDisplayedSwitches = iosDriver.findElement(AppiumBy.accessibilityId("Switches")).isDisplayed();
        Thread.sleep(3000);

        Assert.assertTrue(isDisplayedSwitches);
        iosDriver.findElement(AppiumBy.accessibilityId("Switches")).click();
        isDisplayedSwitches = false;
        Thread.sleep(3000);

        iosDriver.findElement(AppiumBy.accessibilityId("**/XCUIElementTypeSwitch[`value == \"1\"`][2]")).click();
        Thread.sleep(3000);

        boolean isDisplayedTintedOff = iosDriver.findElement(AppiumBy.accessibilityId("**/XCUIElementTypeSwitch[`value == \"0\"`]")).isDisplayed();
        Thread.sleep(3000);

        Assert.assertTrue(isDisplayedTintedOff);
        iosDriver.findElement(AppiumBy.accessibilityId("XCUIElementTypeButton")).click(); //click UiKitCatalog back button.
        Thread.sleep(3000);

        isDisplayedSwitches = iosDriver.findElement(AppiumBy.accessibilityId("Switches")).isDisplayed();
        Assert.assertTrue(isDisplayedSwitches);
        Thread.sleep(3000);

    }
}
