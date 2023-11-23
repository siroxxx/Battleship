package client;

import java.awt.Point;

//classe condivisa
public class condivisa {
    public static int stato = 0; // -1: waiting; 0: prima fase; 1: fase d'attacco; 2: fase di difesa
    public static Flotta listaNavi = new Flotta(); // elemento flotta che contiene una lista delle navi
    public int nave = -1;// nave che sta venendo trascinata
    public static Minimappa mappaDifesa = new Minimappa();// mappa del giocatore con le navi inserite

    public condivisa() {

    }

    // trova la nave che sta venendo selezionata col mouse
    public void getNave(Point point) {
        for (Nave n : listaNavi.flotta) {
            if (Flotta.getRectangle(n).contains(point)) {
                nave = n.id;
                break;
            }
        }
    }

    // setta tutte le navi sulla minimappa
    public static void setMinimappa() {
        mappaDifesa.setNavi(listaNavi);
    }
}
