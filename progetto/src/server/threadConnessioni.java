package server;

import java.io.IOException;
import java.net.Socket;


public class threadConnessioni extends Thread{
    condivisa c;
    Socket s;
    String nome;
    public threadConnessioni(String nome, condivisa cond) throws IOException{
        s=new Socket();
        c=cond;
        this.nome=nome;
    }
    @Override
    public synchronized void start() {
        try {
            c.serverSocket.accept();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}