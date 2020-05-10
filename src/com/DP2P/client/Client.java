package com.DP2P.client;

import com.DP2P.Config;
import com.DP2P.Node;
import com.DP2P.Ping;
import com.DP2P.Pong;

import java.io.*;
import java.net.Socket;

public class Client {

    public void connect(Config config) throws IOException {
        Node node = config.getServerNode();
        Socket client = new Socket(node.ip, node.port);
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outToServer);
        Ping ping = new Ping(node)
                .setCommand("ping");
        out.writeObject(ping);
        out.flush();
        client.close();
    }

    public void findInServer(Config config, String fileName) throws IOException, ClassNotFoundException {
        Node node = config.getServerNode();
        Socket client = new Socket(node.ip, node.port);
        OutputStream outToServer = client.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outToServer);
        Ping ping = new Ping(node)
                .setCommand("search")
                .setFileName(fileName);
        out.writeObject(ping);
        out.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
        Pong pong = (Pong) objectInputStream.readObject();
        pong.getFileList()
                .forEach(System.out::println);
        client.close();
    }
}
