package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ThreadFase3 extends Thread{



    Condivisa c;
    ThreadSocket mySocket;

    public ThreadFase3(Condivisa cond, ThreadSocket t) {
        c = cond;
        mySocket = t;
    }

    @Override
    public synchronized void start() { // non posso usare i throw perchè sto facendo l'override di un metodo

        String mexRicevuto = "";
        String ripsosta="";
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
        String[] messaggioSeparato=c.parser.estraiComando(mexRicevuto);
        String comando=messaggioSeparato[0];
        switch (comando) {      //TODO: metodi che gestiscono i vari case
            case "sparo":
                c.griglia.sparo();
                break;
            case "radar":
                c.griglia.radar();
                break;
            case "bomba":
                c.griglia.bomba();
                break;
            case "speciale":
                c.griglia.speciale();
                break;
            case "cambioturno":
                c.cambioturno();
                break;
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
        writer.println(risposta);                                               //invio la risposta
    }
}

    
}
