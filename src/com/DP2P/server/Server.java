package com.DP2P.server;

import com.DP2P.*;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.ArrayList;

public class Server extends Thread {

    ServerSocket serverSocket;
    Config config;

    public Server(Config config) throws IOException {
        serverSocket = new ServerSocket(0);
        InetAddress inetAddress = InetAddress.getLocalHost();
        config.setMyNode(new Node(inetAddress.getHostAddress(), serverSocket.getLocalPort()));
        this.config = config;
        System.out.println("Listening on port: " + this.config.getMyNode().port);
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
                        Node node = config.getMyNode();
                        ArrayList<FileInfo> arrayList = searchLocalFile(ping.getFileName(), node);
                        pong.setFileList(arrayList);
                        OutputStream outToServer = serverSocket.getOutputStream();
                        ObjectOutputStream out = new ObjectOutputStream(outToServer);
                        out.writeObject(pong);
                        out.flush();
                        out.close();
                        break;
                    case "download":
                        sendFileToClient(serverSocket, ping);
                }
                input.close();
                serverSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendFileToClient(Socket serverSocket, Ping ping) throws IOException {
        Path path = Path.of(config.getLocalPath().toString(), ping.getFileName());
        File myFile = path.toFile();

        byte[] bytes = new byte[(int) myFile.length()];
        FileInputStream inputStream = new FileInputStream(myFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.read(bytes, 0, bytes.length);
        OutputStream os = serverSocket.getOutputStream();
        os.write(bytes, 0, bytes.length);
        os.flush();
    }

    public ArrayList<FileInfo> searchLocalFile(String filename, Node node){
        File directoryObj = config.getLocalPath().toFile();

        ArrayList<FileInfo> returnList = new ArrayList<>();
        String[] filesList = directoryObj.list();
        if (filesList != null) {
            for (String tempFileName : filesList) {
                if (tempFileName.contains(filename)) {
                    FileInfo fileInfo = new FileInfo()
                            .setFileName(tempFileName)
                            .setNode(node);
                    returnList.add(fileInfo);
                }
            }
        }
        return returnList;
    }
}
