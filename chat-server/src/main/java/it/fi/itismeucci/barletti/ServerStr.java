package it.fi.itismeucci.barletti;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerStr {

    static boolean serverAttivo = true;

    public void avvia() throws IOException {
        ServerSocket server = new ServerSocket(19191);
        ArrayList<Socket> S = new ArrayList<>();
        while (serverAttivo) {
            try {
                Socket client = server.accept();
                S.add(client);
                MioThread t1 = new MioThread(client, S, server);
                t1.start();
            } catch (Exception e) {
                
            }
        }
    }
}


