package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ThreadFase3 extends Thread {

    ThreadUtilities util;
    ThreadSocket mySocket;
    ThreadSocket otherSocket;
    Condivisa c;

    public ThreadFase3(ThreadUtilities u, ThreadSocket my, ThreadSocket other, Condivisa cond) {
        util = u;
        mySocket = my;
        otherSocket = other;
        c = cond;
    }

    @Override
    public void run() {
        String mexRicevuto = "";
        InputStream in = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'read'");
            }
        };
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'write'");
            }
        };
        Boolean difesa = null;
        String daInviareAlClient = "";

        // La booleana spiegata nella classe condivisa
        if (util.nomeGiocatore.equals("giocatore1")) {
            difesa = false;
        } else if (util.nomeGiocatore.equals("giocatore2")) {
            difesa = true;
        }
        String rispostaPrecedente = "";

        if (c.turno == true) {
            if (util.nomeGiocatore.equals("giocatore1")) {
                daInviareAlClient = "attacco;";
                c.rispostaDaInoltrare = "difesa;";
            } else {
                daInviareAlClient = "difesa;";
                c.rispostaDaInoltrare = "attacco;";
            }
        }

        // !!!!!!!!!!!!!!!!!!!!!! TODO:DA DECOMMENTARE, VA COMMENTATA SOLO IN FASE DI TESTING
        try {
            out = mySocket.s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter w = new PrintWriter(out, true);

        // Invio la risposta per far capire al client
        w.println(daInviareAlClient);
        try {
            out = otherSocket.s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter w2 = new PrintWriter(out, true);

        // Invio la risposta per far capire al client
        w2.println(c.rispostaDaInoltrare);
        // In che stato deve iniziare
        c.rispostaDaInoltrare = "";
        System.out.println(c.turno);    //TODO:IMPORTANTE!!!!!!!!!!!!!!!! non so perchè invia due volte il segnale d'attacco e una volta il segnale di difesa
        while (c.chiHaPerso == 0) { // qua inizia il ciclo generale

            if (c.turno == difesa) { // solo se si è in difesa
                do { ///////////////////////////////////////////////////////////////////////////////////////////// DIFESA!
                    if (!(rispostaPrecedente.equals(c.rispostaDaInoltrare))) { // se la risposta non è già stata inviata
                        // al client gliela invio
                        rispostaPrecedente = c.rispostaDaInoltrare; // in difesa si inviano le risposte all'altro client
                        try {
                            out = otherSocket.s.getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        PrintWriter writer = new PrintWriter(out, true);
                        writer.println(rispostaPrecedente); // invio la risposta
                    }
                } while ((!(c.rispostaDaInoltrare.equals("cambio;"))) && c.chiHaPerso == 0); // resta in difesa se non
                // c'è cambio turno o se
                // nessuno ha vinto
                ///////////////////////////////////////////////////////////////////////////////////////////// DIFESA!
            }
            try {
                in = mySocket.s.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            try {
                mexRicevuto = reader.readLine(); // ricevo le coordinate
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] messaggioSeparato = util.parser.estraiComando(mexRicevuto);
            String comando = messaggioSeparato[0];
            Integer[] datiNumerici = util.parser.estraiDati(messaggioSeparato);
            Integer risultatoRadar = 0; // variabile usata se viene chiamato il radar
            Boolean[] risultatiSpari = new Boolean[datiNumerici.length]; // contenitore risultati dei singoli spari a
            // partire da in alto a sinistra andando verso
            // destra
            Boolean sparo = false; // variabile che controlla se ho sparato almeno un colpo, sarà false se ho usato
            // il radar
            String risposta = ""; // metto davanti un tag come radar, sparo, cambio, affondate per far capire al
            // client cosa deve trattare
            switch (comando) { // dei metodi che implicano uno sparo gestisco dopo la risposta, in caso
                // contrario direttamente nel case
                case "radar": // PER IL RADAR PASSA SOLAMENTE IL CENTRO
                    risultatoRadar = util.griglia.radar(datiNumerici[0], datiNumerici[1]); // gli passo la x e la y del
                    // centro del radar e
                    risposta += "radar;" + risultatoRadar + ";"; // ritorno
                    break;
                case "sparo":
                case "bomba":
                case "speciale":
                    risultatiSpari = util.griglia.spara(datiNumerici);
                    sparo = true;
                    break;
                case "cambioturno":
                    c.cambioturno(); // cambio del turno
                    risposta = "cambio;";
                    System.out.println("cambio turno");
                    System.out.println(c.turno);
                    break;
            }
            if (sparo == true) {
                risposta += "spari;";
                for (int index = 0; index < risultatiSpari.length; index++) { // per salvarmmi i risultati degli spari
                    risposta += risultatiSpari[index]+";"; // speriamo passi correttamente true o false     //TODO:c'è un problema da sistemare qui
                }
                List<String> naviAffondate = util.griglia.controllaNavi();
                if (naviAffondate.size() > 0) {
                    risposta += "/affondate;"; // TODO:FARE NEL CLIENT UNO SPLIT CON '/' PER VEDERE SE C'è QUALCHE NAVE
                    // AFFONDATA
                    if (naviAffondate.contains("all")) { // codice per dire che tutte le
                        risposta += "tutte;"; // separo l'invio degli spazi colpiti dalle eventuali navi affondate con '/'
                        if (util.nomeGiocatore.equals("giocatore1")) { // metto chi ha perso!!!!!!!!!!!!!!!
                            c.chiHaPerso = 1;
                        } else
                            c.chiHaPerso = 2;
                    } else {
                        for (int i = 0; i < naviAffondate.size(); i++) {
                            risposta += naviAffondate.get(i) + ";"; // se non sono tutte affondate aggungo nella
                            // aggiungo alla risposta le singole navi affondate nel caso vengano affondate
                            // più navi contemporaneamente per esempio con una bomba
                        }
                    }
                }
            }

            try {
                out = mySocket.s.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(risposta); // invio la risposta

        }
    }
}
