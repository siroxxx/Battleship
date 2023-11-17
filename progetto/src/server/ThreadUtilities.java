package server;

import java.io.IOException;

public class ThreadUtilities {  //classe per le utilità tra vaari thread

    public String nomeGiocatore;
    public Griglia griglia;     
    public Parser parser;

    public ThreadUtilities(String nome) throws IOException{
        nomeGiocatore=nome;
        griglia=new Griglia();
        parser=new Parser();
    }
}
