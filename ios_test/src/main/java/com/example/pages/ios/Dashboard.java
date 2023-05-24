package com.example.pages.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class Dashboard{
    private IOSDriver driver;

    /**
     * Locators
     */
    @iOSXCUITFindBy(accessibility = "Switches")
    private WebElement switches;

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
}
