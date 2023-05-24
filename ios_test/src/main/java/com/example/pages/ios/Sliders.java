package com.example.pages.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import jakarta.inject.Inject;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class Sliders {
    private final IOSDriver driver;
    private final String XPATH = "//XCUIElementTypeApplication[@name=\"UIKitCatalog\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeSlider";

    /**
     * Locators
     */
    @iOSXCUITFindBy(xpath = XPATH)
    private WebElement tintedSlider;


    /**
     * Constructor for Sliders.
     * @param driver
     */
    @Inject
    public Sliders(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(1)), this);
    }

    public boolean tintedSliderIsDisplayed() {
        return tintedSlider.isDisplayed();
    }

    /**
     * Slides the slider to the value specified.
     * @param value - the value to slide between 0 and 1 e.g. 0.8 = 80%, 1= 100%
     */
    public void slideTintedSliderToEnd(float value) {
        if (value > 1 || value < 0) {
            throw new IllegalArgumentException("Value must be between 0 and 1");
        }
        String slideValue = Float.toString(value);
        tintedSlider.sendKeys(slideValue);
    }

    public String getAttributeTintedSliderValue() { return tintedSlider.getAttribute("value");}

}
