package com.DP2P;

import java.io.Serializable;
import java.util.Objects;

public class Ping implements Serializable {
    public Node currentNode;
    public Ping(Node node){
        currentNode = node;
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
}
