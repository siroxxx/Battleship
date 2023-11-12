package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadFase2 extends Thread {

    Condivisa c;
    ThreadSocket mySocket;

    public ThreadFase2(Condivisa cond, ThreadSocket t) {
        c = cond;
        mySocket=t;
    }

    @Override
    public synchronized void start(){ 
        InputStream in=new InputStream() {              //occorre fare sto casino per farlo funzionare
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
            String mexRicevuto = reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
