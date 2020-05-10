package com.DP2P.server;

import com.DP2P.Config;
import com.DP2P.Ping;
import com.DP2P.Pong;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    int port;
    ServerSocket serverSocket;
    Config config;

    public Server(Config config) throws IOException {
        serverSocket = new ServerSocket(0);
        this.port = serverSocket.getLocalPort();
        this.config = config;
        System.out.println("Listening on port: " + this.port);
    }

    @Override
    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while(true) {
                Socket serverSocket = this.serverSocket.accept();
                InputStream input = serverSocket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(input);
                Ping ping = (Ping) objectInputStream.readObject();
                switch (ping.getCommand()) {
                    case "ping":
                        //TODO
                        break;
                    case "search":
                        Pong pong = new Pong();
                        ArrayList<String> arrayList = searchLocalFile(ping.getFileName());
                        pong.setFileList(arrayList);
                        OutputStream outToServer = serverSocket.getOutputStream();
                        ObjectOutputStream out = new ObjectOutputStream(outToServer);
                        out.writeObject(pong);
                        out.flush();
                        out.close();
                        break;
                }
                input.close();
                serverSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> searchLocalFile(String filename){
        File directoryObj = config.getLocalPath().toFile();

        ArrayList<String> returnList = new ArrayList<>();
        String[] filesList = directoryObj.list();
        if (filesList != null) {
            for (String tempFileName : filesList) {
                if (tempFileName.contains(filename)) {
                    returnList.add(tempFileName);
                }
            }
        }
        return returnList;
    }
}
