package it.fi.itismeucci.barletti;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MioThread extends Thread {
    
    Socket cli;
    ArrayList<Socket> S;
    ServerSocket server;

    public MioThread(Socket client, ArrayList<Socket> S, ServerSocket server) {
        this.cli = client;
        this.S = S;
        this.server = server;

    }

    public void run() {
        while (ServerStr.serverAttivo) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
                DataOutputStream out = new DataOutputStream(cli.getOutputStream());
                String recv = in.readLine();

                System.out.print("Stringa ricevuta: " + recv);
                String modifiedRecv = recv.toUpperCase();
                out.writeBytes(modifiedRecv + '\n');
                if (recv.equals("FINE")) {
                    cli.close();
                }
                if (recv.equals("SPEGNI")) {
                    ServerStr.serverAttivo = false;
                    for (Socket s : S) {
                        try {
                            s.close();
                        } catch (Exception e) {
                            System.out.println("Socket già chiuso");
                        }

                    }
                    server.close();
                    return;
                }
            } catch (IOException e) {
                System.out.println("Errore del thread");
            }
        }

    }
}