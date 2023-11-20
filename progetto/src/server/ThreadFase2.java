package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ThreadFase2 extends Thread {

    ThreadUtilities c;
    ThreadSocket mySocket;

    public ThreadFase2(ThreadUtilities cond, ThreadSocket t) {
        c = cond;
        mySocket = t;
    }
    
    @Override
    public void run() { // non posso usare i throw perchè sto facendo l'override di un metodo

        String mexRicevuto = "";
        InputStream in = new InputStream() { // occorre fare sto casino per farlo funzionare
            @Override
            public int read() throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'read'");
            }

        };
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
        c.griglia.crea(c.parser.estraiCoordinateNavi(mexRicevuto));             //estraggo le coordinate dal messaggio "parsato"
        String risposta = "griglia di " + c.nomeGiocatore + " creata con successo"; // creo la risposta
        OutputStream out = new OutputStream() { // occorre fare sto casino per farlo funzionare

            @Override
            public void write(int b) throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'write'");
            }

        };
        try {
            out = mySocket.s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(out, true);
        writer.println(risposta);                                               //invio la risposta
    }
}
