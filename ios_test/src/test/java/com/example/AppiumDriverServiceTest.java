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

        /**
         * 1. Click on Switches
         */
        boolean isDisplayedSwitches = iosDriver.findElement(AppiumBy.accessibilityId("Switches")).isDisplayed();
        Assert.assertTrue(isDisplayedSwitches);
        iosDriver.findElement(AppiumBy.accessibilityId("Switches")).click();

        /**
         * change TINTED to OFF.
         */
        boolean asd = iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSwitch[`value == \"1\"`][2]")).isDisplayed();
        Assert.assertTrue(asd);
        iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSwitch[`value == \"1\"`][2]")).click();
        //XCUIElementTypeApplication[@name="UIKitCatalog"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeSwitch

        /**
         * Click on UiKitCatalog back button.
         */
        boolean backButton = iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).isDisplayed();
        Assert.assertTrue(backButton);
        iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).click(); //click UiKitCatalog back button.


        /**
         * 2. Click on Steppers.
         */
        iosDriver.findElement(AppiumBy.accessibilityId("Steppers")).click();

        /**
         * Click on + in the TINTED until value will be 10.
         */

        for (int i = 1; i <= 10; i++) {
            // Perform actions for each iteration
            iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"Increment\"`][2]")).click();
            //Check the value of TINTED.
            Assert.assertTrue(iosDriver.findElement(AppiumBy.accessibilityId(Integer.toString(i))).getText().equalsIgnoreCase(Integer.toString(i)));
        }

        /**
         * Click on UiKitCatalog back button.
         */
        backButton = iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).isDisplayed();
        Assert.assertTrue(backButton);
        iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).click(); //click UiKitCatalog back button.
        Thread.sleep(1000);

        /**
         * 3. Click on Sliders.
         */
        iosDriver.findElement(AppiumBy.accessibilityId("Sliders")).click();
        /**
         * Click on UiKitCatalog back button.
         */
        backButton = iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).isDisplayed();
        Assert.assertTrue(backButton);
        iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).click(); //click UiKitCatalog back button.
        Thread.sleep(1000);

        /**
         * 4. Click on Picker View.
         */
        iosDriver.findElement(AppiumBy.accessibilityId("Picker View")).click();
        /**
         * Click on UiKitCatalog back button.
         */
        backButton = iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).isDisplayed();
        Assert.assertTrue(backButton);
        iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).click(); //click UiKitCatalog back button.
    }
}
