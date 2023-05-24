package com.example.pages.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class PickerView {

    private IOSDriver driver;

    /**
     * Locators
     */
    @iOSXCUITFindBy(accessibility = "Red color component value")
    private WebElement redWheel;
    @iOSXCUITFindBy(accessibility = "Green color component value")
    private WebElement greenWheel;
    @iOSXCUITFindBy(accessibility = "Blue color component value")
    private WebElement blueWheel;


    /**
     * Constructor
     * @param driver
     */
    public PickerView(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(1)), this);
    }

    public boolean isRedWheelDisplayed() { return redWheel.isDisplayed(); }

    public boolean isGreenWheelDisplayed() { return greenWheel.isDisplayed(); }

    public boolean isBlueWheelDisplayed() { return blueWheel.isDisplayed(); }

    public void sendKeysToRedWheel(int value) {
        if(!isValueWithinRange(value)){
            throw new IllegalArgumentException("Value must be between 0 and 255");
        }
        String localValue = Integer.toString(value);
        redWheel.sendKeys(localValue);
    }

    public void sendKeysToGreenWheel(int value) {
        if(!isValueWithinRange(value)){
        throw new IllegalArgumentException("Value must be between 0 and 255");
    }
    String localValue = Integer.toString(value);
    greenWheel.sendKeys(localValue); }

    public void sendKeysToBlueWheel(int value) {
        if(!isValueWithinRange(value)){
            throw new IllegalArgumentException("Value must be between 0 and 255");
        }
        String localValue = Integer.toString(value);
        blueWheel.sendKeys(localValue);
    }

    public int getRedWheelValue() throws NumberFormatException {
        return Integer.parseInt(redWheel.getAttribute("value"));
    }

    public int getGreenWheelValue() throws NumberFormatException {
        return Integer.parseInt(greenWheel.getAttribute("value"));
    }

    public int getBlueWheelValue() throws NumberFormatException {
        return Integer.parseInt(blueWheel.getAttribute("value"));
    }

    private boolean isValueWithinRange(int value) {
        return (value) >= 0 && (value) <= 255;
    }
}
