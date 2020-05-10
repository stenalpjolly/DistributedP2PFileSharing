package com.DP2P;

import java.io.Serializable;

public class FileInfo implements Serializable {
    String fileName;
    Node node;

    public Node getNode() {
        return node;
    }

    public FileInfo setNode(Node node) {
        this.node = node;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileInfo setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "FileName='" + fileName + '\'' +
                ",IpAddress=" + node.ip +
                ",Port=" + node.port +
                '}';
    }
}
