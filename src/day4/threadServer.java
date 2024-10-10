package day4;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadServer{

   public static void main(String[] args) throws IOException {

    // Set the default port to 3000
    int port = 3000;
    if (args.length > 0)
        port = Integer.parseInt(args[0]);

    // Create a server port, TCP
    ServerSocket server = new ServerSocket(port);

    //Create thread pool - to store the workers
    ExecutorService thrPool = Executors.newFixedThreadPool(2);

    
    while(true){
        // Wait for an incoming connection
        System.out.printf("Waiting for connection on port %d\n", port);
        Socket sock = server.accept();

        System.out.println("Got a new connection");

        //Create a worker to handle the work

        ClientHandler handler = new ClientHandler(sock);
        
        //pass work to worker
        thrPool.submit(handler);
      }

   }

}