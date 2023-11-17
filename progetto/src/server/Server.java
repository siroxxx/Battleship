package server;

/**
 * server
 */

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(Costanti.portaServer);
        Condivisa c = new Condivisa(serverSocket);
        ThreadUtilities[] utilities = new ThreadUtilities[2];
        utilities[0] = new ThreadUtilities("giocatore1");
        utilities[1] = new ThreadUtilities("giocatore2");
        ThreadSocket[] sockets = new ThreadSocket[2];
        sockets[0] = new ThreadSocket(utilities[0], c);
        sockets[1] = new ThreadSocket(utilities[1], c);
        ThreadFase2[] fasi2 = new ThreadFase2[2];
        fasi2[0] = new ThreadFase2(utilities[0], sockets[0]); // nella fase 2 non serve la condivisa
        fasi2[1] = new ThreadFase2(utilities[1], sockets[1]);
        RandomTurno randomTurno = new RandomTurno(c);
        ThreadFase3[] fasi3 = new ThreadFase3[2];
        fasi3[0] = new ThreadFase3(utilities[0], sockets[0],sockets[1], c); // nella fase 2 non serve la condivisa
        fasi3[1] = new ThreadFase3(utilities[1], sockets[1],sockets[0], c);
        for (ThreadSocket ths : sockets) {
            ths.start();
        }
        for (ThreadSocket ths : sockets) {
            ths.join();
        }
        System.out.println("connected");
        for (ThreadFase2 tf2 : fasi2) {
            tf2.start();
        }
        for (ThreadFase2 tf2 : fasi2) {
            tf2.join();
        }
        System.out.println("let's start!");
        randomTurno.impostaTurni();
        for (ThreadFase3 tf3 : fasi3) {
            tf3.start();
        }
        for (ThreadFase3 tf3 : fasi3) {
            tf3.join();
        }
        serverSocket.close();
    }
}