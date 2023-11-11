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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(costanti.portaServer); // socket su cui riceviamo i dati
        System.out.println("connettendo...");
        Parser parser = new Parser();
        Boolean fine = false;
        Random rand = new Random();
        String stato1 = "inizio"; // 3 stati: inizio, posizionamento, attacco, difesa
        String stato2 = "inizio";
        Griglia g1 = new Griglia(); // griglia giocatore 1
        Griglia g2 = new Griglia(); // griglia giocatore 2
        while (fine == false) {
            // FASE 1:COMUNICAZIONE RISPOSTE AL CLIENT CHE HA CONTATTATO IL SERVER
            // ricezione
            Socket giocatore = serverSocket.accept(); // Accetta una connession
            InputStream in = giocatore.getInputStream(); // il primo campo è il comando il secondo campo è il numero del
                                                         // giocatore
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String mexRicevuto = reader.readLine(); // leggiamo il primo campo e in base a cos'è adattiamo, creiamo un
                                                    // metodo per ogni tipo di casistica
            String comando = parser.estraiComando(mexRicevuto); // capiamo di che comando si tratta
            String numeroGiocatore = parser.estraiGiocatore(mexRicevuto);
            String risposta = "";
            boolean fase2 = false; // flag per capire se il comando nella fase2 richiede un trattamento particolare
            switch (comando) {
                case "home": // per riportare sulla schermata home
                    if (numeroGiocatore.equals("1")) {
                        stato1 = "inizio";
                    } else
                        stato2 = "inizio";
                    risposta = "inizio";
                    break;
                case "esci": // uscire dal gioco
                    fine = true;
                    risposta = "fine gioco";
                    break;
                case "inizia": // passaggio alla fase di
                    if (numeroGiocatore.equals("1")) {
                        stato1 = "posizionamento";
                        if (stato2.equals("posizionamento")) {
                            risposta = "passaggio alla fase di posizionamento delle navi";
                            fase2 = true; // per capire se bisogna passare alla fase di posizionamento o no
                        } else
                            risposta = "in attesa che l'altro giocatore sia pronto...";
                    } else {
                        stato2 = "posizionamento";
                        if (stato1.equals("posizionamento")) {
                            risposta = "passaggio alla fase di posizionamento delle navi";
                            fase2 = true;
                        } else
                            risposta = "in attesa che l'altro giocatore sia pronto...";
                    }
                    break;
                case "confermanavi":
                    Integer[] l = estraiCoordinateNavi(); // TODO:vengono estratte le singole coordinate di tutte le navi
                    if (numeroGiocatore.equals("1")) {
                        g1.crea(); // TODO:metodo per creare la griglia
                        if (!stato2.equals("attacco") && !stato2.equals("difesa")) {
                            int stato = rand.nextInt(0, 2); // randomizzazione attacco o difesa
                            if (stato == 0) {
                                stato1 = "attacco";
                            } else
                                stato1 = "difesa";
                            risposta=stato1+";attesa";          //
                        } else {
                            fase2=true;                         //devo dire all'altro client di procedere con il gioco
                            if (stato2.equals("attacco"))
                                stato1 = "difesa";
                            else stato1="attacco";
                            risposta=stato1+";gioco";
                        }
                        
                    } else {
                        g2.crea(); // TODO:metodo per creare la griglia
                        if (!stato1.equals("attacco") && !stato1.equals("difesa")) {
                            int stato = rand.nextInt(0, 2); // randomizzazione attacco o difesa
                            if (stato == 0) {
                                stato2 = "attacco";
                            } else
                                stato2 = "difesa";
                            risposta=stato2+";attesa"; 
                        }
                        else {
                            fase2=true;                         //devo dire all'altro client di procedere con il gioco
                            if (stato1.equals("attacco"))       //se il random è già stato fatto allora si va allo stato opposto dell'altro client
                                stato2 = "difesa";
                            else stato2="attacco";
                            risposta=stato2+";gioco";
                        }
                    }
                    break;
            }

            // invio
            OutputStream out = giocatore.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(risposta);
            giocatore.close();

            // FASE 2:COMUNICAZIONE ALL'ALTRO CLIENT PER ALCUNI COMANDI
            // TODO:if con lista dei comandi che non necessitano di una comunicazione con entrambi i client

            int port = 0;
            if (numeroGiocatore.equals("1")) {
                port = costanti.portaClient2; // client2 usa la porta 555
            } else
                port = costanti.portaClient1; // client1 usa la porta 333
            giocatore = new Socket(costanti.indirizzoClient, port); // immettere l'indirizzo corretto

            switch (comando) {
                case "esci":
                    risposta = "esci";
                    break;
                case "inizio":
                    if (fase2 == true) { // se entrambi sono pronti
                        risposta = "passaggio alla fase di posizionamento delle navi";
                    } else
                        risposta = "l'altro giocatore è pronto";        //!NON SO SE LASCIARLO
                    break;
                case "confermanavi":
                    if (fase2==true) {      //procedere con il gioco -> comunico all'altro client di iniziare
                        if (numeroGiocatore.equals("1")) {
                            risposta=stato2+";gioco";
                        }
                        else risposta=stato1+";gioco";
                    }
                    else risposta="l'altro giocatore è pronto";         //!NON SO SE LASCIARLO
                    break;
                    
            }

            OutputStream out2 = giocatore.getOutputStream();
            PrintWriter writer2 = new PrintWriter(out2, true);
            writer2.println(risposta); // mando il messaggio al client opposto a quello che inizialmente ha mandato
                                       // qualcosa
            giocatore.close();

            // recap stati dei client
            System.out.println("giocatore 1: " + stato1);
            System.out.println("giocatore 2: " + stato2);
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