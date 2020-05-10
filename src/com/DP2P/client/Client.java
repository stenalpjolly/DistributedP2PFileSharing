package com.DP2P.client;

import com.DP2P.Node;
import com.DP2P.Ping;

import java.io.*;
import java.net.Socket;

public class Client {

    public void connect(Node node) throws IOException {
        Socket client = new Socket(node.ip, node.port);
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outToServer);
        out.writeObject(new Ping(node));
        out.flush();
    }
}
