package com.example.service;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public interface IAppiumDriverProvider {
    AppiumDriverService getAppiumDriverServiceInstance();
    IOSDriver initDriver();
    AppiumDriverLocalService startAppiumService();
    public void quitDriver(IOSDriver iosDriver);
    public void stopAppiumService(AppiumDriverLocalService appiumDriverLocalService);
}
