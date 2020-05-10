package com.DP2P;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.Properties;

public class Config {
    Properties properties = new Properties();
    private Node serverNode;

    Config(String[] args) throws Exception {
        if (args[0] == null) {
            throw new Exception();
        }
        Path currentPath = Path.of(System.getProperty("user.dir"),"src","config.properties");
        FileReader fileReader = new FileReader(currentPath.toFile());
        properties.load(fileReader);

        properties.setProperty("localDir", args[0]);
    }

    public Path getLocalPath(){
        return Path.of(properties.getProperty("localDir"));
    }

    public void setServerNode(Node serverNode) {
        this.serverNode = serverNode;
    }

    public Node getServerNode() {
        return serverNode;
    }
}
