package server;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public Parser(){

    }
    public String estraiComando(String messaggio){
        String []buffer=messaggio.split(";");
        return buffer[0];
    }

    public String estraiGiocatore(String messaggio){
        String []buffer=messaggio.split(";");
        return buffer[1];
    }
    public Integer[] estraiCoordinateNavi(String messaggio){
        String []buffer=messaggio.split(";");
        Integer[] tuttecoord=new Integer[buffer.length-2];
        for (int i = 2; i < buffer.length; i++) { //estraggo la parte con solo le coordinate senza il comando e il numero giocatore
            tuttecoord[i]=Integer.parseInt(buffer[i]);
        }
        return tuttecoord;
    }
}
