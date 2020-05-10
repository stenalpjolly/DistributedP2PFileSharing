package com.DP2P;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Properties;

public class Config {
    Properties properties = new Properties();
    private Node serverNode;
    private Node myNode;

    private ArrayList<FileInfo> files;

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

    public Node getServerNode() {
        return serverNode;
    }

    public Config setServerNode(Node serverNode) {
        this.serverNode = serverNode;
        return this;
    }

    public Node getMyNode() {
        return myNode;
    }

    public Config setMyNode(Node myNode) {
        this.myNode = myNode;
        return this;
    }

    public ArrayList<FileInfo> getFiles() {
        return files;
    }

    public Config setFiles(ArrayList<FileInfo> files) {
        this.files = files;
        return this;
    }

    public int getTTL(){
        return Integer.parseInt(this.properties.getProperty("TTL"));
    }
}
