package server;

import java.net.ServerSocket;

public class Condivisa {
    public Boolean turno;       //se true->attacco g1 difesa g2 | se false-> g1 difesa g2 attacco
    public ServerSocket serverSocket;
    Integer chiHaPerso; //inizializzato a 0, se è 1 ha perso g1 se è 2 ha perso g2
    String rispostaDaInoltrare;
    public Condivisa(ServerSocket s){
        serverSocket=s;
        chiHaPerso=0;
        rispostaDaInoltrare="";
    }
    public void cambioturno() {     //cambio del turno
        turno=!turno;
    }
}
