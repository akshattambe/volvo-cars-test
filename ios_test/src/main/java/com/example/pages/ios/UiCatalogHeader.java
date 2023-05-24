package com.example.pages.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class UiCatalogHeader {
    private IOSDriver driver;

    @iOSXCUITFindBy(className = "XCUIElementTypeButton")
    private WebElement backToDashboard;

    public UiCatalogHeader(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean backToDashboard_isDisplayed() {
        return backToDashboard.isDisplayed();
    }

    public void clickBackToDashboard() {
        backToDashboard.click();
    }
}
