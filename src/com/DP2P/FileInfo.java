package com.DP2P;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(fileName, fileInfo.fileName) &&
                Objects.equals(node, fileInfo.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, node);
    }
}
