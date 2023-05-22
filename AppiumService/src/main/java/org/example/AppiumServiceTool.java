package org.example;

/**
 * Hello world!
 *
 */
@Singleton
public final class AppiumServiceTool
{
    private static AppiumServiceTool appiumServiceTool;

    AppiumDriverLocalService appiumDriverLocalService;

    private AppiumDriverService(){

    }

    public static AppiumServiceTool getAppiumDriverServiceToolInstance(){
        if(AppiumServiceTool == null) {
            AppiumServiceTool = new AppiumServiceTool();
        }
        return appiumServiceTool;
    }

    public AppiumDriverLocalService startAppiumService(){
        if(appiumDriverLocalService!= null) {
            appiumDriverLocalService.stop();
        }

        appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
        appiumDriverLocalService.start();

        System.out.println("Appium Server started: " + appiumDriverLocalService.getUrl().toString());
        return appiumDriverLocalService;
    }

    /**
     * Stop Appium Service after quiting the driver.
     * @param appiumDriverLocalService
     */
    public void stopAppiumService(AppiumDriverLocalService appiumDriverLocalService){
        if (appiumDriverLocalService.isRunning()) {
            appiumDriverLocalService.stop();
            System.out.println("Appium Server stopped: " + appiumDriverLocalService.getUrl().toString());
        }
    }
}
