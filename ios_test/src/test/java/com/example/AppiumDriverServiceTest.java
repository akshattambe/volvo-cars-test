package com.example;

import com.example.service.AppiumDriverService;
import io.appium.java_client.ios.IOSDriver;
import org.junit.*;

public class AppiumDriverServiceTest {
    private AppiumDriverService appiumDriverService;
    private IOSDriver iosDriver;

    @Before
    public void setup(){
        appiumDriverService = AppiumDriverService.getAppiumDriverServiceInstance();
        iosDriver = appiumDriverService.initDriver();
    }

    @After
    public void teardown(){
        //Appium Service will continue to run in this case.
        appiumDriverService.quitDriver(iosDriver);
        appiumDriverService.stopAppiumService();
    }
    @Test
    public void test(){
        System.out.println("test is good.");
    }
}
