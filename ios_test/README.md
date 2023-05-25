## Micronaut 3.9.1 Documentation

- [User Guide](https://docs.micronaut.io/3.9.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.9.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.9.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Maven Plugin documentation](https://micronaut-projects.github.io/micronaut-maven-plugin/latest/)


**Command to start the Appium service:**

`appium server --allow-cors`

**Running Tests**

`mvn test` for default runs.

**Optional:**

1. cmd line for custom runs with System properties:

    `mvn test -Dappium.ios.device="iPhone 13"`
        
Use the following statement:

    `String device = System.getProperty("appium.ios.device", "iPhone 12");`

2. For custom runs with Env variables.

    `APPIUM_IOS_DEVICE="iPhone 13" mvn test`

Use the following statement:

    `String device = System.getenv("APPIUM_IOS_DEVICE");`