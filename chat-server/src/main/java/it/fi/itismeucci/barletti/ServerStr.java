package it.fi.itismeucci.barletti;
import java.net.*;
import java.io.*;
import java.util.*;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi()
    {
      try {
          System.out.println("1 Server partito in esecuzione . . .");
          if(server == null)
            server = new ServerSocket(19191);
          client = server.accept();
          inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
          outVersoClient = new DataOutputStream(client.getOutputStream());
      } catch (Exception e) {
          System.out.println(e.getMessage());
          System.out.println("Errore durante l'istanza del sever !");
          System.exit(1);
      }  
      return client;
    }

    public void comunica()
    {
        try {
            do{
                System.out.println("3 benvenuto client, scrivi una frase e la trasformo in mauscolo. Attendo . . .");
                stringaRicevuta = inDalClient.readLine();
                System.out.println("6 ricevuta la stampa dal client : " + stringaRicevuta);

                stringaModificata = stringaRicevuta.toUpperCase();
                System.out.println("7 Invio la stringa modificata al client . . .");
                outVersoClient.writeBytes(stringaModificata+'\n');
                if(stringaModificata.equals("FINE"))
                    client.close();
            }while(!(stringaModificata.equals("FINE")));
        } catch (Exception e) {
            //TODO: handle exception
        }
        

    }
}
