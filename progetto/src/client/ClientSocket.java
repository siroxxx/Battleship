package client;

import java.net.*;
import java.io.*;

//testo di base preso da google
public class ClientSocket {
    public static void main(String[] args) throws IOException { // da trasformare in un metodo da mettere nel main,
                                                                // oppure cancellare l'altro main
        Socket socket = new Socket(InetAddress.getByName(Costanti.INDIRIZZO), Costanti.PORTA_SERVER);
        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(out, true);
        System.out.println("connesso");
        String risposta = "";
        String daInviare = "";
        Fase1 f1 = new Fase1(); // fase in cui si posizionano le navi e si confermano
        // Flotta f=f1.getCoordinatNavi();
        // writer.println(f.parseCoordinate())
        risposta = reader.readLine();
        System.out.println(risposta); // messaggio di conferma della griglia
        risposta = reader.readLine(); // messaggio di attacco o difesa

        if (risposta == "attacco") {
            // chiama la schermata di attacco
        }
        // else chiama la schermata di difesa
        Boolean fine = false;
        Boolean statoPrecedente=false;  //per il cambio schermata
        do {
            if (attacco == true) {  //TODO: collegare la variabile che simboleggia attacco e difesa
                if (statoPrecedente!=attacco) {     //se è cambiato lo stato cambia schermata
                    cambiaSchermata();//TODO:collegamento ad un metodo che cambia schermata
                }
                daInviare = "";//TODO:collegare il payload da inviare al server
                writer.write(daInviare); // invio il comando
                risposta = reader.readLine(); 
                codificaRisposta();//NON SO SE SI VUOLE GESTIRE DIRETTAMENTE IL MESSAGGIO OPPURE TRASFORMATO NEL PARSER
                aggiornaGrafica();//TODO: collegare metodo che aggiorna grafica
                if (Parser.checkFine() == true) { // TODO:metodo che controlla se il messaggio contiene la parola "tutte"
                    fine = true;
                }
                
            }
            else{   //se si è in difesa
                if (statoPrecedente!=difesa) {     //se è cambiato lo stato cambia schermata
                    cambiaSchermata();//TODO:collegamento ad un metodo che cambia schermata
                }
                risposta = reader.readLine(); 
                codificaRisposta();//NON SO SE SI VUOLE GESTIRE DIRETTAMENTE IL MESSAGGIO OPPURE TRASFORMATO NEL PARSER
                aggiornaGrafica();//TODO: collegare metodo che aggiorna grafica
            }
        } while (fine == false);

    }
}