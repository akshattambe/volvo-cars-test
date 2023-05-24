package com.example.pages.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import jakarta.inject.Inject;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class Switches   {
    private final IOSDriver driver;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`value == \"1\"`][2]")
    private WebElement tintOn;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`value == \"0\"`]")
    private WebElement tintOff;

    /**
     * Constructor for Switches.
     * @param driver
     */
    @Inject
    public Switches(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Actions
     */
    public boolean isTintOnDisplayed(){
        return tintOn.isDisplayed();
    }

    public boolean isTintOffDisplayed(){
        return tintOff.isDisplayed();
    }

    public void click_ToggleTintToOff  (){
        tintOn.click();
    }

    public void click_ToggleTintToOn (){
        tintOff.click();
    }

    public String getAttributeValue_WhenTintOn(){
        return tintOn.getAttribute("value");
    }

    public String getAttributeValue_WhenTintOff(){
        return tintOff.getAttribute("value");
    }
}
