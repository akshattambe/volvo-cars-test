package com.example.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesReader {
    public Properties readConfProperties() {
        // Specify the path to the properties file
        Path propertiesFilePath = Path.of("src/main/resources/config.properties");

        // Create an instance of Properties
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream(propertiesFilePath.toFile())) {
            // Load the properties from the file
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
