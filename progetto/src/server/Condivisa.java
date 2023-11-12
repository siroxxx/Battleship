package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Condivisa {

    public String nomeGiocatore;
    public ServerSocket serverSocket;
    public Griglia griglia;     //griglia navi giocatore 1
    public Parser parser;

    public Condivisa(String nome) throws IOException{
        nomeGiocatore=nome;
        serverSocket=new ServerSocket(Costanti.portaServer);
        griglia=new Griglia();
        parser=new Parser();
    }
}
