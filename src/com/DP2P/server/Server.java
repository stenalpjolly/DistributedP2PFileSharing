package com.DP2P.server;

import com.DP2P.Config;
import com.DP2P.Ping;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    int port;
    ServerSocket serverSocket;

    public Server(Config config) throws IOException {
        serverSocket = new ServerSocket(0);
        this.port = serverSocket.getLocalPort();
        System.out.println("Listening on port: " + this.port);
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            InputStream input = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            Ping ping = (Ping)objectInputStream.readObject();
            System.out.println(ping.currentNode.port);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
