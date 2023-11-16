package server;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public Parser(){

    }
    public String[] estraiComando(String messaggio){
        String []buffer=messaggio.split(";");
        return buffer;
    }

    public Integer[] estraiCoordinateNavi(String messaggio){    //trasformo il messaggio in un vettore di interi
        String []buffer=messaggio.split(";");
        Integer[] tuttecoord=new Integer[buffer.length];
        for (int i = 0; i < buffer.length; i++) { //estraggo la parte con solo le coordinate senza il comando e il numero giocatore
            tuttecoord[i]=Integer.parseInt(buffer[i]);
        }
        return tuttecoord;
    }
    public Integer[] estraiDati(String[] messaggio){    //trasformo il messaggio in un vettore di interi

        Integer[] dati=new Integer[messaggio.length-1];
        for (int i = 1; i < messaggio.length; i++) { //estraggo la parte con solo le coordinate senza il comando e il numero giocatore
            dati[i-1]=Integer.parseInt(messaggio[i]);
        }
        return dati;
    }
}
