package client;

import java.net.*;
import java.io.*;

//testo di base preso da google
public class ClientSocket {
    public static void main(String[] args) throws IOException { // da trasformare in un metodo da mettere nel main,
                                                                // oppure cancellare l'altro main
        Parser p = new Parser();
        Socket socket = new Socket(InetAddress.getByName(Costanti.INDIRIZZO), Costanti.PORTA_SERVER);
        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(out, true);
        System.out.println("connesso");
        String risposta = "";
        String daInviare = "";
        Fase1 f1 = new Fase1(); // fase in cui si posizionano le navi e si confermano
        Flotta f = f1.getCoordinatNavi();
        writer.println(p.parseCoordinate(f));
        risposta = reader.readLine(); // risposta della creazione della griglia
        System.out.println(risposta); // messaggio di conferma della griglia
        risposta = reader.readLine(); // messaggio di attacco o difesa
        Boolean statoAttuale = null;
        if (risposta.equals("attacco")) {
            statoAttuale = true;
        } else
            statoAttuale = false; // difesa
        Boolean fine = false;
        Boolean statoPrecedente = null; // per il cambio schermata
        do { // devo
            if (statoAttuale == true) { // TODO: collegare la variabile che simboleggia attacco e difesa
                if (statoPrecedente != statoAttuale) { // se è cambiato lo stato cambia schermata
                    cambiaSchermata();// TODO:collegamento ad un metodo che cambia schermata
                }
                statoPrecedente=true;
                daInviare = "";// TODO:collegare il payload da inviare al server
                writer.write(daInviare); // invio il comando
                risposta = reader.readLine();
                String comando = p.parseComando(risposta);
                String[] payload = p.parsePayload(risposta);
                String naviAff=null;
                if(risposta.contains("/")){
                    naviAff=p.getNaviAffondate(risposta);
                }
                Boolean[] risultatiSpari = null;
                String[] naviAffondate = null;
                Integer risRadar = null;
                switch (comando) {
                    case "spari":
                        risultatiSpari = p.parseSpari(payload);
                        naviAffondate = p.parseNaviAffondate(naviAff);
                        break;
                    case "radar":
                        risRadar = p.parseRadar(payload);
                        break;
                    case "cambio":
                        statoAttuale = false; // metto in difesa
                        break;
                }
                if (naviAffondate.length > 0 && naviAffondate[0].equals("tutte")) { // parola "tutte" per dire che tutte
                                                                                    // le navi sono state affondate
                    fine = true;
                }
                aggiornaGrafica(true, risultatiSpari, naviAffondate, risRadar, fine);// TODO: collegare metodo che
                                                                                     // aggiorna grafica a cui potrei
                                                                                     // passare stato di attacco o
                                                                                     // difesa e tutti i possibili
                                                                                     // output più la variabile per
                                                                                     // determinare la fine, verificando
                                                                                     // in quel metodo quali output sono
                                                                                     // null e quali no,

            } else { // se si è in difesa
                if (statoPrecedente != statoAttuale) { // se è cambiato lo stato cambia schermata
                    cambiaSchermata();// TODO:collegamento ad un metodo che cambia schermata
                }
                statoPrecedente=false;
                risposta = reader.readLine();
                String comando = p.parseComando(risposta);
                String[] payload = p.parsePayload(risposta);    //ritorna risSpari e risRadar
                String naviAff=null;
                if(risposta.contains("/")){
                    naviAff=p.getNaviAffondate(risposta);
                }
                Boolean[] risultatiSpari = null;
                String[] naviAffondate = null;
                Integer risRadar = null;
                switch (comando) {
                    case "spari":
                        risultatiSpari = p.parseSpari(payload);
                        if(!naviAff.equals(null)) 
                            naviAffondate = p.parseNaviAffondate(naviAff);
                        break;
                    case "radar":
                        risRadar = p.parseRadar(payload);
                        break;
                    case "cambio":
                        statoAttuale = true; // metto in attacco
                        break;
                }
                
                aggiornaGrafica(true, risultatiSpari, naviAffondate, risRadar, fine);// TODO: collegare metodo che
                                                                                     // aggiorna grafica a cui potrei
                                                                                     // passare stato di attacco o
                                                                                     // difesa e tutti i possibili
                                                                                     // output più la variabile per
                                                                                     // determinare la fine, verificando
                                                                                     // in quel metodo quali output sono
                                                                                     // null e quali no,

            }
            statoPrecedente = statoAttuale;
        } while (fine == false);

    }
}