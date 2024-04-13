package bo.com.test.prueba.tecnica.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);
    private Properties prop;
    private static PropertiesReader instance;

    private PropertiesReader() {
        try {
            prop = new Properties();
            InputStream inputStrem = new FileInputStream(
                    System.getProperty("user.dir") + "/" + "database.properties");
            prop.load(inputStrem);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public static PropertiesReader getInstance() {
        if(instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }
    public String getProperties(String namepropertied) {
        return prop.getProperty(namepropertied);
    }
}
