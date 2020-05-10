package com.DP2P;

import com.DP2P.client.Client;
import com.DP2P.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Config config = new Config(args);

            new Server(config).start();
            Client client = new Client();

            //noinspection InfiniteLoopStatement
            while(true){
                System.out.print("command> ");
                String[] commands = br.readLine().split(" ");
                switch (commands[0]) {
                    case "open":
                        try {
                            String[] options = commands[1].split(":");
                            Node node = new Node(options[0], Integer.parseInt(options[1]));
                            client.connect(node);
                        } catch (Exception e){
                            System.out.println("Invalid argument, Please refer help");
                        }
                        break;
                    case "close":
                        //TODO
                        break;
                    case "info":
                        //TODO
                        break;
                    case "find":
                        //TODO
                        break;
                    case "get":
                        //TODO
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
        } catch (IOException e) {
            System.out.println("Exception: Cannot find the configuration");
        }
    }
}
