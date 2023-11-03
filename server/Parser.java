package server;

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
}
