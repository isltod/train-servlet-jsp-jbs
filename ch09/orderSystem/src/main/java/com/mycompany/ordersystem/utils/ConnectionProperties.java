package com.mycompany.ordersystem.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperties {

    public static Properties loadProperties(String filename) throws IOException {
        Properties prop = new Properties();
        ClassLoader classLoader = ConnectionProperties.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        prop.load(inputStream);
        inputStream.close();
        return prop;
    }
}
