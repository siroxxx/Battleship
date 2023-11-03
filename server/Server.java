package server;

/**
 * server
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(777);
        System.out.println("connettendo...");
        Parser parser=new Parser();
        Boolean fine = false;
        while (fine == false) {
            //ricezione
            Socket giocatore = serverSocket.accept(); // Accetta una connession
            InputStream in = giocatore.getInputStream();                                    //il primo campo è il comando il secondo campo è il numero del giocatore
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String mexRicevuto = reader.readLine();                                         //leggiamo il primo campo e in base a cos'è adattiamo, creiamo un metodo per ogni tipo di casistica
            String comando=parser.estraiComando(mexRicevuto);                               //capiamo di che comando si tratta 
            String numeroGiocatore=parser.estraiGiocatore(mexRicevuto);
            String risposta="";
            switch (comando) {
                case "ciao":
                    String s=Parser.parseCiao();                            //esempio
                    risposta=s;
                    break;
                case "sparo":
                    Map<> m=Parser.parseSparo();                            //esempio
                    risposta=s;
                    break;
        
            }

            //invio
            OutputStream out = giocatore.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(risposta);
            giocatore.close();
        }

        /*
         * esempio lettura
         * 
         * InputStream in = clientSocket.getInputStream();
         * BufferedReader reader = new BufferedReader(new InputStreamReader(in));
         * String mexRicevuto = reader.readLine();
         * 
         * esempio scrittura
         * 
         * OutputStream out = clientSocket.getOutputStream();
         * PrintWriter writer = new PrintWriter(out, true);
         * writer.println(risposta);
         */
}
}