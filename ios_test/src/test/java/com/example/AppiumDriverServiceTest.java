package com.example;

import com.example.pages.ios.*;
import com.example.service.AppiumDriverService;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    @BeforeAll
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

    @AfterAll
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
        assertTrue(dashboard.isSwitchesDisplayed(), "Switch is not displayed on the screen.");
        dashboard.clickOnSwitches();

        /**
         * change TINTED to OFF.
         */
        assertTrue(switches.isTintOnDisplayed());
        String initialValue = switches.getAttributeValue_WhenTintOn();
        switches.click_ToggleTintToOff();
        assertTrue(switches.isTintOffDisplayed());
        String finalValue = switches.getAttributeValue_WhenTintOff();
        assertNotEquals(initialValue, finalValue);

        /**
         * Click on UiKitCatalog back button.
         */
        assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();

        /**
         * 2. Click on Steppers.
         */
        assertTrue(dashboard.isSteppersDisplayed(), "Steppers is not displayed on the screen.");
        dashboard.clickOnSteppers();

        /**
         * Click on + in the TINTED until value will be 10.
         */
        steppers.addTintForTimes(10);

        /**
         * Click on UiKitCatalog back button.
         */
        assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();

        /**
         * 3. Click on Sliders.
         */
        assertTrue(dashboard.isSlidersDisplayed(),"Steppers is not displayed on the screen.");
        dashboard.clickOnSliders();

        /**
         * Move the TINTED slider to 100%.
         */
        assertTrue(sliders.tintedSliderIsDisplayed());
        String initialSliderValue = sliders.getAttributeTintedSliderValue();
        sliders.slideTintedSliderToEnd(1);
        String finalSliderValue = sliders.getAttributeTintedSliderValue();
        assertNotEquals(initialSliderValue, finalSliderValue);
        assertEquals(finalSliderValue, "100 %");


        /**
         * Click on UiKitCatalog back button.
         */
        assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();

        /**
         * 4. Click on Picker View.
         */
        assertTrue(dashboard.isPickerViewDisplayed());
        dashboard.clickOnPickerView();

        /**
         * Set Picker Views -> Select 80 200 100.
         */
        assertTrue(pickerView.isRedWheelDisplayed());
        assertTrue(pickerView.isGreenWheelDisplayed());
        assertTrue(pickerView.isBlueWheelDisplayed());

        pickerView.sendKeysToRedWheel(80);
        assertEquals(80,pickerView.getRedWheelValue());

        pickerView.sendKeysToGreenWheel(200);
        assertEquals(200,pickerView.getGreenWheelValue());

        pickerView.sendKeysToBlueWheel(100);
        assertEquals(100,pickerView.getBlueWheelValue());

        /**
         * Click on UiKitCatalog back button.
         */
        assertTrue(uiCatalogHeader.backToDashboard_isDisplayed());
        uiCatalogHeader.clickBackToDashboard();
//
//        /**
//         * Put application in the Background ,Open Last Message in the Mobile / Simulator and then open application again.
//         */
    }
}
