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
    ThreadSocket otherSocket; // per la trasmissione all'altro client
    Condivisa c;

    public ThreadFase3(ThreadUtilities u, ThreadSocket my, ThreadSocket other, Condivisa cond) {
        util = u;
        mySocket = my;
        otherSocket = other;
        c = cond;
    }

    @Override
    public synchronized void start() { // non posso usare i throw perchè sto facendo l'override di un metodo

        String mexRicevuto = ""; // dichiaro input e output streams fuori dal while spero funzioni
        InputStream in = new InputStream() { // occorre fare sto casino per farlo funzionare
            @Override
            public int read() throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'read'");
            }

        };
        OutputStream out = new OutputStream() { // occorre fare sto casino per farlo funzionare

            @Override
            public void write(int b) throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'write'");
            }

        };
        Boolean difesa = null;
        if (util.nomeGiocatore.equals("giocatore1")) { // la booleana spiegata nella classe condivisa
            difesa = false;
        } else
            difesa = true;
        while (c.chiHaPerso == 0) {
            String rispostaPrecedente = ""; // variabile per memorizzare la risposta precedente mandata dal client per
                                            // non fargli mandare più volte la stessa risposta
            if (c.turno == difesa) { // solo se si è in difesa
                do { ///////////////////////////////////////////////////////////////////////////////////////////// DIFESA!
                    if (!(rispostaPrecedente.equals(c.rispostaDaInoltrare))) { // se la risposta non è già stata inviata
                                                                               // al
                                                                               // client gliela invio
                        rispostaPrecedente = c.rispostaDaInoltrare; // in difesa si inviano le risposte all'altro client
                        try {
                            out = otherSocket.s.getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        PrintWriter writer = new PrintWriter(out, true);
                        writer.println(rispostaPrecedente); // invio la risposta
                    }
                } while ((!(c.rispostaDaInoltrare.equals("cambio;"))) || c.chiHaPerso == 0); // resta in difesa se non
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
                    break;
            }
            if (sparo == true) {
                risposta += "spari;";
                for (int index = 0; index < risultatiSpari.length; index++) { // per salvarmmi i risultati degli spari
                    risposta += risultatiSpari[index]; // speriamo passi correttamente true o false
                }
                List<String> naviAffondate = util.griglia.controllaNavi();
                if (naviAffondate.size() > 0) {
                    risposta += "/affondate;"; // TODO:FARE NEL CLIENT UNO SPLIT CON '/' PER VEDERE SE C'è QUALCHE NAVE
                                               // AFFONDATA
                    if (naviAffondate.contains("all")) { // codice per dire che tutte le
                        risposta += "tutte;"; // separo l'invio degli spazi colpiti dalle eventuali navi affondate con
                                              // uno
                                              // '/'
                        if (util.nomeGiocatore.equals("giocatore1")) {  // metto chi ha perso!!!!!!!!!!!!!!!
                            c.chiHaPerso = 1;
                        } else
                            c.chiHaPerso = 2;
                    } else {
                        for (int i = 0; i < naviAffondate.size(); i++) {
                            risposta += naviAffondate.get(i) + ";"; // se non sono tutte affondate aggungo nella
                                                                    // risposta le
                                                                    // singole navi affondate nel caso vengano affondate
                                                                    // più
                                                                    // navi contemporaneamente per esempio con una bomba
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
