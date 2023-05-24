package com.example;

import com.example.pages.ios.*;
import com.example.service.AppiumDriverService;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.*;
import org.openqa.selenium.WebElement;

public class AppiumDriverServiceTest {
    private AppiumDriverService appiumDriverService;
    private IOSDriver iosDriver;
    private AppiumDriverLocalService appiumDriverLocalService;
    private Dashboard dashboard;
    private Switches switches;
    private UiCatalogHeader uiCatalogHeader;
    private Steppers steppers;
    private Sliders sliders;
    private PickerView pickerView;

    @Before
    public void setup(){
        appiumDriverService = AppiumDriverService.getAppiumDriverServiceInstance();
//        appiumDriverLocalService = appiumDriverService.startAppiumService();

        iosDriver = appiumDriverService.initDriver();
        dashboard = new Dashboard(iosDriver);
        switches = new Switches(iosDriver);
        uiCatalogHeader = new UiCatalogHeader(iosDriver);
        steppers = new Steppers(iosDriver);
        sliders = new Sliders(iosDriver);
        pickerView = new PickerView(iosDriver);
    }

    @After
    public void teardown(){
        //Appium Service will continue to run in this case.
        appiumDriverService.quitDriver(iosDriver);
//        appiumDriverService.stopAppiumService(appiumDriverLocalService);
    }
    @Test
    public void test() throws InterruptedException {
        /**
         * 1. Click on Switches
         */
        Assert.assertTrue("Switch is not displayed on the screen.", dashboard.isSwitchesDisplayed());
        dashboard.clickOnSwitches();

        /**
         * change TINTED to OFF.
         */
        Assert.assertTrue(switches.isTintOnDisplayed());
        String initialValue = switches.getAttributeValue_WhenTintOn();
        switches.click_ToggleTintToOff();
        Assert.assertTrue(switches.isTintOffDisplayed());
        String finalValue = switches.getAttributeValue_WhenTintOff();
        Assert.assertNotEquals(initialValue, finalValue);

        /**
         * Click on UiKitCatalog back button.
         */
        Assert.assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();

        /**
         * 2. Click on Steppers.
         */
        Assert.assertTrue("Steppers is not displayed on the screen.", dashboard.isSteppersDisplayed());
        dashboard.clickOnSteppers();

        /**
         * Click on + in the TINTED until value will be 10.
         */
        steppers.addTintForTimes(10);
//        for (int i = 1; i <= 10; i++) {
//            // Perform actions for each iteration
//            iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"Increment\"`][2]")).click();
//            //Check the value of TINTED.
//            Assert.assertTrue(iosDriver.findElement(AppiumBy.accessibilityId(Integer.toString(i))).getText().equalsIgnoreCase(Integer.toString(i)));
//        }

        /**
         * Click on UiKitCatalog back button.
         */
        Assert.assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();
//        iosDriver.findElement(AppiumBy.accessibilityId("Steppers")).click();

//        /**
//         * Click on + in the TINTED until value will be 10.
//         */
//
//        for (int i = 1; i <= 10; i++) {
//            // Perform actions for each iteration
//            iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == \"Increment\"`][2]")).click();
//            //Check the value of TINTED.
//            Assert.assertTrue(iosDriver.findElement(AppiumBy.accessibilityId(Integer.toString(i))).getText().equalsIgnoreCase(Integer.toString(i)));
//        }
//
//        /**
//         * Click on UiKitCatalog back button.
//         */
//        backButton = iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).isDisplayed();
//        Assert.assertTrue(backButton);
//        iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).click(); //click UiKitCatalog back button.
//        Thread.sleep(1000);
//
//        /**
//         * 3. Click on Sliders.
//         */
//        iosDriver.findElement(AppiumBy.accessibilityId("Sliders")).click();
//
//        /**
//         * Change the TINTED slider to the Maximum.
//         */
//        String xpath = "//XCUIElementTypeApplication[@name=\"UIKitCatalog\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeSlider";
//        WebElement slider = iosDriver.findElement(AppiumBy.xpath(xpath));
//        slider.sendKeys("1");
//
//        /**
//         * Click on UiKitCatalog back button.
//         */
//        backButton = iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).isDisplayed();
//        Assert.assertTrue(backButton);
//        iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).click(); //click UiKitCatalog back button.
//        Thread.sleep(1000);
//
//        /**
//         * 4. Click on Picker View.
//         */
//        iosDriver.findElement(AppiumBy.accessibilityId("Picker View")).click();
//
//        /**
//         * Set Picker Views -> Select 80 200 100.
//         */
//        WebElement red = iosDriver.findElement(AppiumBy.accessibilityId("Red color component value"));
//        Assert.assertTrue(red.isDisplayed());
//        red.sendKeys("80");
//        Assert.assertTrue(red.getText().equalsIgnoreCase("80"));
//        WebElement green = iosDriver.findElement(AppiumBy.accessibilityId("Green color component value"));
//        Assert.assertTrue(green.isDisplayed());
//        green.sendKeys("200");
//        Assert.assertTrue(green.getText().equalsIgnoreCase("200"));
//        WebElement blue = iosDriver.findElement(AppiumBy.accessibilityId("Blue color component value"));
//        Assert.assertTrue(blue.isDisplayed());
//        blue.sendKeys("100");
//        Assert.assertTrue(blue.getText().equalsIgnoreCase("100"));
//
//
//        /**
//         * Click on UiKitCatalog back button.
//         */
//        backButton = iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).isDisplayed();
//        Assert.assertTrue(backButton);
//        iosDriver.findElement(AppiumBy.className("XCUIElementTypeButton")).click(); //click UiKitCatalog back button.
//
//        /**
//         * Put application in the Background ,Open Last Message in the Mobile / Simulator and then open application again.
//         */
    }
}
