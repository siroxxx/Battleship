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
        ServerSocket serverSocket = new ServerSocket(777);          //socket su cui riceviamo i dati
        System.out.println("connettendo...");
        Parser parser=new Parser();
        Boolean fine = false;
        String stato1="inizio";                                 //3 stati: inizio, posizionamento, attacco, difesa
        String stato2="inizio";
        while (fine == false) {
            //FASE 1:COMUNICAZIONE RISPOSTE AL SERVER CHE HA CONTATTATO
            //ricezione
            Socket giocatore = serverSocket.accept(); // Accetta una connession
            InputStream in = giocatore.getInputStream();                                    //il primo campo è il comando il secondo campo è il numero del giocatore
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String mexRicevuto = reader.readLine();                                         //leggiamo il primo campo e in base a cos'è adattiamo, creiamo un metodo per ogni tipo di casistica
            String comando=parser.estraiComando(mexRicevuto);                               //capiamo di che comando si tratta 
            String numeroGiocatore=parser.estraiGiocatore(mexRicevuto);
            String risposta="";
            switch (comando) {
                case "home":
                    if (numeroGiocatore.equals("1")) {
                        stato1="inizio";
                    }
                    else stato2="inizio";                           
                    risposta="inizio";
                    break;
                case "esci":
                    fine=true;                          //esempio
                    risposta="fine gioco";
                    break;
            }

            //invio
            OutputStream out = giocatore.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(risposta);
            giocatore.close();
            
            //FASE 2:COMUNICAZIONE ALL'ALTRO CLIENT PER ALCUNI COMANDI
            //TODO:if con lista dei comandi che non necessitano di una comunicazione con entrambi i client

            int port=0;
            if (numeroGiocatore.equals("1")) {
                port=555;                                                               //client2 usa la porta 555
            }
            else port=333;                                                              //client1 usa la porta 333
            giocatore = new Socket("127.0.0.1",port);
            
            switch (comando) {
                case "esci":
                    risposta="esci";
                    break;
            
                default:
                    break;
            }

            
            OutputStream out2 = giocatore.getOutputStream();                
            PrintWriter writer2 = new PrintWriter(out2, true);
            writer2.println(risposta);                                                  //mando il messaggio al client opposto a quello che inizialmente ha mandato qualcosa
            giocatore.close();

            //recap stati dei client
            System.out.println("giocatore 1: "+stato1); 
            System.out.println("giocatore 2: "+stato2);
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
        serverSocket.close();
}
}