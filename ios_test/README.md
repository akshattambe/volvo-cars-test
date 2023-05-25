## IOSApplication - VolvoCars Test

**Command to start the Appium service:**

`appium server --allow-cors`

**Running Tests**

`mvn test` for default runs.

**Optional:**

Below options are available if you want to develop tests based on the custom configuration. 

1. cmd line for custom runs with System properties:

    `mvn test -Dappium.ios.device="iPhone 13"`
        
Use the following statement:

    `String device = System.getProperty("appium.ios.device", "iPhone 12");`

2. For custom runs with Env variables.

    `APPIUM_IOS_DEVICE="iPhone 13" mvn test`

Use the following statement:

    `String device = System.getenv("APPIUM_IOS_DEVICE");`