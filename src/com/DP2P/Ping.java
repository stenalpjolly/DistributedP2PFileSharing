package com.DP2P;

import java.io.Serializable;
import java.util.Objects;

public class Ping implements Serializable {
    public Node currentNode;
    private String fileName;
    private String command;

    public Ping(Node node) {
        currentNode = node;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public String getFileName() {
        return fileName;
    }

    public Ping setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ping ping = (Ping) o;
        return Objects.equals(currentNode, ping.currentNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentNode);
    }

    public Ping setCommand(String command) {
        this.command = command;
        return this;
    }

    public String getCommand() {
        return command;
    }
}
