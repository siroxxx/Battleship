package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class condivisa {

    ServerSocket serverSocket;
    threadConnessioni giocatore1;
    threadConnessioni giocatore2;

    public condivisa() throws IOException{
        serverSocket=new ServerSocket(costanti.portaServer);
        giocatore1=new threadConnessioni("giocatore1",this);
        giocatore2=new threadConnessioni("giocatore2",this);
    }
}
