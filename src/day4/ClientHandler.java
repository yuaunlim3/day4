package day4;

import java.net.*;
import java.io.*;
import java.util.*;

//Work for the thread to perform
public class ClientHandler implements Runnable {
    private final Socket sock;

    public ClientHandler(Socket s){
        sock = s;
    }
    //Entry point for thread
    @Override
    public void run(){
        String name = Thread.currentThread().getName();
        try{
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            // Get the output stream
            OutputStream os = sock.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            // Read from the client
            String fromClient = br.readLine();

            System.out.printf("[%s]>>> CLIENT: %s\n",name, fromClient);

            // Process the data
            fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();

            // Write result back to client
            bw.write(fromClient);
            bw.newLine();
            bw.flush();
            os.flush();

            os.close();
            is.close();
            sock.close();
        }

        catch(IOException ex){
            //Exception Handler
            ex.printStackTrace();
        }
    }
}
