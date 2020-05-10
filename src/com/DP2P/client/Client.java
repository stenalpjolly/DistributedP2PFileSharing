package com.DP2P.client;

import com.DP2P.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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

    public ArrayList<FileInfo> findInServer(Config config, String fileName, int ttlValue) throws IOException, ClassNotFoundException {
        Node node = config.getServerNode();
        Socket client = new Socket(node.ip, node.port);
        OutputStream outToServer = client.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outToServer);
        Ping ping = new Ping(node)
                .setCommand("search")
                .setTTL(ttlValue)
                .setFileName(fileName);
        out.writeObject(ping);
        out.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
        Pong pong = (Pong) objectInputStream.readObject();
        client.close();
        return pong.getFileList();
    }

    public void downloadFile(FileInfo file) throws IOException {
        Node node = file.getNode();
        Socket client = new Socket(node.ip, node.port);
        OutputStream outToServer = client.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outToServer);
        Ping ping = new Ping(node)
                .setCommand("download")
                .setFileName(file.getFileName());
        out.writeObject(ping);
        out.flush();
        InputStream in = client.getInputStream();
        OutputStream output = new FileOutputStream(file.getFileName());
        byte[] buffer = new byte[1024];
        int bytesRead;
        System.out.println("Downloading");
        int index = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
            if (index % 10 == 0) {
                System.out.print(".");
            }
            index++;
        }
        output.close();
        client.close();
        System.out.println(" Done");
    }
}
