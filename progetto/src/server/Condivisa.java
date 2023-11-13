package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Condivisa {

    public String nomeGiocatore;
    public Boolean turno;               //true=attacco | false=difesa 
    public ServerSocket serverSocket;
    public Griglia griglia;     
    public Parser parser;
    public Boolean hoperso;             //se true il giocatore il questione ha perso

    public Condivisa(String nome, ServerSocket s) throws IOException{
        nomeGiocatore=nome;
        serverSocket=s;
        griglia=new Griglia();
        parser=new Parser();
        hoperso=false;
    }
}
