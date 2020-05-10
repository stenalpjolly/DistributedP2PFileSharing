package com.DP2P;

import java.io.Serializable;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;

    public String ip;
    public int port;

    public Node(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

}
