package server;

import java.security.PublicKey;

public class fase1 {    //FASE 1; CONNESSIONE DEI DUE CLIENT
    condivisa c;
    public fase1(condivisa c){
        this.c=c;
    }
    public void connetti(){
        c.giocatore1.start();
        c.giocatore2.start();
        
    }
}
