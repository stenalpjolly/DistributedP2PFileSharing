package com.DP2P;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class Config {
    Properties properties = new Properties();
    Config(String[] args) throws IOException {
        Path currentPath = Path.of(System.getProperty("user.dir"),"src","config.properties");
        FileReader fileReader = new FileReader(currentPath.toFile());
        properties.load(fileReader);
    }
}
