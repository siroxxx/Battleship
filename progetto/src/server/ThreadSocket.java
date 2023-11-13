package server;

import java.io.IOException;
import java.net.Socket;


public class ThreadSocket extends Thread{
    Condivisa c;
    Socket s;
    public ThreadSocket(Condivisa cond) throws IOException{
        s=new Socket();
        c=cond;
    }
    @Override
    public synchronized void start() {
        try {
            s=c.serverSocket.accept();          //creo la socket
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}