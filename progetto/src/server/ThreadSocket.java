package server;

import java.io.IOException;
import java.net.Socket;


public class ThreadSocket extends Thread{
    Condivisa c;
    Socket s;
    String nome;
    public ThreadSocket(String nome, Condivisa cond) throws IOException{
        s=new Socket();
        c=cond;
        this.nome=nome;
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