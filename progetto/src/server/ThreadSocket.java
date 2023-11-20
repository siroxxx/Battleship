package server;

import java.io.IOException;
import java.net.Socket;


public class ThreadSocket extends Thread{
    Condivisa c;
    ThreadUtilities util;
    Socket s;
    public ThreadSocket(ThreadUtilities t, Condivisa c) throws IOException{
        s=new Socket();
        util=t;
        this.c=c;
    }
    @Override
    public void run() {
        try {
            s=c.serverSocket.accept();          //creo la socket
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}