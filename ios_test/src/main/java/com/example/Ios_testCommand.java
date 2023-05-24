package com.example;

import com.example.service.AppiumDriverService;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import jakarta.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "ios_test", description = "...",
        mixinStandardHelpOptions = true)
public class Ios_testCommand implements Runnable {
    private AppiumDriverService appiumDriverService;
    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    @Inject
    public Ios_testCommand(ApplicationContext applicationContext) {
        this.appiumDriverService = applicationContext.getBean(AppiumDriverService.class);
    }

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(Ios_testCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
        appiumDriverService.initDriver();
    }
}
