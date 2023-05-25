## IOSApplication - VolvoCars Test

### Tools Used
```
1. Programming language - Java
2. JDK version 17
3. Build tool - Maven
4. Test Framework - JUnit 4.13.2
5. Appium server 2.0
6. java-client v8.5.0
7. Selenium-java v 4.9.1
8. iOS v 16.4.1
9. macOS 13.3.1
10. Xcode v14.3
```

### Command to start the Appium service on local system: (if required)

This will start the Appium service that will be compatible for using https://inspector.appiumpro.com/

`appium server --allow-cors`

This is optional. The project can start the Appium service on local system within the project environment. 

### Pre-requisite before executing the code:

Set the application path for the iOS application in `config.properties` file.

   `IOS_APP_PATH = /Users/akshat.tambe/Downloads/UIKitCatalog.app`

### Running Tests

   `mvn test` for default runs.

**Optional:**

Below options are available if you want to run the code based on the custom configuration. 

1. User can provide the `appium.ios.appPath` in the cmd line as well. It will override the default `IOS_APP_PATH` value in `config.properties` file:


    `mvn test -Dappium.ios.appPath="path-to-UIKitCatalog.app"`
