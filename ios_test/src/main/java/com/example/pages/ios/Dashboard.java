package com.example.pages.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import jakarta.inject.Inject;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class Dashboard {
    private final IOSDriver driver;

    /**
     * Locators
     */
    @iOSXCUITFindBy(accessibility = "Switches")
    private WebElement switches;

    @iOSXCUITFindBy(accessibility = "Steppers")
    private WebElement steppers;

    @iOSXCUITFindBy(accessibility = "Sliders")
    private WebElement sliders;

    @iOSXCUITFindBy(accessibility = "Picker View")
    private WebElement pickerView;

    /**
     * Constructor for Dashboard.
     *
     * @param driver
     */
    @Inject
    public Dashboard(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(1)), this);
    }


    /**
     * Actions
     */
    public void clickOnSwitches() {
        switches.click();
    }

    public boolean isSwitchesDisplayed() {
        return switches.isDisplayed();
    }

    public boolean isSteppersDisplayed() {
        return steppers.isDisplayed();
    }

    public void clickOnSteppers() {
        steppers.click();
    }

    public boolean isSlidersDisplayed() {
        return sliders.isDisplayed();
    }

    public void clickOnSliders() {
        sliders.click();
    }

    public boolean isPickerViewDisplayed() {
        return pickerView.isDisplayed();
    }

    public void clickOnPickerView() {
        pickerView.click();
    }
}
