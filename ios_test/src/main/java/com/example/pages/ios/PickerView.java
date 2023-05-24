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
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Increment\"`][2]")
    private WebElement btn_addTint;


    /**
     * Constructor
     * @param driver
     */
    public PickerView(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(1)), this);
    }
}
