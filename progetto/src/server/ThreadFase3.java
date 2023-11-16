package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ThreadFase3 extends Thread {

    Condivisa c;
    ThreadSocket mySocket;

    public ThreadFase3(Condivisa cond, ThreadSocket t) {
        c = cond;
        mySocket = t;
    }

    @Override
    public synchronized void start() { // non posso usare i throw perchè sto facendo l'override di un metodo

        String mexRicevuto = "";
        String ripsosta = "";
        InputStream in = new InputStream() { // occorre fare sto casino per farlo funzionare
            @Override
            public int read() throws IOException {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'read'");
            }

        };
        try {
            in = mySocket.s.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            mexRicevuto = reader.readLine(); // ricevo le coordinate
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] messaggioSeparato = c.parser.estraiComando(mexRicevuto);
        String comando = messaggioSeparato[0];
        Integer[] datiNumerici = c.parser.estraiDati(messaggioSeparato);
        Boolean[] risultatiSpari = new Boolean[datiNumerici.length];
        Boolean sparo = true; // variabile che controlla se ho sparato almeno un colpo, sarà false se ho usato
                              // il radar
        String risposta = "";
        switch (comando) { // dei metodi che implicano uno sparo gestisco dopo la risposta, in caso
                           // contrario direttamente nel case
            case "sparo":
                risultatiSpari = c.griglia.spara(datiNumerici);
                break;
            case "radar":
                c.griglia.radar(); // TODO:questo
                break;
            case "bomba":
                risultatiSpari = c.griglia.spara(datiNumerici);
                break;
            case "speciale":
                risultatiSpari = c.griglia.spara(datiNumerici);
                break;
            case "cambioturno":
                c.cambioturno(); // TODO:questo
                break;
        }
        if (sparo == true) {
            for (int index = 0; index < risultatiSpari.length; index++) { // per salvarmmi i risultati degli spari
                risposta += risultatiSpari[index]; // speriamo passi correttamente true o false
            }
            List<String> naviAffondate = c.griglia.controllaNavi();
            if (naviAffondate.size() > 0) {
                risposta += "/affondate;";
                if (naviAffondate.contains("all")) { // codice per dire che tutte le
                    risposta += "tutte;"; // separo l'invio degli spazi colpiti dalle eventuali navi affondate con uno
                                          // '/'
                } else {
                    for (int i = 0; i < naviAffondate.size(); i++) {
                        risposta += naviAffondate.get(i) + ";"; // se non sono tutte affondate aggungo nella risposta le
                                                                // singole navi affondate nel caso vengano affondate più
                                                                // navi contemporaneamente per esempio con una bomba
                    }
                }
            }
        }

        OutputStream out = new OutputStream() { // occorre fare sto casino per farlo funzionare

            @Override
            public void write(int b) throws IOException {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'write'");
            }

        };
        try {
            out = mySocket.s.getOutputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(out, true);
        writer.println(risposta); // invio la risposta
    }
}
