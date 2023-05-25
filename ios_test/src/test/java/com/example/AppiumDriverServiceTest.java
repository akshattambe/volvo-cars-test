package com.example;

import com.example.Utils.PropertiesReader;
import com.example.pages.ios.*;
import com.example.service.AppiumDriverService;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppiumDriverServiceTest {
    private final Logger LOG = LoggerFactory.getLogger(AppiumDriverServiceTest.class);
    private AppiumDriverService appiumDriverService;
    private IOSDriver iosDriver;
    private AppiumDriverLocalService appiumDriverLocalService;
    private Dashboard dashboard;
    private Switches switches;
    private UiCatalogHeader uiCatalogHeader;
    private Steppers steppers;
    private Sliders sliders;
    private PickerView pickerView;

    private PropertiesReader propertiesReader;

    /**
     * This method will be executed before every test method.
     */
    @BeforeAll
    public void setup() {
        String appPath = null;
        appiumDriverService = AppiumDriverService.getAppiumDriverServiceInstance();

        try {
            appPath = System.getProperty("appium.ios.appPath").toLowerCase();
            appiumDriverService.checkAppPath(appPath);
        } catch (Exception e) {
            LOG.info("IOS App Path is not set. Continuing with the IOS_APP_PATH provided in the config.properties file.");
        }
        appiumDriverLocalService = appiumDriverService.startAppiumService();

        iosDriver = appiumDriverService.initDriver(appPath);
        dashboard = new Dashboard(iosDriver);
        switches = new Switches(iosDriver);
        uiCatalogHeader = new UiCatalogHeader(iosDriver);
        steppers = new Steppers(iosDriver);
        sliders = new Sliders(iosDriver);
        pickerView = new PickerView(iosDriver);
        propertiesReader = new PropertiesReader();
    }

    /**
     * This method will be executed after every test method.
     */
    @AfterAll
    public void teardown(){
        //Appium Service will continue to run in this case.
        appiumDriverService.quitDriver(iosDriver);
        appiumDriverService.stopAppiumService(appiumDriverLocalService);
    }

    /**
     * IOS Application Task
     */
    @Test
    public void executeStepsFromTheIOSTask() {

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

         /**
         * Put application in the Background ,Open Last Message in the Mobile / Simulator and then open application again.
         */
        String MESSAGE_BUNDLE_ID = propertiesReader.readConfProperties().getProperty("MESSAGE_BUNDLE_ID");
        String APP_BUNDLE_ID = propertiesReader.readConfProperties().getProperty("APP_BUNDLE_ID");

        HashMap args = new HashMap<>();
        args.put("bundleId", MESSAGE_BUNDLE_ID);
        iosDriver.executeScript("mobile: launchApp", args);

        args.put("bundleId", APP_BUNDLE_ID);
        iosDriver.executeScript("mobile: activateApp", args);
    }
}
