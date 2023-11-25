package client;

import java.net.*;
import java.util.List;
import java.awt.Point;
import java.io.*;

//testo di base preso da google
public class ClientSocket {
    public MyFrame frame;
    Parser p;
    Socket socket;
    InputStream in;
    BufferedReader reader;
    PrintWriter writer;
    OutputStream out;

    public ClientSocket(MyFrame f) throws IOException {
        frame = f;
        p = new Parser();
        socket = new Socket(InetAddress.getByName(Costanti.INDIRIZZO), Costanti.PORTA_SERVER);
        in = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        out = socket.getOutputStream();
        writer = new PrintWriter(out, true);
    }

    public void aspettaMessaggio() throws IOException {
        Flotta f = condivisa.listaNavi;// PASSARE COORDINATE NAVI
        writer.println(p.parseCoordinate(f));
        System.out.println(reader.readLine()); // messaggio di creazione con successo della griglia
        String risposta = reader.readLine(); // messaggio di attacco o difesa
        System.out.println(risposta);
        if (risposta.equals("attacco;")) {
            condivisa.stato = 1;
        } else
            condivisa.stato = 2;
    }

    public Boolean azione(String comandoNuovo, List<Point> payloadNuovo) throws IOException { // da trasformare in un
                                                                                              // metodo
        // da mettere nel main,
        // oppure cancellare l'altro main
        System.out.println("azione: " + comandoNuovo);
        String risposta = "";
        String daInviare = "";

        Boolean fine = null;

        if (condivisa.stato == 1) {
            daInviare = p.parseInvio(comandoNuovo, payloadNuovo);
            System.out.println(daInviare);
            writer.println(daInviare); // invio il comando
            risposta = reader.readLine();
            String comando = p.parseComando(risposta);
            String[] payload = p.parsePayload(risposta);
            String naviAff = null;
            System.out.println(risposta);
            if (risposta.contains("/")) {
                naviAff = p.getNaviAffondate(risposta);
            }
            Boolean[] risultatiSpari = null;
            String[] naviAffondate = null;
            Integer risRadar = null;
            switch (comando) {
                case "spari":
                    risultatiSpari = p.parseSpari(payload);

                    if (naviAff != null)
                        naviAffondate = p.parseNaviAffondate(naviAff);

                    if (risultatiSpari[0] == false && risultatiSpari.length == 1) // DEVE ESSEREV UN COLPO SINGOLO-> PER
                                                                                  // QUESTO CONTROLLO LENGHT
                        condivisa.stato = 2;
                    break;
                case "radar":
                    risRadar = p.parseRadar(payload);
                    System.out.println("radar: " + risRadar);
                    break;
            }
            if (naviAffondate != null)
                if (naviAffondate.length > 0 && naviAffondate[0].equals("tutte")) { // parola "tutte" per dire che tutte
                                                                                    // le navi sono state affondate
                    fine = true;
                }
            MyFrame.faseAttDif.setRisultati(risultatiSpari, naviAffondate, risRadar, fine, null);// metodo per passare i
            // risultati

        } else { // se si è in difesa
            risposta = reader.readLine();
            String comando = p.parseComando(risposta);
            System.out.println(risposta);
            String[] payload = p.parsePayloadDifesa(risposta); // ritorna risSpari e risRadar
            List<Point> listaPunti = p.getCoordinateDifesa(risposta);
            Boolean[] risultatiSpari = null;
            String[] naviAffondate = null;
            Integer risRadar = null;
            switch (comando) {
                case "sparo":
                case "bomba":
                case "aereo":
                    risultatiSpari = p.parseSpari(payload);
                    naviAffondate = p.getNaviAffDifesa(risposta);
                    if (comando.equals("sparo") && risultatiSpari.length == 1 && !risultatiSpari[0]) { // CONTROLLO CON
                                                                                                       // LENGHT PER
                                                                                                       // VEDERE SE ERA
                                                                                                       // UN COLPO
                                                                                                       // SINGOLO
                        condivisa.stato = 1;
                        MyFrame.faseAttDif.energia += 2; // SPERO CHE FUNZIONI, AGGIUNGO 2 ENERGIA OGNI INIZIO TURNO
                    }
                    break;
                case "radar":
                    risRadar = p.getRadarNumDifesa(risposta);
                    ; // numero fittizio dato che non serve alla difesa
                    break;
            }
            if (naviAffondate != null)
                if (naviAffondate.length > 0 && naviAffondate[0].equals("tutte")) { // parola "tutte" per dire che tutte
                                                                                    // le navi sono state affondate
                    fine = false;
                }
            MyFrame.faseAttDif.setRisultati(risultatiSpari, naviAffondate, risRadar, fine, listaPunti);// metodo per
                                                                                                       // passare i
            // risultati

        }
        return fine;
    }
}