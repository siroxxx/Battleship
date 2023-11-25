package client;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MyFrame frame = new MyFrame();// frame principale del gioco
        ClientSocket cs = new ClientSocket(frame);

        AspettaStato waitStato = new AspettaStato();
        waitStato.start();
        try {
            waitStato.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cs.aspettaMessaggio();

        frame.faseAttDif();
        if(condivisa.stato==1){     //aggiunta energia al primo turno
            MyFrame.faseAttDif.energia+=2;  //AGGIUNGO 2 ENERGIA SE è IN ATTACCO
        }
        do {
            AspettaComando waitComando = new AspettaComando();
            waitComando.start();
            try {
                waitComando.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (cs.azione(FaseAttDif.comando, MyFrame.faseAttDif.listSpari) == null);

        frame.setEnabled(false);
        cs.socket.close();
    }
}
