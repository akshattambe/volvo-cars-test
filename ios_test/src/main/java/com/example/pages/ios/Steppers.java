package com.example.pages.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import jakarta.inject.Inject;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class Steppers {
    private final IOSDriver driver;

    /**
     * Locators
     */
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Increment\"`][2]")
    private WebElement btn_addTint;


    /**
     * Constructor for Steppers.
     * @param driver
     */
    @Inject
    public Steppers(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(1)), this);
    }


    /**
     * Actions
     */
    public void addTintForTimes(int times) {
        for (int i = 1; i <= 10; i++) {
            // Perform actions for each iteration
            btn_addTint.click();
            //Check the value of TINTED.
            if(!(currentTintValue(i).equalsIgnoreCase(Integer.toString(i)))){
                System.out.println("the current TINT value is :" + currentTintValue(i) + ", whereas the times value is : " + i );
                throw new IllegalStateException("TINTED value is not correct");
            }
        }
    }

    private String currentTintValue(int i) {
        return driver.findElement(AppiumBy.accessibilityId(Integer.toString(i))).getText();
    }

    public boolean isTintAddDisplayed() {
        return btn_addTint.isDisplayed();
    }
}
