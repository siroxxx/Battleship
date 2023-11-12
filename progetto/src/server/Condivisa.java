package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Condivisa {

    public ServerSocket serverSocket;
    public Griglia griglia;     //griglia navi giocatore 1

    public Condivisa() throws IOException{
        serverSocket=new ServerSocket(Costanti.portaServer);
        griglia=new Griglia();
    }
}
