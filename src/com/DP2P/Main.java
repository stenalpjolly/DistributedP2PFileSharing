package com.DP2P;

import com.DP2P.client.Client;
import com.DP2P.server.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Config config = new Config(args);

            new Server(config).start();

            //noinspection InfiniteLoopStatement
            while(true){
                System.out.print("command> ");
                String[] commands = br.readLine().split(" ");
                switch (commands[0]) {
                    case "open":
                        try {
                            Client client = new Client();
                            String[] options = commands[1].split(":");
                            Node node = new Node(options[0], Integer.parseInt(options[1]));
                            config.setServerNode(node);
                            client.connect(config);
                        } catch (Exception e){
                            System.out.println("Invalid argument, Please refer help");
                        }
                        break;
                    case "close":
                        config.setServerNode(null);
                        config.setFiles(null);
                        break;
                    case "info":
                        //TODO
                        break;
                    case "find":
                        try {
                            String fileName = commands[1];
                            Client client = new Client();
                            ArrayList<FileInfo> fileInfos = client.findInServer(config, fileName, config.getTTL());
                            config.setFiles(fileInfos);
                            if (fileInfos.size() == 0) {
                                System.out.println("No files found");
                            } else {
                                for (int index = 0; index < fileInfos.size(); index++) {
                                    System.out.println("Index Id: " + index + ":" + fileInfos.get(index));
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Cannot find the file");
                        }
                        break;
                    case "get":
                        try{
                            int fileId = Integer.parseInt(commands[1]);
                            FileInfo file = config.getFiles().get(fileId);
                            Client client = new Client();
                            client.downloadFile(file);
                        }catch (Exception e) {
                            System.out.println("Cannot find the file");
                        }
                        break;
                    default:
                        System.out.println("Usage:\n" +
                                "      help              :lists details of available commands\n" +
                                "      open <host:port>  :connects to a node on the network\n" +
                                "      close <id>        :closes connection by connection id (see info command)\n" +
                                "      info connections  :prints list of connected hosts with an id for each\n" +
                                "      find <keyword>    :search files on the network and lists results with an id for each entry\n" +
                                "      get <id>          :download a file by id");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: Cannot find the configuration");
        }
    }
}
