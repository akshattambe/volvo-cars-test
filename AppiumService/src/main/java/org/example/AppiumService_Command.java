package org.example;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.micronaut.configuration.picocli.PicocliRunner;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import jakarta.inject.Inject;

import static org.example.AppiumServiceTool.appiumDriverLocalService;

@Command(name = "appium_service", description = "...",
        mixinStandardHelpOptions = true)
public class AppiumService_Command implements Runnable {

    private static AppiumServiceTool appiumServiceTool;

    @Option(names = {"-s", "--state"}, description = "...")
    String serviceState;

    @Inject
    public AppiumService_Command() {
        appiumServiceTool = AppiumServiceTool.getAppiumDriverServiceToolInstance();
    }

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(AppiumService_Command.class, args);
    }

    public void run() {
        // business logic here
        if (serviceState.equalsIgnoreCase("start")) {
            appiumServiceTool.startAppiumService();
//            System.out.println("\n\n\n\n\n\nService is initialised.\n\n\n\n\n\n");
        } else if (serviceState.equalsIgnoreCase("stop")) {
            if (appiumDriverLocalService == null || (appiumDriverLocalService.isRunning() == false)) {
                System.out.println("\n\n\n\n\n\nService is not initialised.\n\n\n\n\n\n");
                return;
            }
            appiumServiceTool.stopAppiumService(appiumDriverLocalService);
        }else {
            System.out.println("\n\n\n\n\n\nInvalid service state: " + serviceState);
            System.out.println("Supported service states are Start or Stop.\n\n\n\n\n\n");
        }
    }
}
