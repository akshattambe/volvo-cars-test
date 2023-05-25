package com.example.service;

import io.micronaut.context.annotation.Property;
import jakarta.inject.Singleton;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ClassDesiredCapabilities {
    List<String> dcal;
    public ClassDesiredCapabilities(@Property(name = "ios.platform.name") String profilePath,
                                    @Property(name = "ios.platform.version") String profileVersion,
                                    @Property(name = "ios.device.name") String deviceName,
                                    @Property(name = "ios.automation.name") String automationName) {
        dcal = new ArrayList<>();

        // Add elements to the ArrayList
        dcal.add(profilePath);
        dcal.add(profileVersion);
        dcal.add(deviceName);
        dcal.add(automationName);
    }

    public DesiredCapabilities setDesiredCapabilities() {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", dcal.);
        dc.setCapability("platformVersion", dc.get);
        dc.setCapability("deviceName", "iPhone 11");
        dc.setCapability("automationName", "XCUITest");
        return dc;
    }
}
