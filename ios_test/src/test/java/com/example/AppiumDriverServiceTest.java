package com.example;

import com.example.pages.ios.*;
import com.example.service.AppiumDriverService;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.*;
import org.openqa.selenium.WebElement;

@MicronautTest
public class AppiumDriverServiceTest {
    private AppiumDriverService appiumDriverService;
    @Inject
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
        appiumDriverService = new AppiumDriverService().getAppiumDriverServiceInstance();
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

        /**
         * Click on UiKitCatalog back button.
         */
        Assert.assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();

        /**
         * 3. Click on Sliders.
         */
        Assert.assertTrue("Steppers is not displayed on the screen.", dashboard.isSlidersDisplayed());
        dashboard.clickOnSliders();

        /**
         * Move the TINTED slider to 100%.
         */
        Assert.assertTrue(sliders.tintedSliderIsDisplayed());
        String initialSliderValue = sliders.getAttributeTintedSliderValue();
        sliders.slideTintedSliderToEnd(1);
        String finalSliderValue = sliders.getAttributeTintedSliderValue();
        Assert.assertNotEquals(initialSliderValue, finalSliderValue);
        Assert.assertEquals(finalSliderValue, "100 %");


        /**
         * Click on UiKitCatalog back button.
         */
        Assert.assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();

        /**
         * 4. Click on Picker View.
         */
        Assert.assertTrue(dashboard.isPickerViewDisplayed());
        dashboard.clickOnPickerView();

        /**
         * Set Picker Views -> Select 80 200 100.
         */
        Assert.assertTrue(pickerView.isRedWheelDisplayed());
        Assert.assertTrue(pickerView.isGreenWheelDisplayed());
        Assert.assertTrue(pickerView.isBlueWheelDisplayed());

        pickerView.sendKeysToRedWheel(80);
        Assert.assertEquals(80,pickerView.getRedWheelValue());

        pickerView.sendKeysToGreenWheel(200);
        Assert.assertEquals(200,pickerView.getGreenWheelValue());

        pickerView.sendKeysToBlueWheel(100);
        Assert.assertEquals(100,pickerView.getBlueWheelValue());

        /**
         * Click on UiKitCatalog back button.
         */
        Assert.assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();
//
//        /**
//         * Put application in the Background ,Open Last Message in the Mobile / Simulator and then open application again.
//         */
    }
}
