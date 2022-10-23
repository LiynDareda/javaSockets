package it.fi.itismeucci.barletti;

import java.net.*;
import java.io.*;

public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 19191;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti()
    {
        System.out.println("2 CLIENT: partito in esecuzione . . .");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Host sconosciunto");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connesione !");
            System.exit(1);
        }
        return mioSocket;
    }

    public void comunica()
    {
        try {
        do{
            System.out.println("4 . . . inserisci la stringa da trasmettere al server"+'\n');
            stringaUtente = tastiera.readLine();
            System.out.println("5 . . . invio la stringa al server e attendo . . .");
            outVersoServer.writeBytes(stringaUtente+'\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8 . . . risposta dal server"+'\n'+stringaRicevutaDalServer);

            if(stringaRicevutaDalServer.equals("FINE"))
                mioSocket.close();
        }while(!(stringaRicevutaDalServer.equals("FINE")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1);
        }
    }
}
